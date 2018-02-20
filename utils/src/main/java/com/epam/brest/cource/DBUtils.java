package com.epam.brest.cource;

import java.sql.*;

/**
 * DBUtils class for implementation JDBC operations.
 */

public class DBUtils {

    /**
     * Get connection to DB.
     *
     * @return Instance of class Connection, or null if connection is not defined;
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConnection()
            throws ClassNotFoundException, SQLException {

        System.out.println("Connect to DB.");
        String databaseURL = "jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1";

        Class.forName("org.h2.Driver");

        Connection connection = DriverManager.getConnection(databaseURL, "sa", "");

        return connection;

    }

    /**
     * Create app_user table in DB.
     *
     * @param _connection
     * @throws SQLException
     */
    public void createUserTable(Connection _connection)
            throws SQLException {

        System.out.println("Create app_user table.");

        String createTable = "CREATE TABLE app_user (" +
                "user_id INT NOT NULL AUTO_INCREMENT," +
                "login VARCHAR(255) NOT NULL," +
                "password VARCHAR(255) NOT NULL," +
                "description VARCHAR(255) NULL," +
                "PRIMARY KEY (user_id))";

        try (Statement statement = _connection.createStatement()) {

            statement.executeUpdate(createTable);
        }

    }

    /**
     * Add user into app_user table in DB.
     *
     * @param _connection
     * @param _login
     * @param _password
     * @param _description
     * @return Value which expressed status of operation.
     * If value is equal to:
     * * '-3' then connection has null value or not defined;
     * * '-2' then input String parameters has a null value or not defined;
     * * '-1' then input String parameters has a zero length;
     * * '0' then query is not executed;
     * * '1' then user successfully added;
     * @throws SQLException
     */
    public int addUser(Connection _connection,
                       final String _login,
                       final String _password,
                       final String _description)
            throws SQLException {

        if (_connection == null)
            return -3;

        if (_login == null || _password == null || _description == null)
            return -2;

        if (_login.length() == 0 || _password.length() == 0 || _description.length() == 0)
            return -1;

        System.out.println(String.format("Add user: %s: ", _login));

        String newUser = "INSERT INTO app_user (login, password, description) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = _connection.prepareStatement(newUser);

        preparedStatement.setString(1, _login);
        preparedStatement.setString(2, _password);
        preparedStatement.setString(3, _description);

        int transact_status = preparedStatement.executeUpdate();

        return transact_status;

    }

    /**
     * Print informtion about all users in app_user table.
     *
     * @param _connection
     * @throws SQLException
     */
    public void getUser(Connection _connection)
            throws SQLException {

        String getRecords = "SELECT user_id, login, description FROM app_user ORDER BY user_id";
        Statement statement = _connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getRecords);

        System.out.println("Users:");

        while (resultSet.next()) {

            System.out.println(String.format("User: %s, %s, %s",
                    resultSet.getInt("user_id"),
                    resultSet.getString("login"),
                    resultSet.getString("description")));

        }

    }

    /**
     * Delete user from table app_user in DB.
     *
     * @param _connection
     * @param _user_id
     * @return Value which expressed status of operation.
     * If value is equal to:
     * * '-3' then connection has null value or not defined;
     * * '-2' then input Integer parameters has a null value or not defined;
     * * '-1' then input Integer parameters has a negative value or zero;
     * * '0' then query is not executed;
     * * '1' then user successfully deleted;
     * @throws SQLException
     */
    public int deleteUser(Connection _connection,
                          final Integer _user_id)
            throws SQLException {

        if (_connection == null)
            return -3;

        if (_user_id == null)
            return -2;

        if (_user_id <= 0)
            return -1;

        System.out.println("Deleting user with id: " + _user_id + ";");

        String remove_user = "DELETE FROM app_user WHERE user_id = ?";
        PreparedStatement preparedStatement =
                _connection.prepareStatement(remove_user);

        preparedStatement.setString(1, _user_id.toString());

        int transact_status = preparedStatement.executeUpdate();

        return transact_status;
    }

}
