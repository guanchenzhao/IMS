package com.gc.ims.enums;


public enum AppointStatusEnums {

    NORMAL(0, "normal"),
    CANCEL(1, "cancelled"),
    FINISH(2, "finished");

    private Integer code;

    private String name;

    AppointStatusEnums(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
