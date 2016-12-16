package com.lms.data.access;

import com.lms.entity.User;
import java.util.List;

/**
 * Created by nuwantha on 11/10/16.
 */
public interface UserDao {

    void createUserAccount(User user);
    List getAllUsers();
    User getUserByName(String name);
    User getUserById(int id);
    User getUserByEmail(String email);
    int getUserCount();
    boolean isUserHasAccount(String userName);
    List getUserHasNotLeaveToday();


}
