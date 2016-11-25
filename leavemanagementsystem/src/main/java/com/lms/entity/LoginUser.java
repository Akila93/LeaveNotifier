package com.lms.entity;

/**
 * Created by nuwantha on 11/21/16.
 */
public class LoginUser {
    private String username;
    private String email;

    public LoginUser() {


    }

    public LoginUser( String username, String password, String email) {

        this.username = username;
        this.email = email;

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
