package com.gc.ims.service;

import com.gc.ims.entity.UserEntity;
import com.gc.ims.model.dto.PageResponseDto;
import com.gc.ims.model.dto.ResponseDto;
import com.gc.ims.model.param.UserParam;

import java.util.List;

/**
 * @Description: user service
 * @Author: Guanchen Zhao
 * @Date: 2021/11/20
 */
public interface UserService {

    /**
     * add user
     *
     * @param
     * @return
     */
    ResponseDto add(UserParam userParam);

    /**
     * update user
     *
     * @param
     * @return
     */
    ResponseDto update(UserParam userParam);

    /**
     * remove user
     *
     * @param
     * @return
     */
    ResponseDto remove(UserParam userParam);

    /**
     * list all user
     *
     * @param userParam
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageResponseDto list(UserParam userParam, Integer currentPage, Integer pageSize);

    /**
     * get user detail
     *
     * @param
     * @return
     */
    ResponseDto detail(UserParam userParam);

    ResponseDto updatePassword(UserParam userParam);

    List<UserEntity> listById(List<Integer> userIdList);
}
