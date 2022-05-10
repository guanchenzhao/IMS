package com.gc.ims.model.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubscribeResult implements Serializable {

    /**
     * userId
     */
    private Integer userId;

    /**
     * inventoryId
     */
    private Integer inventoryId;

}
