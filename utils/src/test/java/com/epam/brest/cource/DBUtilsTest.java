package com.epam.brest.cource;

import org.junit.Assert;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtilsTest {

    private final DBUtils dbUtils = new DBUtils();

    @org.junit.Test
    public void getConnection()
            throws SQLException, ClassNotFoundException {

        Assert.assertNotNull(dbUtils.getConnection());

    }

    @org.junit.Test
    public void addUser()
            throws SQLException, ClassNotFoundException {

        Connection connection = dbUtils.getConnection();

        dbUtils.createUserTable(connection);

        Assert.assertEquals(dbUtils.addUser(connection,"admin", "admin", "User admin"), 1);
        Assert.assertEquals(dbUtils.addUser(connection,"admin1", "admin1", "User admin1"), 1);
        Assert.assertEquals(dbUtils.addUser(connection,"admin2", "admin2", "User admin2"), 1);

        Assert.assertEquals(dbUtils.addUser(connection, "", "admin3", "User admin3"), -1);
        Assert.assertEquals(dbUtils.addUser(connection, "admin3", "", "User admin3"), -1);
        Assert.assertEquals(dbUtils.addUser(connection, "admin3", "admin3", ""), -1);

        Assert.assertEquals(dbUtils.addUser(connection, null, "admin3", "User admin3"), -2);
        Assert.assertEquals(dbUtils.addUser(connection, "admin3", null, "User admin3"), -2);
        Assert.assertEquals(dbUtils.addUser(connection, "admin3", "admin3", null), -2);

        Assert.assertEquals(dbUtils.addUser(null, "admin3", "admin3", "User admin3"), -3);

    }

    @org.junit.Test
    public void deleteUser()
            throws SQLException, ClassNotFoundException {

        Connection connection = dbUtils.getConnection();

        Assert.assertEquals(dbUtils.deleteUser(connection, 2), 1);
        Assert.assertEquals(dbUtils.deleteUser(connection, 1), 1);

        Assert.assertEquals(dbUtils.deleteUser(connection, 4), 0);

        Assert.assertEquals(dbUtils.deleteUser(connection, -5), -1);
        Assert.assertEquals(dbUtils.deleteUser(connection, 0), -1);

        Assert.assertEquals(dbUtils.deleteUser(connection, null), -2);

        Assert.assertEquals(dbUtils.deleteUser(null, null), -3);

    }

}