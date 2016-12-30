package com.lms.data.access;

import com.lms.entity.Group;

import java.util.List;

/**
 * Created by nuwantha on 12/28/16.
 */
public interface GroupDao {

    void createGroup(Group group);
    List<Group> getAllGroup();
    List<Group> getGroup(int groupLeaderId);
    Group getGroup(String groupName);
    boolean deleteGroup(String groupId);

}
