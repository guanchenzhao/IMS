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


    private String ACCOUNT_SID = "AC8bd5bbc65ae0400f0520b6fcfa7d1ec2";
    private String AUTH_TOKEN = "412a5413b424a44f8d542e5d82d2d82f";
    private String serviceSid = "MG61f8806e554694db184603bd973ac7db";


    public void sendSms(String number, String body) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(
                new PhoneNumber(number),
                serviceSid,
                body)
                .create();

    }


}

