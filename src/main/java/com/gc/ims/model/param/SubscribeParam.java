package com.gc.ims.model.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubscribeParam implements Serializable {


    /**
     * userId
     */
    private Integer userId;

    /**
     * inventoryId
     */
    private Integer inventoryId;


}
