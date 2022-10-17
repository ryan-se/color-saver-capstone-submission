package com.capstone.capstonejavafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC {
    /* local DB settings
    private static final String protocol = "jdbc";
    private static final String vendor = ":postgresql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "CapstoneDB";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "org.postgresql.Driver"; // Driver reference
    private static final String userName = "postgres"; // Username
    private static String password = "password"; // Password

     */

    // DEVELPMENT CONNECTION SETTINGS
    private static final String protocol = "jdbc";
    private static final String vendor = ":postgresql:";
    private static final String location = "//capstonedb.cgrjynjlcmjy.us-east-2.rds.amazonaws.com/";
    private static final String databaseName = "postgres";
    private static final String jdbcUrl = protocol + vendor + location + databaseName; // LOCAL
    private static final String driver = "org.postgresql.Driver"; // Driver reference
    private static final String userName = "postgres"; // Username
    private static String password = "reinapearl"; // Password
    public static Connection connection = null;  // Connection Interface
    public static PreparedStatement preparedStatement;


    /**
     * Connect to the database
     * @return a database connection
     */
    public static Connection makeConnection() {

        System.out.println("Connecting to database...");

        try {
            Class.forName("java.sql.DriverManager");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver Missing");
            e.printStackTrace();
        }

        System.out.println("JDBC Driver Registered.");

        try {
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (SQLException e) {
            System.out.println("Connection Failed!:\n" + e.getMessage());
        }

        if (connection != null) {
            System.out.println("Successfully connected to database!");
        } else {
            System.out.println("Failed to make connection!");
        }
        return connection;
    }
    /* DEPRECIATED CONNECTION METHOD
    public static Connection makeConnection() {
        try {
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    } */

    /**
     * Close the database connection
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Database connection closed.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Get the current connection
     * @return Connection
     */
    public static Connection getConnection() {
        return connection;
    }
}
