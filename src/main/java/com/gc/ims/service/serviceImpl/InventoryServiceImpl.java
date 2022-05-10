package com.gc.ims.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gc.ims.entity.InventoryEntity;
import com.gc.ims.entity.SubscribeEntity;
import com.gc.ims.entity.UserEntity;
import com.gc.ims.enums.InventoryStatusEnums;
import com.gc.ims.mapper.InventoryMapper;
import com.gc.ims.mapper.SubscribeMapper;
import com.gc.ims.model.dto.PageResponseDto;
import com.gc.ims.model.dto.ResponseDto;
import com.gc.ims.model.param.InventoryParam;
import com.gc.ims.model.param.NoticeParam;
import com.gc.ims.model.result.InventoryResult;
import com.gc.ims.service.*;
import com.gc.ims.util.UserKit;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service("InventoryService")
public class InventoryServiceImpl implements InventoryService {

    @Resource
    private InventoryMapper inventoryMapper;

    @Resource
    private EmailService emailService;

    @Resource
    private SmsService smsService;

    @Resource
    private SubscribeService subscribeService;

    @Resource
    private SubscribeMapper subscribeMapper;

    @Resource
    private UserService userService;

    @Resource
    private UserKit userKit;

    private static ExecutorService send = Executors.newFixedThreadPool(1);


    @Override
    public PageResponseDto pageList(InventoryParam param, Integer currentPage, Integer pageSize) {
        Page<InventoryEntity> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(currentPage);

        QueryWrapper<InventoryEntity> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotEmpty(param.getName())) {
            queryWrapper.like("name", param.getName());
        }

        if (StringUtils.isNotEmpty(param.getType())) {
            queryWrapper.eq("type", param.getType());
        }

        if (null != param.getStatus()) {
            queryWrapper.eq("status", param.getStatus());
        }

        if (null != param.getRfidNo()) {
            queryWrapper.eq("rfid_no", param.getRfidNo());
        }

        if (null != param.getId()) {
            queryWrapper.eq("id", param.getId());
        }
        queryWrapper.eq("is_del", 0);
        IPage<InventoryEntity> list = inventoryMapper.selectPage(page, queryWrapper);
        List<InventoryResult> resultList = generateListDto(list);

        List<Integer> inventoryIdList = resultList.stream().map(InventoryResult::getId).collect(Collectors.toList());

        Integer userId = userKit.getCurrentUserId();

        QueryWrapper<SubscribeEntity> subscribeEntityQueryWrapper = new QueryWrapper<>();
        subscribeEntityQueryWrapper.in("inventory_id", inventoryIdList);
        subscribeEntityQueryWrapper.eq("user_id", userId);
        List<SubscribeEntity> subscribeEntityList = subscribeMapper.selectList(subscribeEntityQueryWrapper);

        List<Integer> subscribedInventoryIdList = subscribeEntityList.stream().map(SubscribeEntity::getInventoryId).collect(Collectors.toList());

        for (InventoryResult inventoryResult : resultList) {
            inventoryResult.setSubscribed(subscribedInventoryIdList.contains(inventoryResult.getId()));
        }

        return PageResponseDto.success(resultList, list.getTotal());
    }


    @Override
    public ResponseDto list(InventoryParam param) {
        QueryWrapper<InventoryEntity> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotEmpty(param.getName())) {
            queryWrapper.like("name", param.getName());
        }

        if (StringUtils.isNotEmpty(param.getType())) {
            queryWrapper.eq("type", param.getType());
        }

        if (null != param.getStatus()) {
            queryWrapper.eq("status", param.getStatus());
        }

        if (null != param.getRfidNo()) {
            queryWrapper.eq("rfid_no", param.getRfidNo());
        }

        if (null != param.getId()) {
            queryWrapper.eq("id", param.getId());
        }
        queryWrapper.eq("is_del", 0);
        List<InventoryEntity> list = inventoryMapper.selectList(queryWrapper);
        return ResponseDto.success(list);
    }


    private List<InventoryResult> generateListDto(IPage<InventoryEntity> list) {
        List<InventoryEntity> entityList = list.getRecords();
        if (CollectionUtils.isNotEmpty(entityList)) {
            return entityList.stream().map(entity -> {
                InventoryResult result = new InventoryResult();
                BeanUtils.copyProperties(entity, result);
                return result;
            }).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public ResponseDto add(InventoryParam param) {
        InventoryEntity entity = new InventoryEntity();
        BeanUtils.copyProperties(param, entity);
        entity.setIsDel(0);
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        inventoryMapper.insert(entity);
        InventoryResult result = new InventoryResult();
        BeanUtils.copyProperties(entity, result);
        return ResponseDto.success(result);
    }

    @Override
    public ResponseDto update(InventoryParam param) {
        InventoryEntity oldEntity = inventoryMapper.selectById(param.getId());
        InventoryEntity entity = new InventoryEntity();
        BeanUtils.copyProperties(param, entity);
        entity.setUpdateTime(new Date());
        inventoryMapper.updateById(entity);
        notify(oldEntity, entity);
        InventoryResult result = new InventoryResult();
        BeanUtils.copyProperties(entity, result);
        return ResponseDto.success(result);
    }

    private void notify(InventoryEntity oldEntity, InventoryEntity newEntity) {
        try {
            if (oldEntity.getStatus().equals(newEntity.getStatus())) {
                return;
            }

            if (InventoryStatusEnums.READY.getCode().equals(newEntity.getStatus())
                    || InventoryStatusEnums.SHOW.getCode().equals(newEntity.getStatus())) {
                send.execute(() -> sendNotice(newEntity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * sendNotice
     *
     * @param entity
     */
    private void sendNotice(InventoryEntity entity) {
        List<Integer> userIdList = subscribeService.listUserByInventory(entity.getId());
        List<UserEntity> userEntityList = userService.listById(userIdList);

        for (UserEntity userEntity : userEntityList) {
            if (InventoryStatusEnums.READY.getCode().equals(entity.getStatus())) {
                NoticeParam param = new NoticeParam();
                param.setEmailAddress(userEntity.getEmail());
                param.setPhone(userEntity.getPhone());
                param.setUserName(userEntity.getName());
                param.setInventoryName(entity.getName());
                param.setShowDate(entity.getExhibitTime());
                smsService.sendAdvanceNoticeSms(param);
                emailService.sendAdvanceNotice(param);

            } else if (InventoryStatusEnums.SHOW.getCode().equals(entity.getStatus())) {
                NoticeParam param = new NoticeParam();
                param.setEmailAddress(userEntity.getEmail());
                param.setPhone(userEntity.getPhone());
                param.setUserName(userEntity.getName());
                param.setInventoryName(entity.getName());
                param.setShowDate(entity.getExhibitTime());
                param.setLocation(entity.getLocation());
                smsService.sendNoticeSms(param);
                emailService.sendNotice(param);
            }

        }

    }

    @Override
    public ResponseDto remove(InventoryParam param) {
        InventoryEntity entity = new InventoryEntity();
        entity.setUpdateTime(new Date());
        entity.setIsDel(1);
        entity.setId(param.getId());
        inventoryMapper.updateById(entity);
        return ResponseDto.success();
    }

    @Override
    public ResponseDto detail(InventoryParam param) {
        QueryWrapper<InventoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", param.getId());
        queryWrapper.last("limit 1");
        InventoryEntity entity = inventoryMapper.selectOne(queryWrapper);

        InventoryResult result = new InventoryResult();
        BeanUtils.copyProperties(entity, result);
        return ResponseDto.success(result);

    }
}
