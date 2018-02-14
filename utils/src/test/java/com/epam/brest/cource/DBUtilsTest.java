package com.epam.brest.cource;

import org.junit.Assert;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBUtilsTest {

    @org.junit.Test
    public void getConnection() throws SQLException, ClassNotFoundException {

        DBUtils dbUtils = new DBUtils();
        Assert.assertNotNull(dbUtils.getConnection());

    }
}