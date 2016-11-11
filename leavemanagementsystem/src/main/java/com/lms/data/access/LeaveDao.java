package com.lms.data.access;

import com.lms.entity.Leave;

import java.util.List;

/**
 * Created by nuwantha on 11/11/16.
 */
public interface LeaveDao {
    void addNewLeave(Leave leave);
    List<Leave> getAllLeaves();
    List<Leave> getLeavesOfDay(String date);
}
