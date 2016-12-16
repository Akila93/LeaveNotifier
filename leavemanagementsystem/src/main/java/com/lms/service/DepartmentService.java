package com.lms.service;

import com.lms.data.access.DepartmentDao;
import com.lms.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nuwantha on 12/15/16.
 */

@Service
public class DepartmentService {
    @Autowired
    DepartmentDao departmentDao;

    public List<Department> getAllDepartment(){
        return departmentDao.getAllDepartment();
    }

    public Department getDepartment(int depId){
        System.out.println("dep id is "+depId);
        return departmentDao.getDepartment(depId);
    }
}
