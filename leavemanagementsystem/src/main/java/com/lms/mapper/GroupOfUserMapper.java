package com.lms.mapper;

import com.lms.entity.GroupOfUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nuwantha on 12/28/16.
 */

public class GroupOfUserMapper implements RowMapper {

    @Override
    public GroupOfUser mapRow(ResultSet resultSet, int i) throws SQLException {
        GroupOfUser groupOfUser = new GroupOfUser();
        groupOfUser.setUserId(resultSet.getInt("userid"));
        groupOfUser.setGroupId(resultSet.getInt("groupid"));
        return groupOfUser;
    }
}
