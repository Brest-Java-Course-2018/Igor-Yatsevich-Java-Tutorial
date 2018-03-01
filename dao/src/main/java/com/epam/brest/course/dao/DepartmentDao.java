package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;

import java.util.List;

/**
 * Department DAO interface.
 */
public interface DepartmentDao {

    List<Department> getDepartments();

    Department getDepartmentById(Integer departmentId);
    Department getDepartmentByName(String departmentName);
    Department addDepartment(Department department);

    void updteDepartment(Department department);
    void deleteDepartment(Integer id);

}
