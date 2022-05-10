package com.gc.ims.service;

import com.gc.ims.model.dto.PageResponseDto;
import com.gc.ims.model.dto.ResponseDto;
import com.gc.ims.model.param.InventoryParam;
import com.gc.ims.model.param.SubscribeParam;

import java.util.List;

/**
 * @Description: Subscribe service
 * @Author: Guanchen Zhao
 * @Date: 2021/12/1
 */
public interface SubscribeService {

    /**
     * list
     *
     * @param param
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageResponseDto list(InventoryParam param, Integer currentPage, Integer pageSize);

    /**
     * add
     *
     * @param param
     * @return
     */
    ResponseDto add(SubscribeParam param);

    /**
     * cancel
     *
     * @param param
     * @return
     */
    ResponseDto cancel(SubscribeParam param);

    /**
     * get user list
     *
     * @param inventoryId
     * @return
     */
    List<Integer> listUserByInventory(Integer inventoryId);


}
