package com.gc.ims.Task;

import com.gc.ims.service.AppointService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Description: Scheduled Task
 * @Author: Guanchen Zhao
 * @Date: 2021/11/20
 */
@Component
public class ScheduledTask {

    @Resource
    private AppointService appointService;

    /**
     * sendNotice every minute
     */
    @Scheduled(fixedRate = 60000)
    public void sendNotice() {
        System.out.println("schedule ：" + LocalDateTime.now());
        appointService.sendNotice();
    }


}
