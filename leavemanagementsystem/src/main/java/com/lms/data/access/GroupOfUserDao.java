package com.lms.data.access;

import com.lms.entity.Group;
import com.lms.entity.GroupOfUser;
import com.lms.entity.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by nuwantha on 12/28/16.
 */
public interface GroupOfUserDao {
    void addUserOfGroup(User user,int groupId);
    List<GroupOfUser> getUsersOfGroup(int groupId);
    boolean deleteUsersOfGroup(int groupid);

}
