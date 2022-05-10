package com.gc.ims.util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;


/**
 * @Description: sms util
 * @Author: Guanchen Zhao
 * @Date: 2021/12/1
 */
@Component
public class SmsUtil {


    private String ACCOUNT_SID = "";
    private String AUTH_TOKEN = "";
    private String serviceSid = "";


    public void sendSms(String number, String body) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(
                new PhoneNumber(number),
                serviceSid,
                body)
                .create();

    }


}

