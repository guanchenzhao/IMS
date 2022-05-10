package com.gc.ims.service;

import com.gc.ims.model.param.NoticeParam;

/**
 * @Description: sms service
 * @Author: Guanchen Zhao
 * @Date: 2021/11/20
 */
public interface SmsService {

    /**
     * sendWelcomeSms
     *
     * @param param
     */
    void sendWelcomeSms(NoticeParam param);


    /**
     * sendAdvanceNoticeSms
     *
     * @param param
     */
    void sendAdvanceNoticeSms(NoticeParam param);

    /**
     * sendNoticeSms
     *
     * @param param
     */
    void sendNoticeSms(NoticeParam param);

    /**
     * appoint notice
     *
     * @param param
     */
    void sendAppointNoticeSms(NoticeParam param);
}
