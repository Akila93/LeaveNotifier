package com.lms.entity;

import java.util.Date;

/**
 * Created by nuwantha on 11/10/16.
 */
public class Leave {

    private int userId;
    private Date leaveDate;
    private int leaveType;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public int getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(int leaveType) {
        this.leaveType = leaveType;
    }
}
