package com.gc.ims.controller;

import com.gc.ims.model.dto.PageResponseDto;
import com.gc.ims.model.dto.ResponseDto;
import com.gc.ims.model.param.AppointParam;
import com.gc.ims.service.AppointService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

 /**
  * @Description: appointment service
  * @Author: Guanchen Zhao
  * @Date: 2021/12/8
  */
@Controller
@CrossOrigin
@RequestMapping("/appoint")
public class AppointController {

    @Resource
    private AppointService appointService;


    @ResponseBody
    @RequestMapping("/list")
    public PageResponseDto pageList(AppointParam param, Integer currentPage, Integer pageSize) {
        if (null == currentPage || null == pageSize) {
            return PageResponseDto.error("currentPage and pageSize are required");
        }

        return appointService.list(param, currentPage, pageSize);
    }

    @ResponseBody
    @RequestMapping("/add")
    public ResponseDto add(AppointParam param) {
        if (null == param.getInventoryId()) {
            return ResponseDto.error("InventoryId is required");
        }
        if (null == param.getPlanDate()) {
            return ResponseDto.error("plan date is required");
        }
        return appointService.add(param);
    }

    @ResponseBody
    @RequestMapping("/cancel")
    public ResponseDto cancel(AppointParam param) {
        if (null == param.getId()) {
            return ResponseDto.error("Id is required");
        }
        return appointService.cancel(param);
    }

}
