package com.gc.ims.service.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gc.ims.entity.UserEntity;
import com.gc.ims.mapper.UserMapper;
import com.gc.ims.model.dto.PageResponseDto;
import com.gc.ims.model.dto.ResponseDto;
import com.gc.ims.model.param.UserParam;
import com.gc.ims.model.result.UserResult;
import com.gc.ims.service.UserService;
import com.gc.ims.util.UserKit;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserKit userKit;


    @Override
    public PageResponseDto list(UserParam userParam, Integer currentPage, Integer pageSize) {
        Page<UserEntity> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(currentPage);

        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotEmpty(userParam.getAccount())) {
            queryWrapper.like("account", userParam.getAccount());
        }

        if (StringUtils.isNotEmpty(userParam.getName())) {
            queryWrapper.like("name", userParam.getName());
        }

        if (StringUtils.isNotEmpty(userParam.getRole())) {
            queryWrapper.eq("role", userParam.getRole());
        }

        if (null != userParam.getStatus()) {
            queryWrapper.eq("status", userParam.getStatus());
        }
        IPage<UserEntity> list = userMapper.selectPage(page, queryWrapper);
        List<UserResult> resultList = generateListDto(list);
        return PageResponseDto.success(resultList, list.getTotal());
    }


    private List<UserResult> generateListDto(IPage<UserEntity> list) {
        List<UserEntity> entityList = list.getRecords();
        if (CollectionUtils.isNotEmpty(entityList)) {
            return entityList.stream().map(userEntity -> {
                UserResult userResult = new UserResult();
                BeanUtils.copyProperties(userEntity, userResult);
                return userResult;
            }).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public ResponseDto add(UserParam userParam) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userParam, userEntity, "password");
        String salt = userKit.getRandomSalt(5);
        String password = userKit.md5(userParam.getPassword(), salt);
        userEntity.setSalt(salt);
        userEntity.setPassword(password);
        userEntity.setStatus(1);
        userEntity.setUpdateTime(new Date());
        userEntity.setCreateTime(new Date());
        userMapper.insert(userEntity);
        return ResponseDto.success();

    }

    @Override
    public ResponseDto update(UserParam userParam) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userParam, userEntity, "password");
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("account", userParam.getAccount());
        userMapper.update(userEntity, updateWrapper);
        return ResponseDto.success();
    }

    @Override
    public ResponseDto updatePassword(UserParam userParam) {
        UserEntity userEntity = new UserEntity();
        String salt = userKit.getRandomSalt(5);
        String password = userKit.md5(userParam.getPassword(), salt);
        userEntity.setSalt(salt);
        userEntity.setPassword(password);
        userEntity.setUpdateTime(new Date());

        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("account", userParam.getAccount());
        userMapper.update(userEntity, updateWrapper);
        return ResponseDto.success();
    }

    @Override
    public ResponseDto remove(UserParam userParam) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userParam, userEntity, "password");
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("account", userParam.getAccount());
        updateWrapper.set("status", 0);
        userEntity.setUpdateTime(new Date());
        userMapper.update(userEntity, updateWrapper);
        return ResponseDto.success();

    }

    @Override
    public ResponseDto detail(UserParam userParam) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", userParam.getAccount());
        queryWrapper.last("limit 1");
        UserEntity userEntity = userMapper.selectOne(queryWrapper);
        UserResult userResult = new UserResult();
        BeanUtils.copyProperties(userEntity, userResult);
        return ResponseDto.success(userResult);

    }

    @Override
    public List<UserEntity> listById(List<Integer> userIdList) {
        if (userIdList.isEmpty()) {
            return new ArrayList<>();
        }
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_id", userIdList);
        return userMapper.selectList(queryWrapper);
    }

}
