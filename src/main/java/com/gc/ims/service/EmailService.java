package com.gc.ims.service;

import com.gc.ims.model.param.NoticeParam;

/**
 * @Description: email service
 * @Author: Guanchen Zhao
 * @Date: 2021/11/20
 */
public interface EmailService {

    /**
     * sendWelcomeSms
     *
     * @param param
     */
    void sendWelcome(NoticeParam param);


    /**
     * sendAdvanceNoticeSms
     *
     * @param param
     */
    void sendAdvanceNotice(NoticeParam param);

    /**
     * sendNoticeSms
     *
     * @param param
     */
    void sendNotice(NoticeParam param);

    void sendAppointNotice(NoticeParam param);


}
