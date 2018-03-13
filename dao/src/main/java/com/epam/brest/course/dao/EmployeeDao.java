package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;
import java.util.List;

public interface EmployeeDao {

    List<Employee> getEmployees();
    List<Employee> getEmployeesByDepartment(Integer _department_ID);
    List<Employee> getEmployeesByDepartment(String _department_name);

    Employee getEmployee(Integer _employee_ID);
    Employee getEmployee(String _first_name, String _last_name);
    Employee addEmployee(Employee _employee);

    void updateEmployee(Employee _employee);
    void deleteEmployee(Integer _employee_ID);

}
