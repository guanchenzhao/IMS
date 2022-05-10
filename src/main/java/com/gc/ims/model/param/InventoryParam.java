package com.gc.ims.model.param;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class InventoryParam implements Serializable {


    private Integer id;

    /**
     * status  0-store ; 1-ready  2-showing   3- repair
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
     * link
     */
    private String link;


}
