package com.gc.ims.controller;

import com.gc.ims.model.dto.PageResponseDto;
import com.gc.ims.model.dto.ResponseDto;
import com.gc.ims.model.param.InventoryParam;
import com.gc.ims.model.param.SubscribeParam;
import com.gc.ims.service.SubscribeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: subscribe service
 * @Author: Guanchen Zhao
 * @Date: 2021/11/20
 */
@CrossOrigin
@Controller
@RequestMapping("/subscribe")
public class SubscribeController {

    @Resource
    private SubscribeService subscribeService;

    /**
     * add subscribe
     *
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public ResponseDto add(SubscribeParam param) {
        if (null == param.getInventoryId()) {
            return ResponseDto.error("InventoryId is required");
        }
        return subscribeService.add(param);
    }

    /**
     * cancel a subscription
     *
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/cancel")
    public ResponseDto cancel(SubscribeParam param) {
        if (null == param.getInventoryId()) {
            return ResponseDto.error("InventoryId is required");
        }
        return subscribeService.cancel(param);
    }

    /**
     * show subscription list
     *
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public PageResponseDto pageList(InventoryParam param, Integer currentPage, Integer pageSize) {
        if (null == currentPage || null == pageSize) {
            return PageResponseDto.error("currentPage and pageSize are required");
        }

        return subscribeService.list(param, currentPage, pageSize);
    }

}
