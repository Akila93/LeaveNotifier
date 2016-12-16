package com.lms.mapper;

import com.lms.entity.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nuwantha on 12/14/16.
 */
public class DepartmentMapper implements RowMapper {
    @Override
    public Department mapRow(ResultSet resultSet, int i) throws SQLException {
        Department department = new Department();
        department.setDepID(resultSet.getInt("dep_id"));
        department.setDepName(resultSet.getString("dep_name"));
        return department;
    }
}
