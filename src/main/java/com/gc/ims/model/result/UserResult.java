package com.gc.ims.model.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResult implements Serializable {

    /**
     * account
     */
    private String account;

    /**
     * name
     */
    private String name;

    /**
     * role
     */
    private String role;

    /**
     * email
     */
    private String email;

    /**
     * status
     */
    private Integer status;

    /**
     * phone
     */
    private String phone;

    /**
     * phone
     */
    private String token;

}
