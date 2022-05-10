package com.gc.ims.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Description: inventory entity
 * @Author: Guanchen Zhao
 * @Date: 2021/11/21
 */
@Data
@TableName("inventory")
public class InventoryEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    /**
     * name
     */
    @TableField("name")
    private String name;

    /**
     * type
     */
    @TableField("type")
    private String type;


    /**
     * description
     */
    @TableField("description")
    private String description;

    /**
     * status 0-showing; 1-ready  2- repair  3- store
     */
    @TableField("status")
    private Integer status;

    /**
     * description
     */
    @TableField("rfid_no")
    private String rfidNo;

    /**
     * link
     */
    @TableField("location")
    private String location;


    /**
     * salt
     */
    @TableField("is_del")
    private Integer isDel;

    /**
     * createTime
     */
    @TableField(value = "exhibit_time")
    private Date exhibitTime;

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