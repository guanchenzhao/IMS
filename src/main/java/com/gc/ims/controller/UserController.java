package com.gc.ims.controller;

import com.gc.ims.model.dto.PageResponseDto;
import com.gc.ims.model.dto.ResponseDto;
import com.gc.ims.model.param.UserParam;
import com.gc.ims.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description: user service
 * @Author: Guanchen Zhao
 * @Date: 2021/11/20
 */
@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * user page list
     *
     * @param userParam
     * @param currentPage
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/pageList")
    public PageResponseDto pageList(UserParam userParam, Integer currentPage, Integer pageSize) {
        if (null == currentPage || null == pageSize) {
            return PageResponseDto.error("currentPage and pageSize are required");
        }
        return userService.list(userParam, currentPage, pageSize);
    }

    /**
     * add user
     *
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public ResponseDto add(UserParam param) {
        if (null == param.getName()) {
            return ResponseDto.error("name is required");
        }
        return userService.add(param);
    }

    /**
     * update user
     *
     * @param userParam
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResponseDto update(UserParam userParam) {
        return userService.update(userParam);
    }

    /**
     * remove user
     *
     * @param userParam
     * @return
     */
    @ResponseBody
    @RequestMapping("/remove")
    public ResponseDto remove(UserParam userParam) {
        if (null == userParam.getAccount()) {
            return ResponseDto.error("account is required");
        }
        return userService.remove(userParam);
    }

    /**
     * get user detail
     *
     * @param userParam
     * @return
     */
    @ResponseBody
    @RequestMapping("/detail")
    public ResponseDto detail(UserParam userParam) {
        if (null == userParam.getAccount()) {
            return ResponseDto.error("account is required");
        }
        return userService.detail(userParam);
    }


    /**
     * update password
     *
     * @param userParam
     * @return
     */
    @ResponseBody
    @RequestMapping("/updatePassword")
    public ResponseDto updatePassword(UserParam userParam) {
        if (null == userParam.getAccount()) {
            return ResponseDto.error("account is required");
        }
        return userService.updatePassword(userParam);
    }
}
