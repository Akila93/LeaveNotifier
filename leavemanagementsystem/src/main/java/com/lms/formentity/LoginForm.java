package com.lms.formentity;

import java.util.Date;

/**
 * Created by akila on 11/17/16.
 */
public class LoginForm implements Form{
    private String name;
    private String password;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }
}
