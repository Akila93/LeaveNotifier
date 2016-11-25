package com.lms.data.access;

import com.lms.entity.User;
import java.util.List;

/**
 * Created by nuwantha on 11/10/16.
 */
public interface UserDao {

    void addNewUser(User user);
    List<User> getAllUsers();
    User getUserByName(String name);
    User getUserById(int id);
    int getUserCount();
}
