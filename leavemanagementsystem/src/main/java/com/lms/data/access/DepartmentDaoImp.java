package com.lms.data.access;

import com.lms.entity.Department;
import com.lms.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by nuwantha on 12/15/16.
 */
@Repository
public class DepartmentDaoImp extends JdbcDaoSupport implements DepartmentDao {

    @Autowired
    DataSource dataSource;


    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }


    @Override
    public List<Department> getAllDepartment() {
        String sql = "select * from department";
        List<Department> departmentList = getJdbcTemplate().query(sql, new DepartmentMapper());
        return departmentList;
    }

    @Override
    public Department getDepartment(int depId) {
        String sql = "select * from department where dep_Id=?";
        Department department = (Department) getJdbcTemplate().queryForObject(sql, new Object[]{depId}, new DepartmentMapper());


        return department;

    }
}
