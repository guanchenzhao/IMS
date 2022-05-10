package com.gc.ims.model.result;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class InventoryResult implements Serializable {


    private Integer id;

    /**
     * status  0-showing; 1-ready  2- repair  3- store
     */
    private Integer status;

    /**
     * rfidNo
     */
    private String rfidNo;

    /**
     * location
     */
    private String location;

    /**
     * exhibitTime
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date exhibitTime;

    /**
     * name
     */
    private String name;

    /**
     * type
     */
    private String type;

    /**
     * description
     */
    private String description;

    /**
     * is subscribed
     */
    private Boolean subscribed;

}
