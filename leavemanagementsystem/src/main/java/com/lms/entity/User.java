package com.lms.entity;

import org.springframework.stereotype.Service;

/**
 * Created by nuwantha on 11/10/16.
 */
@Service
public class User {

    private int userId;
    private String userName;
    private String email;
    private String role;
    private String depId;

    public User(){

    }

    public User(int userId, String userName, String email, String role) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.role= role;
    }

    public User(int userId, String userName, String email, String role, String depId) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.depId = depId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    @Override
    public String toString() {
        return "User [userid=" + userId + ", username=" + userName+ "]";
    }

}
