package com.lms.shedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by nuwantha on 12/21/16.
 */
@Component
public class Sheduler {

    @Scheduled(cron = " 0,30 * * * * *")
    public void updateUserAccount(){
        System.out.println("shedula is worked");
    }
}
