package com.gc.ims.service;

import com.gc.ims.model.dto.PageResponseDto;
import com.gc.ims.model.dto.ResponseDto;
import com.gc.ims.model.param.AppointParam;

/**
 * @Description: Appoint service
 * @Author: Guanchen Zhao
 * @Date: 2021/12/1
 */
public interface AppointService {

    PageResponseDto list(AppointParam param, Integer currentPage, Integer pageSize);

    ResponseDto add(AppointParam param);

    ResponseDto cancel(AppointParam param);

    void sendNotice();

}
