package com.lms.entity;

/**
 * Created by nuwantha on 12/28/16.
 */
public class GroupOfUser {
    private int groupId;
    private int userId;

    public GroupOfUser(){

    }

    public GroupOfUser(int groupId, int userId) {
        this.groupId = groupId;
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
