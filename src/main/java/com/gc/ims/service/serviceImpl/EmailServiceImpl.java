package com.gc.ims.service.serviceImpl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gc.ims.model.param.NoticeParam;
import com.gc.ims.service.EmailService;
import com.gc.ims.util.EmailUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("EmailService")
public class EmailServiceImpl implements EmailService {

    @Resource
    private EmailUtil emailUtil;

    private final String prefix = "[Bonanza] ";

    @Override
    public void sendWelcome(NoticeParam param) {
        String body = prefix + "Dear " + param.getUserName() + ", Welcome to Bonanza, the museum inventory management system.";

        String subject = prefix + "Welcome to Bonanza!";

        String toEmail = param.getEmailAddress();

        sendEmail(subject, body, toEmail);

    }


    @Override
    public void sendAdvanceNotice(NoticeParam param) {

        String subject = prefix + "Collection coming soon!";

        String body = prefix + "Dear " + param.getUserName() + ", The collection you're looking at --" + param.getInventoryName() + "' will be on display on " + param.getShowDate();
        String location = "The location is " + param.getLocation();
        String welcome = "Welcome to visit !";
        body = body + location + welcome;
        String toEmail = param.getEmailAddress();

        sendEmail(subject, body, toEmail);

    }

    @Override
    public void sendNotice(NoticeParam param) {
        String subject = prefix + "Collection coming now!";
        String body = prefix + "Dear " + param.getUserName() + ", The collection you're looking at --" + param.getInventoryName() + "' is displaying now! ";
        String location = "The location is " + param.getLocation();
        String welcome = "Welcome to visit !";
        body = body + location + welcome;
        String toEmail = param.getEmailAddress();
        sendEmail(subject, body, toEmail);

    }


    @Override
    public void sendAppointNotice(NoticeParam param) {
        String subject = prefix + "The appointment time is up!";

        String body = prefix + "Dear " + param.getUserName() + ", You booked a visit with " + param.getInventoryName() + ", It's time to go! ";

        String location = "The location is " + param.getLocation();
        String welcome = "Welcome to visit!";
        body = body + location + welcome;
        String toEmail = param.getEmailAddress();
        sendEmail(subject, body, toEmail);

    }


    private void sendEmail(String subject, String body, String toEmail) {
        if (StringUtils.isBlank(toEmail)) {
            return;
        }
        emailUtil.sendEmail(subject, body, toEmail);
    }
}
