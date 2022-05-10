package com.gc.ims.service;

import com.gc.ims.model.dto.PageResponseDto;
import com.gc.ims.model.dto.ResponseDto;
import com.gc.ims.model.param.InventoryParam;

/**
 * @Description: Inventory service
 * @Author: Guanchen Zhao
 * @Date: 2021/12/1
 */
public interface InventoryService {

    /**
     * add
     *
     * @param
     * @return
     */
    ResponseDto add(InventoryParam param);

    /**
     * update
     *
     * @param
     * @return
     */
    ResponseDto update(InventoryParam param);

    /**
     * remove
     *
     * @param
     * @return
     */
    ResponseDto remove(InventoryParam param);

    /**
     * list all
     *
     * @param
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageResponseDto pageList(InventoryParam param, Integer currentPage, Integer pageSize);


    ResponseDto list(InventoryParam param);

    /**
     * get   detail
     *
     * @param
     * @return
     */
    ResponseDto detail(InventoryParam param);

}
