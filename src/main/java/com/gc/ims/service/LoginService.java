package com.gc.ims.service;

import com.gc.ims.model.dto.ResponseDto;
import com.gc.ims.model.param.UserParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: login service
 * @Author: Guanchen Zhao
 * @Date: 2021/11/20
 */
public interface LoginService {

    /**
     * login
     *
     * @param request
     * @param response
     * @param userParam
     * @return
     */
    ResponseDto login(HttpServletRequest request, HttpServletResponse response, UserParam userParam);

    /**
     * register
     *
     * @param userParam
     * @return
     */
    ResponseDto register(UserParam userParam);

    /**
     * logout
     *
     * @param request
     * @return
     */
    ResponseDto logout(HttpServletRequest request);


}
