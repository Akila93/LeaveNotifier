package com.lms.service;

import com.lms.data.access.UserDao;
import com.lms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nuwantha on 11/10/16.
 */

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public void addNewUser(User user){
        userDao.addNewUser(user);
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }
}
