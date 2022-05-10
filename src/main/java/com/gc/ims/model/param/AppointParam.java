package com.gc.ims.model.param;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class AppointParam implements Serializable {
    private Integer id;

    private Integer userId;

    private String  userName;

    private String  inventoryName;
    /**
     * 0- normal ; 1-cancel; 2-finished
     */
    private Integer status;


    private Integer inventoryId;

    /**
     * exhibitTime
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date planDate;



}
