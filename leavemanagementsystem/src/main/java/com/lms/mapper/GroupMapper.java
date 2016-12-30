package com.lms.mapper;

import com.lms.entity.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nuwantha on 12/28/16.
 */
public class GroupMapper implements RowMapper{

    @Override
    public Group mapRow(ResultSet resultSet, int i) throws SQLException {
        Group group = new Group();
        group.setGroupId(resultSet.getInt("groupid"));
        group.setGroupLederId(resultSet.getInt("grouplederid"));
        group.setGroupName(resultSet.getString("groupname"));
        return group;
    }
}
