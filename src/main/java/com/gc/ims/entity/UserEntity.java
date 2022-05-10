package com.gc.ims.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Description: user entity
 * @Author: Guanchen Zhao
 * @Date: 2021/11/21
 */
@Data
@TableName("user")
public class UserEntity {

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * account
     */
    @TableField("account")
    private String account;

    /**
     * name
     */
    @TableField("name")
    private String name;

    /**
     * password
     */
    @TableField("password")
    private String password;


    /**
     * salt
     */
    @TableField("salt")
    private String salt;


    /**
     * role
     */
    @TableField("role")
    private String role;

    /**
     * email
     */
    @TableField("email")
    private String email;

    /**
     * phone
     */
    @TableField("phone")
    private String phone;


    /**
     * status
     */
    @TableField("status")
    private Integer status;


    /**
     * createTime
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * updateTime
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;
}
