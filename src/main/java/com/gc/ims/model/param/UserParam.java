package com.gc.ims.model.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserParam implements Serializable {


    private Integer userId;

    /**
     * account
     */
    private String account;

    /**
     * name
     */
    private String name;

    /**
     * password
     */
    private String password;


    /**
     * role
     */
    private String role;

    /**
     * email
     */
    private String email;

    /**
     * phone
     */
    private String phone;

    /**
     * status
     */
    private Integer status;


}
