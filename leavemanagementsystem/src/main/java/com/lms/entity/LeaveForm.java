package com.lms.entity;

import java.util.Date;

/**
 * Created by akila on 11/11/16.
 */
public class LeaveForm implements Form{
    private String name;
    private String leaveDate;
    private String leaveType;
    private String reasonToLeave;
    private String comment;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Override
    public String toString(){
        return (this.getName() +":"+ this.getLeaveDate() +":"+ this.getLeaveType());
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
}
