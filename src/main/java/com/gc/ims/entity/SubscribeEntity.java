package com.gc.ims.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Description: subscribe entity
 * @Author: Guanchen Zhao
 * @Date: 2021/11/21
 */
@Data
@TableName("subscribe")
public class SubscribeEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * userId
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * inventoryId
     */
    @TableField("inventory_id")
    private Integer inventoryId;

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