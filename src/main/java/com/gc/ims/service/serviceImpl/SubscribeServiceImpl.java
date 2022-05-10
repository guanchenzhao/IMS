package com.gc.ims.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gc.ims.entity.InventoryEntity;
import com.gc.ims.entity.SubscribeEntity;
import com.gc.ims.entity.UserEntity;
import com.gc.ims.mapper.InventoryMapper;
import com.gc.ims.mapper.SubscribeMapper;
import com.gc.ims.model.dto.PageResponseDto;
import com.gc.ims.model.dto.ResponseDto;
import com.gc.ims.model.param.InventoryParam;
import com.gc.ims.model.param.SubscribeParam;
import com.gc.ims.model.result.InventoryResult;
import com.gc.ims.service.SubscribeService;
import com.gc.ims.util.UserKit;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("SubscribeService")
public class SubscribeServiceImpl implements SubscribeService {

    @Resource
    private SubscribeMapper subscribeMapper;

    @Resource
    private InventoryMapper inventoryMapper;

    @Resource
    private UserKit userKit;


    @Override
    public PageResponseDto list(InventoryParam param, Integer currentPage, Integer pageSize) {
        Page<InventoryEntity> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(currentPage);

        Integer currentUserId = getCurrentUserId();
        List<Integer> inventoryIdList = getSubscribeList(currentUserId);
        QueryWrapper<InventoryEntity> queryWrapper = new QueryWrapper<>();

        if (inventoryIdList.isEmpty()) {
            return PageResponseDto.success();
        }

        queryWrapper.in("id", inventoryIdList);

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
        return PageResponseDto.success(resultList, list.getTotal());
    }

    private List<InventoryResult> generateListDto(IPage<InventoryEntity> list) {
        List<InventoryEntity> entityList = list.getRecords();
        if (CollectionUtils.isNotEmpty(entityList)) {
            return entityList.stream().map(entity -> {
                InventoryResult result = new InventoryResult();
                BeanUtils.copyProperties(entity, result);
                result.setSubscribed(true);
                return result;
            }).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }


    private List<Integer> getSubscribeList(Integer userId) {
        QueryWrapper<SubscribeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);

        List<SubscribeEntity> subscribeEntityList = subscribeMapper.selectList(queryWrapper);

        return subscribeEntityList.stream()
                .map(SubscribeEntity::getInventoryId)
                .collect(Collectors.toList());
    }

    private Integer getCurrentUserId() {
        UserEntity userEntity = userKit.getCurrentUser();
        if (userEntity != null) {
            return userEntity.getUserId();
        }

        return 0;
    }

    @Override
    public ResponseDto add(SubscribeParam param) {
        SubscribeEntity entity = new SubscribeEntity();
        Integer currentUserId = getCurrentUserId();
        entity.setUserId(currentUserId);
        entity.setInventoryId(param.getInventoryId());
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        subscribeMapper.insert(entity);
        return ResponseDto.success();
    }

    @Override
    public ResponseDto cancel(SubscribeParam param) {
        Integer currentUserId = getCurrentUserId();
        QueryWrapper<SubscribeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", currentUserId);
        queryWrapper.eq("inventory_id", param.getInventoryId());
        subscribeMapper.delete(queryWrapper);
        return ResponseDto.success();
    }

    @Override
    public List<Integer> listUserByInventory(Integer inventoryId) {
        QueryWrapper<SubscribeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("inventory_id", inventoryId);
        List<SubscribeEntity> subscribeEntityList = subscribeMapper.selectList(queryWrapper);

        return subscribeEntityList.stream()
                .map(SubscribeEntity::getUserId)
                .collect(Collectors.toList());
    }
}
