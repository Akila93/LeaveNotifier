package com.lms.entity;

import org.springframework.stereotype.Service;

/**
 * Created by nuwantha on 11/10/16.
 */
@Service
public class User {

    private int userId;
    private String userName;

    public User(){

    }

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public String toString() {
        return "User [userid=" + userId + ", username=" + userName+ "]";
    }


}
