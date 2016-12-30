package com.lms.data.access;

import com.lms.entity.Leave;
import com.lms.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by nuwantha on 11/11/16.
 */
public interface LeaveDao {
    void addNewLeave(Leave leave) throws SQLException;
    List<Leave> getAllLeaves();
    List<Leave> getLeavesOfDay(String date);

    List<Leave> getLeavesOfDay(String date,int projectLeaderId);
    List<Leave> getTodayLeaves();
    List<Leave> getTodayLeaves(int depId);
    int getLeaveCount(int userId,int month,int year);
    List<Leave> getLeavesOfYear(int userid,int year);
    int getLeaveCount(int month,int year,String leaveType);
    int isUserLeave(int userId,int month,int year,int day);
    boolean isUserHasTodayLeave(String username);
    boolean isUserHasLeave(String username,String date);
    int getLeaveCount(int userId,int month,int year,String leaveType);



//    void addNewLeaves(LeaveList)

}
