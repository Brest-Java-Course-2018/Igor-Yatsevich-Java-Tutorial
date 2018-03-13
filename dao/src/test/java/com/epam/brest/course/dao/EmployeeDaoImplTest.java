package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
                                    "classpath:test-dao.xml",
                                    "classpath:dao.xml"})
@Transactional

public class EmployeeDaoImplTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void getEmployees() {

        List<Employee> employees = employeeDao.getEmployees();
        Assert.assertTrue(employees.isEmpty());

    }


}
