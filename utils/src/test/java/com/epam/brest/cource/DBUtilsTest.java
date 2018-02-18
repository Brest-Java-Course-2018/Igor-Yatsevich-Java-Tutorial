package com.epam.brest.cource;

import org.junit.Assert;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtilsTest {

    @org.junit.Test
    public void getConnection() throws SQLException, ClassNotFoundException {

        DBUtils dbUtils = new DBUtils();

        Assert.assertNotNull(dbUtils.getConnection());

    }

    @org.junit.Test
    public void deleteUser() throws SQLException, ClassNotFoundException {

        DBUtils dbUtils = new DBUtils();

        Connection connection = dbUtils.getConnection();
        dbUtils.createUserTable(connection);

        dbUtils.addUser(connection,"admin", "admin", "User admin");
        dbUtils.addUser(connection,"admin1", "admin1", "User admin1");
        dbUtils.addUser(connection,"admin2", "admin2", "User admin2");

        Assert.assertEquals(dbUtils.deleteUser(connection, 2), 1);
        Assert.assertEquals(dbUtils.deleteUser(connection, 1), 1);
        Assert.assertEquals(dbUtils.deleteUser(connection, 4), 0);
        Assert.assertEquals(dbUtils.deleteUser(connection, -5), 0);
        Assert.assertEquals(dbUtils.deleteUser(connection, 0), 0);

    }

    @org.junit.Test
    public void addUser() throws SQLException, ClassNotFoundException {

        DBUtils dbUtils = new DBUtils();

        Connection connection = dbUtils.getConnection();
        dbUtils.createUserTable(connection);

        Assert.assertEquals(dbUtils.addUser(connection,"admin", "admin", "User admin"), 1);
        Assert.assertEquals(dbUtils.addUser(connection,"admin1", "admin1", "User admin1"), 1);
        Assert.assertEquals(dbUtils.addUser(connection,"admin2", "admin2", "User admin2"), 1);

        Assert.assertEquals(dbUtils.addUser(connection, null, "admin3", "User admin3"), -1);
        Assert.assertEquals(dbUtils.addUser(connection, "admin3", null, "User admin3"), -1);
        Assert.assertEquals(dbUtils.addUser(connection, "admin3", "admin3", null), -1);

        Assert.assertEquals(dbUtils.addUser(connection, "", "admin3", "User admin3"), -1);
        Assert.assertEquals(dbUtils.addUser(connection, "admin3", "", "User admin3"), -1);
        Assert.assertEquals(dbUtils.addUser(connection, "admin3", "admin3", ""), -1);

        Assert.assertEquals(dbUtils.addUser(null, "admin3", "admin3", "User admin3"), -2);


    }
}