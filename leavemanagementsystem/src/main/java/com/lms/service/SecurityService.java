package com.lms.service;

/**
 * Created by nuwantha on 11/24/16.
 */
public interface SecurityService {

    String findLoggedInUsername();
    void autologin(String username,String email);

}
