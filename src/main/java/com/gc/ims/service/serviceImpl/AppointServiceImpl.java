package com.gc.ims.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gc.ims.entity.AppointEntity;
import com.gc.ims.entity.InventoryEntity;
import com.gc.ims.entity.UserEntity;
import com.gc.ims.enums.AppointStatusEnums;
import com.gc.ims.mapper.AppointMapper;
import com.gc.ims.mapper.InventoryMapper;
import com.gc.ims.model.dto.AppointVo;
import com.gc.ims.model.dto.PageResponseDto;
import com.gc.ims.model.dto.ResponseDto;
import com.gc.ims.model.param.AppointParam;
import com.gc.ims.model.param.NoticeParam;
import com.gc.ims.service.*;
import com.gc.ims.util.UserKit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("AppointService")
public class AppointServiceImpl implements AppointService {

    @Resource
    private AppointMapper appointMapper;

    @Resource
    private UserKit userKit;

    @Resource
    private UserService userService;

    @Resource
    private EmailService emailService;

    @Resource
    private SmsService smsService;

    @Resource
    private InventoryMapper inventoryMapper;


    @Override
    public PageResponseDto list(AppointParam param, Integer currentPage, Integer pageSize) {
        Page<AppointVo> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(currentPage);

        UserEntity currentUser = getCurrentUser();

        if (!userKit.isAdmin()) {
            param.setUserId(currentUser.getUserId());
        }

        IPage<AppointVo> list = appointMapper.customPageList(page, param);

        return PageResponseDto.success(list.getRecords(), list.getTotal());
    }


    private UserEntity getCurrentUser() {
        return userKit.getCurrentUser();
    }

    private Integer getCurrentUserId() {
        return getCurrentUser().getUserId();
    }


    @Override
    public ResponseDto add(AppointParam param) {
        AppointEntity entity = new AppointEntity();
        Integer currentUserId = getCurrentUserId();
        entity.setUserId(currentUserId);
        entity.setPlanDate(param.getPlanDate());
        entity.setInventoryId(param.getInventoryId());
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setStatus(AppointStatusEnums.NORMAL.getCode());
        appointMapper.insert(entity);
        return ResponseDto.success();
    }

    @Override
    public ResponseDto cancel(AppointParam param) {
        AppointEntity entity = new AppointEntity();
        entity.setId(param.getId());
        entity.setStatus(AppointStatusEnums.CANCEL.getCode());
        appointMapper.updateById(entity);
        return ResponseDto.success();
    }

    @Override
    public void sendNotice() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Date endDate = calendar.getTime();
        calendar.add(Calendar.MINUTE, -1);
        Date startDate = calendar.getTime();
        QueryWrapper<AppointEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", AppointStatusEnums.NORMAL.getCode());
        queryWrapper.gt("plan_date", startDate);
        queryWrapper.le("plan_date", endDate);

        List<AppointEntity> appointEntityList = appointMapper.selectList(queryWrapper);

        List<Integer> inventoryIdList = appointEntityList.stream().map(AppointEntity::getInventoryId).collect(Collectors.toList());

        QueryWrapper<InventoryEntity> inventoryWrapper = new QueryWrapper<>();
        inventoryWrapper.in("inventory_id", inventoryIdList);
        List<InventoryEntity> inventoryEntityList = inventoryMapper.selectList(inventoryWrapper);
        Map<Integer, InventoryEntity> inventoryEntityMap = inventoryEntityList.stream().collect(Collectors.toMap(InventoryEntity::getId, inventoryEntity -> inventoryEntity));

        List<Integer> userIdList = appointEntityList.stream().map(AppointEntity::getUserId).collect(Collectors.toList());
        List<UserEntity> userEntityList = userService.listById(userIdList);

        Map<Integer, UserEntity> userEntityMap = userEntityList.stream().collect(Collectors.toMap(UserEntity::getUserId, userEntity -> userEntity));
        for (AppointEntity appointEntity : appointEntityList) {
            UserEntity userEntity = userEntityMap.get(appointEntity.getUserId());
            InventoryEntity inventoryEntity = inventoryEntityMap.get(appointEntity.getInventoryId());
            NoticeParam param = new NoticeParam();
            param.setEmailAddress(userEntity.getEmail());
            param.setPhone(userEntity.getPhone());
            param.setUserName(userEntity.getName());
            param.setLocation(inventoryEntity.getLocation());
            param.setInventoryName(inventoryEntity.getName());
            smsService.sendAppointNoticeSms(param);
            emailService.sendAppointNotice(param);

            appointEntity.setStatus(AppointStatusEnums.FINISH.getCode());
            appointMapper.updateById(appointEntity);

        }


    }


}
