package com.gc.ims.util;

import com.alibaba.fastjson.JSONObject;
import com.gc.ims.core.consts.Constant;
import com.gc.ims.entity.UserEntity;
import com.gc.ims.enums.RoleEnums;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserKit {

    /**
     * 加盐参数
     */
    public final static String hashAlgorithmName = "MD5";

    /**
     * 循环次数
     */
    public final static int hashIterations = 2;

    @Resource
    private RedisUtil redisUtils;


    /**
     * md5
     *
     * @param credentials 密码
     * @param saltSource  密码盐
     * @return
     */
    public String md5(String credentials, String saltSource) {
        ByteSource salt = new Md5Hash(saltSource);
        return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toString();
    }

    /**
     * random salt
     *
     * @param length
     * @return
     */
    public String getRandomSalt(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }


    public UserEntity getCurrentUser() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader(Constant.AUTH_HEADER);
        if (StringUtils.isNotBlank(token)) {
            String userStr = (String) redisUtils.get(token);
            if (StringUtils.isNotBlank(userStr)) {
                return JSONObject.parseObject(userStr, UserEntity.class);
            }
        }
        return null;
    }


    public Boolean isAdmin() {
        UserEntity userEntity = getCurrentUser();
        return RoleEnums.ADMIN.getCode().equals(userEntity.getRole());
    }

    /**
     * get current user by token
     *
     * @return
     */
    public Integer getCurrentUserId() {
        UserEntity userEntity = getCurrentUser();
        return userEntity.getUserId();
    }

}

