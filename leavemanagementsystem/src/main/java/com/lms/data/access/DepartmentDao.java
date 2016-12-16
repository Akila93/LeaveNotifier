package com.lms.data.access;

import com.lms.entity.Department;

import java.util.List;

/**
 * Created by nuwantha on 12/15/16.
 */
public interface DepartmentDao {
    List<Department> getAllDepartment();
    Department getDepartment(int depId);
}
