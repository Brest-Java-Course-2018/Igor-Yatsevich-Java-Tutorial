package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
                                    "classpath:test-dao.xml"})
public class DepartmentDaoImplTest {

    @Autowired
    private DepartmentDao departmentDao;

    @org.junit.Test
    public void getDepartments() {

        List<Department> departments = departmentDao.getDepartments();
        Assert.assertFalse(departments.isEmpty());

    }

    @org.junit.Test(expected = EmptyResultDataAccessException.class)
    public void getDepartmentById() {

        Department department = departmentDao.getDepartmentById(1);

        Assert.assertNotNull(department);
        Assert.assertTrue(department.getDepartmentId()
                .equals(1));
        Assert.assertTrue(department.getDepartmentName()
                .equals("Distribution"));
        Assert.assertTrue(department.getDescription()
                .equals("Distribution performs"));
        departmentDao.getDepartmentById(500);

    }

    @org.junit.Test(expected = EmptyResultDataAccessException.class)
    public void getDepartmentByName() {

        Department department = departmentDao.getDepartmentByName("Distribution");

        Assert.assertNotNull(department);
        Assert.assertTrue(department.getDepartmentId()
                .equals(1));
        Assert.assertTrue(department.getDepartmentName()
                .equals("Distribution"));
        Assert.assertTrue(department.getDescription()
                .equals("Distribution performs"));
        departmentDao.getDepartmentByName("RUDERANT_VALUE");

    }

    @org.junit.Test
    public void addDepartment() {

        Department department = new Department();

        department.setDepartmentName("Development");
        department.setDescription("Perform developing applications");

        Assert.assertNotNull(departmentDao.addDepartment(department));

        Department added_department = departmentDao.getDepartmentById(2);

        Assert.assertNotNull(added_department);
        Assert.assertTrue(added_department.getDepartmentId()
                .equals(2));
        Assert.assertTrue(added_department.getDepartmentName()
                .equals(department.getDepartmentName()));
        Assert.assertTrue(added_department.getDescription()
                .equals(department.getDescription()));

    }

    @org.junit.Test
    public void updateDepartment() {

        Department department = new Department();

        department.setDepartmentId(1);
        department.setDepartmentName("Drugs distribution");
        department.setDescription("Selling drugs for arctic bears.");

        departmentDao.updateDepartment(department);

        Department updated_department =
                departmentDao.getDepartmentByName(department.getDepartmentName());

        Assert.assertTrue(updated_department.getDepartmentId()
                .equals(department.getDepartmentId()));
        Assert.assertTrue(updated_department.getDepartmentName()
                .equals(department.getDepartmentName()));
        Assert.assertTrue(updated_department.getDescription()
                .equals(department.getDescription()));

    }

    @org.junit.Test(expected = EmptyResultDataAccessException.class)
        public void deleteDepartment() {
        

    }
}