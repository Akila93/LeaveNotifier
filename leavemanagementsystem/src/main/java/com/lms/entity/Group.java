package com.lms.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by nuwantha on 12/28/16.
 */
@XmlRootElement
public class Group {
    private int groupId;
    private String groupName;
    private int groupLederId;

    public Group(){

    }

    public Group(int groupId, String groupName, int groupLederId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupLederId = groupLederId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupLederId() {
        return groupLederId;
    }

    public void setGroupLederId(int groupLederId) {
        this.groupLederId = groupLederId;
    }
}
