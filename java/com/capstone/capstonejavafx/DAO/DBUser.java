package com.capstone.capstonejavafx.DAO;

import com.capstone.capstonejavafx.JDBC;
import com.capstone.capstonejavafx.model.Color;
import com.capstone.capstonejavafx.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUser {
    public static User LoggedInUser = new User();

    public static boolean logIn(String user, String pass) {
        Statement stmt;
        ResultSet rs;

        boolean loggedIn = false;

        try {
            stmt = JDBC.connection.createStatement();
            rs = stmt.executeQuery("SELECT user_id, user_name, user_pass\n" +
                    "\tFROM public.users;");
            while (rs.next()) {
                int userIDDB = rs.getInt("user_id");
                String userNameDB = rs.getString("user_name");
                String userPassDB = rs.getString("user_pass");
                if (user.equals(userNameDB) && pass.equals(userPassDB)) {
                    LoggedInUser.setUserID(userIDDB);
                    LoggedInUser.setUserName(userNameDB);
                    LoggedInUser.setUserPass(userPassDB);
                    loggedIn = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return loggedIn;
    }

    /**
     * Allows the user to change their password
     * @param userID an int value that represents the user's ID
     * @param newPasword a string value that represents the new password
     */
    public static void changeUserPasword(int userID, String newPasword) {
        Statement stmt;
        try {
            stmt = JDBC.connection.createStatement();
            stmt.executeUpdate("UPDATE users SET user_pass='" + newPasword + "' WHERE user_id='" + userID + "';");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Checks the database to validate the user's password
     * @param userID the int value that represents the user's ID
     * @param password the current user's password as a string
     * @return True if the password is correct, False if not
     */
    public static boolean checkPassword(int userID, String password) {
        Statement stmt;
        ResultSet rs;

        try {
            stmt = JDBC.connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users WHERE user_id='" + userID + "' AND user_pass='" + password + "';");
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    /**
     * Adds a user object to the database
     * @param user to be added
     */
    public static void addUser(User user) {
        Statement stmt;
        try {
            stmt = JDBC.connection.createStatement();
            stmt.executeUpdate("INSERT INTO users (user_id, user_name, user_pass)" +
                    " VALUES ('"+user.getUserID()+"'," +
                    "'"+user.getUserName()+"'," +
                    "'"+user.getUserPass()+"');");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Gets a new user ID for a new user
     * @return an int number to be used for the ID
     */
    public static int getNewUserID() {
        Statement stmt;
        ResultSet rs;
        int userCount = 0;
        try {
            stmt = JDBC.connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                userCount++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userCount + 1;
    }

    /**
     * Gets all users from the database
     * @return an ObservableList of User objects
     */
    public static ObservableList<User> getAllUsers() {
        ObservableList<User> allUsers = FXCollections.observableArrayList();

        Statement stmt;
        ResultSet rs;

        try {

            stmt = JDBC.connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {

                String userName = rs.getString("user_name");
                User user = new User(userName);
                allUsers.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return allUsers;
    }
}
