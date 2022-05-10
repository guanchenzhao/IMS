package com.gc.ims.enums;


public enum InventoryStatusEnums {

    STORE(0, "store"),
    READY(1, "ready"),
    SHOW(2, "show"),
    REPAIR(3, "repair");

    private Integer code;

    private String name;

    InventoryStatusEnums(Integer code, String name) {
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
