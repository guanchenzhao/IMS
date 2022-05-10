package com.gc.ims.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AppointVo implements Serializable {

    private Integer id;

    /**
     * userId
     */
    private Integer userId;

    /**
     * userId
     */
    private Integer status;

    /**
     * userName
     */
    private String userName;

    /**
     * userId
     */
    private Integer inventoryId;

    /**
     * inventoryName
     */
    private String inventoryName;

    /**
     * plan_date
     */
    private Date planDate;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateTime
     */
    private Date updateTime;


}
