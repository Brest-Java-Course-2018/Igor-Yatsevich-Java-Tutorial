package com.epam.brest.cource.model;

/**
 * TEST COMMENT
 */
public class Employee {

    private Integer employeeId;
    private String employeeName;
    private Integer salary;
    private Integer departmentId;

    /**
     * TEST COMMENT FOR METHOD
     * @return EMPLOYEE ID
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", salary=" + salary +
                ", departmentId=" + departmentId +
                '}';
    }
}
