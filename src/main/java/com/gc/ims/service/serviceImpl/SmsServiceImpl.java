package com.gc.ims.service.serviceImpl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gc.ims.model.param.NoticeParam;
import com.gc.ims.service.SmsService;
import com.gc.ims.util.SmsUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("SmsService")
public class SmsServiceImpl implements SmsService {

    @Resource
    private SmsUtil smsUtil;

    private final String prefix = "[Bonanza] ";

    @Override
    public void sendWelcomeSms(NoticeParam param) {
        String body = prefix + "Dear " + param.getUserName() + ", Welcome to Bonanza, the museum inventory management system.";
        sendSms(param.getPhone(), body);
    }


    @Override
    public void sendAdvanceNoticeSms(NoticeParam param) {
        String body = prefix + "Dear " + param.getUserName() + ", The collection you're looking at --" + param.getInventoryName() + "' will be on display on " + param.getShowDate();
        String location = "The location is " + param.getLocation();
        String welcome = "Welcome to visit !";
        body = body + location + welcome;
        sendSms(param.getPhone(), body);

    }

    @Override
    public void sendNoticeSms(NoticeParam param) {
        String body = prefix + "Dear " + param.getUserName() + ", The collection you're looking at --" + param.getInventoryName() + "' is displaying now! ";
        String location = "The location is " + param.getLocation();
        String welcome = "Welcome to visit !";
        body = body + location + welcome;
        sendSms(param.getPhone(), body);
    }

    @Override
    public void sendAppointNoticeSms(NoticeParam param) {
        String body = prefix + "Dear " + param.getUserName() + ", You booked a visit with " + param.getInventoryName() + ", It's time to go! ";

        String location = "The location is " + param.getLocation();
        String welcome = "Welcome to visit!";
        body = body + location + welcome;
        sendSms(param.getPhone(), body);
    }


    private void sendSms(String number, String body) {
        if (StringUtils.isBlank(number)) {
            return;
        }
        smsUtil.sendSms(number, body);
    }
}
