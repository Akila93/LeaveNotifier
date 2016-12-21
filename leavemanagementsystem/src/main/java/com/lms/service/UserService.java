package com.lms.service;

import com.lms.data.access.UserDao;
import com.lms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nuwantha on 11/10/16.
 */

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void createUserAccount(User user) {
        userDao.createUserAccount(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    public int getUserCount() {
        return userDao.getUserCount();
    }

    public User getUserByEmail(String email) throws EmptyResultDataAccessException {
        return userDao.getUserByEmail(email);
    }

    public User getUserByName(String userName) {

        return userDao.getUserByName(userName);

    }

    public boolean isUserHasAccount(String userName){
        return userDao.isUserHasAccount(userName);
    }

    public List<User> getUserHasNotLeaveToday(){
        return userDao.getUserHasNotLeaveToday();
    }

    @Transactional
    public boolean updateUserAccount(List<User> userList){
        for (User user :userList) {
            String email = user.getEmail();
            boolean userHasAccountByEmail = userDao.isUserHasAccountByEmail(email);
            if(!userHasAccountByEmail){
                userDao.createUserAccount(user);
            }
        }
        return true;
    }

}
