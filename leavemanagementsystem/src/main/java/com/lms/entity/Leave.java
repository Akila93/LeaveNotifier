package com.lms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by nuwantha on 11/10/16.
 */
@XmlRootElement
public class Leave {


    private int userId;
    private String name;
    private String leaveDate;
    private String leaveType;
    private String reasonToLeave;
    private String comment;

    @JsonProperty
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getReasonToLeave() {
        return reasonToLeave;
    }

    public void setReasonToLeave(String reasonToLeave) {
        this.reasonToLeave = reasonToLeave;
    }

    @JsonProperty
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonProperty
    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    @JsonProperty
    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }
}
