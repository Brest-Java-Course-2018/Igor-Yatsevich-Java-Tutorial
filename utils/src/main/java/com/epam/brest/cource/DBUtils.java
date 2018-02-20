package com.epam.brest.cource;

import java.sql.*;

/**
 * DBUtils class for implementation JDBC operations.
 */

public class DBUtils {

    /**
     * Get connection to DB.
     *
     * @return Connection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException {

        System.out.println("Connect to DB.");
        String databaseURL = "jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1";

        Class.forName("org.h2.Driver");

        Connection connection = DriverManager.getConnection(databaseURL, "sa", "");

        return connection;

    }

    /**
     * Create app_user table in DB.
     *
     * @param connection
     * @throws SQLException
     */
    public void createUserTable(Connection connection) throws SQLException {

        System.out.println("Create app_user table.");

        String createTable = "CREATE TABLE app_user (" +
                "user_id INT NOT NULL AUTO_INCREMENT," +
                "login VARCHAR(255) NOT NULL," +
                "password VARCHAR(255) NOT NULL," +
                "description VARCHAR(255) NULL," +
                "PRIMARY KEY (user_id))";

        try (Statement statement = connection.createStatement()) {

            statement.executeUpdate(createTable);
        }

    }

    /**
     * Add user into app_user table in DB.
     *
     * @param connection
     * @param login
     * @param password
     * @param description
     * @return check_transaction_result
     * @throws SQLException
     */
    public int addUser(Connection connection, String login, String password, String description) throws SQLException {

        System.out.println(String.format("Add user: %s: ", login));

        if (connection == null)

            return -2; //Connection not defined;

        if (login == null || password == null || description == null)

            return -1;

        if (login.length() == 0 || password.length() == 0 || description.length() == 0)

            return -1; //Input parameter error;

        String newUser = "INSERT INTO app_user (login, password, description) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(newUser);

        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, description);

        int check_transaction_result = preparedStatement.executeUpdate();

        return check_transaction_result;

    }

    /**
     * Print informtion about all users in app_user table.
     *
     * @param connection
     * @throws SQLException
     */
    public void getUser(Connection connection) throws SQLException {

        String getRecords = "SELECT user_id, login, description FROM app_user ORDER BY user_id";
        Statement statement = connection.createStatement();
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
     * @param connection
     * @param user_id
     * @return check_transaction_status
     * @throws SQLException
     */
    public int deleteUser(Connection connection, final Integer user_id)
            throws SQLException {

        System.out.println("Deleting user with id: " + user_id + ";");

        String remove_user = "DELETE FROM app_user WHERE user_id = ?";
        PreparedStatement preparedStatement =
                connection.prepareStatement(remove_user);

        preparedStatement.setString(1, user_id.toString());

        int check_transaction_status = preparedStatement.executeUpdate();

        return check_transaction_status;
    }

}
