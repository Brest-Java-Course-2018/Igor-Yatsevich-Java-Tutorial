package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
                                    "classpath:test-dao.xml",
                                    "classpath:dao.xml"})
@Rollback
@Transactional
public class DepartmentDaoImplTest {

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void getDepartments() {

        List<Department> departments = departmentDao.getDepartments();
        Assert.assertFalse(departments.isEmpty());

    }

    @Test(expected = EmptyResultDataAccessException.class)
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

    @Test(expected = EmptyResultDataAccessException.class)
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

    @Test
    public void addDepartment() {

        List<Department> departments = departmentDao.getDepartments();
        int size_before = departments.size();
        Department department = new Department("Education and Training", "Decription");
        Department new_department =  departmentDao.addDepartment(department);

        Assert.assertNotNull(new_department.getDepartmentId());
        Assert.assertTrue(new_department.getDepartmentName()
                .equals(department.getDepartmentName()));
        Assert.assertTrue(new_department.getDescription()
                .equals(department.getDescription()));
        Assert.assertTrue((size_before + 1) == departmentDao.getDepartments().size());

    }

    @Test(expected = IllegalArgumentException.class)
    public void addSameDepartment() {

        Department department = new Department("Education and Training", "Decription");
        departmentDao.addDepartment(department);
        departmentDao.addDepartment(department);

    }

    @Test
    public void updateDepartment() {

        Department department = new Department("Education", "Decription");
        Department new_department =  departmentDao.addDepartment(department);

        new_department.setDepartmentName("NEWEducation");
        new_department.setDescription("NEWDescription");
        departmentDao.updateDepartment(new_department);

        Department updated_department = departmentDao.getDepartmentById
                (new_department.getDepartmentId());

        Assert.assertTrue(new_department.getDepartmentId()
                .equals(updated_department.getDepartmentId()));
        Assert.assertTrue(new_department.getDepartmentName()
                .equals(department.getDepartmentName()));
        Assert.assertTrue(new_department.getDescription()
                .equals(department.getDescription()));
    }

    @Test
    public void deleteDepartment() {

        Department department = new Department("Education", "Decription");
        department =  departmentDao.addDepartment(department);
        List<Department> departments = departmentDao.getDepartments();
        int size_before = departments.size();

        departmentDao.deleteDepartment(department.getDepartmentId());

        Assert.assertTrue((size_before - 1) == departmentDao.getDepartments().size());

    }
}