package com.lms.mapper;

import com.lms.entity.Leave;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nuwantha on 11/11/16.
 */
public class LeaveMapper implements RowMapper {
    @Override
    public Leave mapRow(ResultSet resultSet, int i) throws SQLException {
        Leave leave = new Leave();
        leave.setUserId(resultSet.getInt("userid"));
        leave.setLeaveDate(resultSet.getString("leavedate"));
        leave.setLeaveType(resultSet.getString("leavetype"));
        leave.setReasonToLeave(resultSet.getString("reason"));
        leave.setComment(resultSet.getString("comment"));
        return leave;
    }
}
