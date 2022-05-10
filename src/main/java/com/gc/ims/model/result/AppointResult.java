package com.gc.ims.model.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class AppointResult implements Serializable {


    private Integer id;

    /**
     * status
     */
    private Integer status;

}
