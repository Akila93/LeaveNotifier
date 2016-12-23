package com.lms.shedule;

import com.lms.common.Common;
import com.lms.entity.User;
import com.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by nuwantha on 12/21/16.
 */
@Component
public class Sheduler {

    @Autowired
    UserService userService;
    @Scheduled(cron = " * 0 * * * *")
    public void updateUserAccount(){
        List<User> userList = Common.printUserList();
        userService.updateUserAccount(userList);
    }
}
