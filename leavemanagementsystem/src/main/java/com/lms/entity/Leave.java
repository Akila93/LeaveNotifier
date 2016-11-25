package com.lms.entity;

import java.util.Date;

/**
 * Created by nuwantha on 11/10/16.
 */
public class Leave {


    private int userId;
    private String name;
    private String leaveDate;
    private String leaveType;
    private String reasonToLeave;
    private String comment;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReasonToLeave() {
        return reasonToLeave;
    }

    public void setReasonToLeave(String reasonToLeave) {
        this.reasonToLeave = reasonToLeave;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }
}
