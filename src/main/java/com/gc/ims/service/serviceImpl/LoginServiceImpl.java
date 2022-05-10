package com.gc.ims.service.serviceImpl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gc.ims.entity.UserEntity;
import com.gc.ims.enums.RoleEnums;
import com.gc.ims.mapper.UserMapper;
import com.gc.ims.model.dto.ResponseDto;
import com.gc.ims.model.param.NoticeParam;
import com.gc.ims.model.param.UserParam;
import com.gc.ims.model.result.UserResult;
import com.gc.ims.service.EmailService;
import com.gc.ims.service.LoginService;
import com.gc.ims.service.SmsService;
import com.gc.ims.util.RedisUtil;
import com.gc.ims.util.UserKit;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private EmailService emailService;

    @Resource
    private SmsService smsService;

    @Resource
    private UserKit userKit;


    @Override
    public ResponseDto login(HttpServletRequest request, HttpServletResponse response, UserParam userParam) {
        String account = userParam.getAccount();
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        queryWrapper.eq("status", 1);
        List<UserEntity> list = userMapper.selectList(queryWrapper);

        if (list.size() == 0) {
            return ResponseDto.error("account is not exist");
        }
        UserEntity userEntity = list.get(0);

        String salt = userEntity.getSalt();
        String password = userKit.md5(userParam.getPassword(), salt);

        String realPassword = userEntity.getPassword();
        if (!password.equals(realPassword)) {
            return ResponseDto.error("password is not correct");
        }

        String token = "TCSS559 " + userKit.getRandomSalt(16);
        redisUtil.set(token, JSONObject.toJSONString(userEntity), 24 * 60 * 60L);
        response.setHeader("Authorization", token);

        UserResult userResult = new UserResult();
        userResult.setToken(token);
        BeanUtils.copyProperties(userEntity, userResult);
        return ResponseDto.success(userResult);

    }

    @Override
    public ResponseDto register(UserParam userParam) {
        String account = userParam.getAccount();
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        queryWrapper.eq("status", 1);
        List<UserEntity> list = userMapper.selectList(queryWrapper);

        if (list.size() > 0) {
            return ResponseDto.error("account is already exist");
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userParam, userEntity, "password");
        String salt = userKit.getRandomSalt(5);
        String password = userKit.md5(userParam.getPassword(), salt);
        userEntity.setSalt(salt);
        userEntity.setRole(RoleEnums.NORMAL.getCode());
        userEntity.setPassword(password);
        userEntity.setStatus(1);
        userMapper.insert(userEntity);

        sendNotice(userEntity);

        return ResponseDto.success();

    }

    @Override
    public ResponseDto logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        redisUtil.remove(token);
        return ResponseDto.success();
    }


    /**
     * send notice via sms and email
     *
     * @param userEntity
     */
    private void sendNotice(UserEntity userEntity) {
        NoticeParam param = new NoticeParam();
        param.setEmailAddress(userEntity.getEmail());
        param.setPhone(userEntity.getPhone());
        param.setUserName(userEntity.getName());
        smsService.sendWelcomeSms(param);
        emailService.sendWelcome(param);
    }
}
