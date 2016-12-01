package com.lms.service;

/**
 * Created by nuwantha on 11/24/16.
 */
public interface SecurityService {

    String findLoggedInUsername();
    String autologin(String username,String email);

}
