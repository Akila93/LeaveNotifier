package com.lms.service;
import com.lms.data.access.GroupDao;
import com.lms.data.access.GroupOfUserDao;
import com.lms.data.access.UserDao;
import com.lms.entity.Group;
import com.lms.entity.GroupOfUser;
import com.lms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nuwantha on 12/28/16.
 */
@Service
public class GroupService {

    @Autowired
    GroupDao groupDao;

    @Autowired
    GroupOfUserDao groupOfUserDao;

    @Autowired
    UserDao userDao;

    public List<Group> getGroups(int groupLeaderId){
        List<Group> groupList = groupDao.getGroup(groupLeaderId);
        return groupList;
    }


    public List<User> getUsersOfGroup(int groupId){
        List<GroupOfUser> usersOfGroup = groupOfUserDao.getUsersOfGroup(groupId);
        List<User> userList=new ArrayList<>();
        for (GroupOfUser groupOfUser: usersOfGroup) {
            User userById = userDao.getUserById(groupOfUser.getUserId());
            userList.add(userById);
        }
        return userList;

    }

    public Group getGroup(String groupName){
        Group group = groupDao.getGroup(groupName);
        return group;
    }

    @Transactional
    public void addNewGroup(Group group,List<GroupOfUser> groupOfUsers){

        groupDao.createGroup(group);

        System.out.println("group is added 1");
        GroupOfUser groupOfUser = new GroupOfUser();
        Group group1 = getGroup(group.getGroupName());
        System.out.println(group1.getGroupId());
        groupOfUser.setGroupId(group1.getGroupId());
        groupOfUser.setUserId(group.getGroupLederId());
        groupOfUsers.add(groupOfUser);

        for (GroupOfUser groupOfUser1: groupOfUsers) {
            User user = userDao.getUserById(groupOfUser1.getUserId());
            groupOfUserDao.addUserOfGroup(user,groupOfUser1.getGroupId());
        }

    }
    public boolean deleteGroup(String groupId){

        boolean isdelete = groupDao.deleteGroup(groupId);
        return isdelete;

    }
    @Transactional
    public void updateGroup(Group group,List<User> userList){

        for (User user: userList) {


        }

    }
}
