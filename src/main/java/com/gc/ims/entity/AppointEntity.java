package com.gc.ims.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Description: appointment entity
 * @Author: Guanchen Zhao
 * @Date: 2021/11/21
 */
@Data
@TableName("appointment")
public class AppointEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 0- normal ; 1-cancel; 2-finished
     */
    @TableField("status")
    private Integer status;

    /**
     * userId
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * userId
     */
    @TableField("inventory_id")
    private Integer inventoryId;

    /**
     * plan_date
     */
    @TableField(value = "plan_date")
    private Date planDate;

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