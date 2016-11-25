package com.lms.data.access;

import com.lms.entity.LoginUser;

/**
 * Created by nuwantha on 11/23/16.
 */
public interface LoginDao {
    LoginUser getLoginUserByEmail(String email);
    LoginUser getLoginUserByUserName(String userName);
    void createUserAccount(LoginUser loginUser);
}
