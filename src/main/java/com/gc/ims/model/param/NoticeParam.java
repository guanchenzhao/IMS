package com.gc.ims.model.param;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeParam {

    /**
     * name
     */
    private String userName;

    /**
     * inventoryName
     */
    private String inventoryName;

    /**
     * date
     */
    private Date showDate;

    /**
     * email
     */
    private String emailAddress;

    /**
     * phone
     */
    private String phone;

    /**
     * location
     */
    private String location;
}
