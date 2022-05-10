package com.gc.ims.controller;

import com.gc.ims.model.dto.PageResponseDto;
import com.gc.ims.model.dto.ResponseDto;
import com.gc.ims.model.param.InventoryParam;
import com.gc.ims.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: inventory service
 * @Author: Guanchen Zhao
 * @Date: 2021/11/20
 */
@CrossOrigin
@Controller
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    /**
     * list by page
     *
     * @param param
     * @param currentPage
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/pageList")
    public PageResponseDto pageList(InventoryParam param, Integer currentPage, Integer pageSize) {
        if (null == currentPage || null == pageSize) {
            return PageResponseDto.error("currentPage and pageSize are required");
        }
        return inventoryService.pageList(param, currentPage, pageSize);
    }

    /**
     * list, not page
     *
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public ResponseDto list(InventoryParam param) {
        return inventoryService.list(param);
    }

    /**
     * add inventory
     *
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public ResponseDto add(InventoryParam param) {
        if (null == param.getName()) {
            return ResponseDto.error("name is required");
        }
        return inventoryService.add(param);
    }

    /**
     * update inventory
     *
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResponseDto update(InventoryParam param) {
        if (null == param.getId()) {
            return ResponseDto.error("id is required");
        }
        return inventoryService.update(param);
    }

    /**
     * remove inventory
     *
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/remove")
    public ResponseDto remove(InventoryParam param) {
        if (null == param.getId()) {
            return ResponseDto.error("id is required");
        }
        return inventoryService.remove(param);
    }

    /**
     * get inventory detail
     *
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/detail")
    public ResponseDto detail(InventoryParam param) {
        if (null == param.getId()) {
            return ResponseDto.error("id is required");
        }
        return inventoryService.detail(param);
    }

}
