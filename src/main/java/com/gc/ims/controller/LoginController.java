package com.gc.ims.controller;

import com.gc.ims.model.dto.ResponseDto;
import com.gc.ims.model.param.UserParam;
import com.gc.ims.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: login service
 * @Author: Guanchen Zhao
 * @Date: 2021/11/28
 */
@CrossOrigin
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;


    /**
     * login
     *
     * @param request
     * @param response
     * @param userParam
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public ResponseDto login(HttpServletRequest request, HttpServletResponse response, UserParam userParam) {
        return loginService.login(request, response, userParam);
    }

    /**
     * register
     *
     * @param userParam
     * @return
     */
    @ResponseBody
    @RequestMapping("/register")
    public ResponseDto register(UserParam userParam) {
        return loginService.register(userParam);
    }

    /**
     * logout
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/logout")
    public ResponseDto logout(HttpServletRequest request) {
        return loginService.logout(request);
    }


}
