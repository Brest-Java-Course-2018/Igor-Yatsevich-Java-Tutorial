package com.epam.brest.course.model;

import org.junit.Assert;

public class EmployeeTest {

    private static final String ABDULA = "Abdula";

    @org.junit.Test
    public void getEmployeeName() {

        Employee employee = new Employee();

        employee.setEmployeeName(ABDULA);
        Assert.assertEquals(ABDULA, employee.getEmployeeName());

    }
}