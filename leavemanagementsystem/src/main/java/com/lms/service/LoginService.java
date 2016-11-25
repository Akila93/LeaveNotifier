package com.lms.service;

import com.lms.data.access.LoginDao;
import com.lms.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nuwantha on 11/23/16.
 */

@Service
public class LoginService {
    @Autowired
    LoginDao loginDao;

    public LoginUser getLoginUserByEmail(String email){
        return loginDao.getLoginUserByEmail(email);
    }


    public LoginUser getLoginUserByUserName(String userName){

        return loginDao.getLoginUserByUserName(userName);

    }

    public void createUserAccount(LoginUser loginUser){
//        System.out.println(loginUser.getUsername()+" "+loginUser.getEmail()+" "+loginUser.getPassword());
        loginDao.createUserAccount(loginUser);
    }


}
