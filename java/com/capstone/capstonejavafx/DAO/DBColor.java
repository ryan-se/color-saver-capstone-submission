package com.capstone.capstonejavafx.DAO;

import com.capstone.capstonejavafx.JDBC;
import com.capstone.capstonejavafx.model.Color;
import com.capstone.capstonejavafx.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

import java.sql.*;
import java.time.*;

public class DBColor {

    /**
     * Adds a color to the database
     * @param color to add
     */
    public static void addColor(Color color) {
        Statement stmt;
        try {
            stmt = JDBC.connection.createStatement();
            stmt.executeUpdate("INSERT INTO colors (color_name, color_brand, color_sheen, color_area, color_purchased_date, color_painted_date, color_user_id)" +
                    " VALUES ('"+color.getColorName()+"'," +
                    "'"+color.getColorBrand()+"'," +
                    "'"+color.getColorSheen()+"'," +
                    "'"+color.getColorArea()+"'," +
                    "'"+color.getColorPurchasedDate()+"'," +
                    "'"+color.getColorPaintedDate()+"'," +
                    "'"+color.getUserID()+"');");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Gets all of a user's colors from the database
     * @param userID for the user to find
     * @return an ObservableList of Color objects
     */
    public static ObservableList<Color> getUserColors(int userID) {
        ObservableList<Color> userColors = FXCollections.observableArrayList();

        Statement stmt;
        ResultSet rs;

        try {
            stmt = JDBC.connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM colors WHERE color_user_id = " + userID);
            while (rs.next()) {
                // create an array list of appointment objects
                int colorID = rs.getInt("color_id");
                String colorName = rs.getString("color_name");
                String colorBrand = rs.getString("color_brand");
                String colorSheen = rs.getString("color_sheen");
                String colorArea = rs.getString("color_area");
                Date colorPurchasedDate = rs.getDate("color_purchased_date");
                Date colorPaintedDate = rs.getDate("color_painted_date");
                int colorUserId = rs.getInt("color_user_id");

                Color color = new Color(colorID, colorName, colorBrand, colorSheen, colorArea, colorPurchasedDate, colorPaintedDate, colorUserId);
                // add the color to the list
                System.out.println(color.toString());
                userColors.add(color);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userColors;
    }

    /**
     * Counts the frequency of a color in the database
     * @param color to find
     * @return an int value for the number of times the color was found
     */
    public static int countColor(Color color) {
        Statement stmt;
        ResultSet rs;

        try {
            stmt = JDBC.connection.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM colors WHERE color_name = " + "'" + color.getColorName() + "'" + ";");
            while (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Finds a color by name
     * @param colorNameToFind string value for the color to look for
     * @return the found Color object
     */
    public static Color getColorByName(String colorNameToFind) {
        Statement stmt;
        ResultSet rs;
        Color color = new Color();
        try {

            stmt = JDBC.connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM colors WHERE color_name='" + colorNameToFind + "' LIMIT 1;");

            while (rs.next()) {
                int colorID = rs.getInt("color_id");
                String colorName = rs.getString("color_name");
                String colorBrand = rs.getString("color_brand");
                String colorSheen = rs.getString("color_sheen");
                String colorArea = rs.getString("color_area");
                Date colorPurchasedDate = rs.getDate("color_purchased_date");
                Date colorPaintedDate = rs.getDate("color_painted_date");
                int colorUserId = rs.getInt("color_user_id");

                color = new Color(colorID, colorName, colorBrand, colorSheen, colorArea, colorPurchasedDate, colorPaintedDate, colorUserId);
                // add the color to the list
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return color;
    }

    /**
     * Only finds distinct color names, and ignores the frequency of their use
     * @return ObservableList of Color objects
     */
    public static ObservableList<Color> getUniqueColors() {
        ObservableList<Color> uniqueColors = FXCollections.observableArrayList();

        Statement stmt;
        ResultSet rs;

        try {

            stmt = JDBC.connection.createStatement();
            rs = stmt.executeQuery("SELECT DISTINCT color_name FROM colors");

            while (rs.next()) {
                String colorName = rs.getString(1);
                Color distinctColor = DBColor.getColorByName(colorName);
                System.out.println(distinctColor.toString());
                uniqueColors.add(distinctColor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return uniqueColors;
    }

    /**
     * Gets all the colors recorded in the database
     * @return an ObservableList of Color objects
     */
    public static ObservableList<Color> getAllColors() {
        ObservableList<Color> allColors = FXCollections.observableArrayList();

        Statement stmt;
        ResultSet rs;

        try {

            stmt = JDBC.connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM colors");

            while (rs.next()) {
                // create an array list of appointment objects
                int colorID = rs.getInt("color_id");
                String colorName = rs.getString("color_name");
                String colorBrand = rs.getString("color_brand");
                String colorSheen = rs.getString("color_sheen");
                String colorArea = rs.getString("color_area");
                Date colorPurchasedDate = rs.getDate("color_purchased_date");
                Date colorPaintedDate = rs.getDate("color_painted_date");
                int colorUserId = rs.getInt("color_user_id");

                Color color = new Color(colorID, colorName, colorBrand, colorSheen, colorArea, colorPurchasedDate, colorPaintedDate, colorUserId);
               // add the color to the list
                System.out.println(color.toString());
                allColors.add(color);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return allColors;
    }

    /**
     * Takes a string as parameter and searches the database for colors that contain that search term
     * @param colorSearchTerm a string to search for
     * @return an ObservableList of Color objects that match the search criteria
     */
    public static ObservableList<Color> searchColors(String colorSearchTerm) {
        ObservableList<Color> returnedColors = FXCollections.observableArrayList();

        Statement stmt;
        ResultSet rs;

        try {

            stmt = JDBC.connection.createStatement();
            rs = stmt.executeQuery("SELECT DISTINCT color_name FROM colors WHERE LOWER(color_name) LIKE '%" + colorSearchTerm + "%'");


            while (rs.next()) {
                String colorName = rs.getString(1);
                Color distinctColor = DBColor.getColorByName(colorName);
                System.out.println(distinctColor.toString());
                returnedColors.add(distinctColor);
            }

            /*
            stmt = JDBC.connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM colors WHERE LOWER(color_name) LIKE '%" + colorSearchTerm + "%'");
            while (rs.next()) {
                // create an array list of appointment objects
                int colorID = rs.getInt("color_id");
                String colorName = rs.getString("color_name");
                String colorBrand = rs.getString("color_brand");
                String colorSheen = rs.getString("color_sheen");
                String colorArea = rs.getString("color_area");
                Date colorPurchasedDate = rs.getDate("color_purchased_date");
                Date colorPaintedDate = rs.getDate("color_painted_date");
                int colorUserId = rs.getInt("color_user_id");

                Color color = new Color(colorID, colorName, colorBrand, colorSheen, colorArea, colorPurchasedDate, colorPaintedDate, colorUserId);
                // add the color to the list
                System.out.println(color.toString());
                returnedColors.add(color);
            } */
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return returnedColors;
    }

    /**
     * Updates a color in the database.
     * @param color to be updated
     */
    public static void updateColor(Color color) {
        Statement stmt;
        try {
            stmt = JDBC.connection.createStatement();


            stmt.executeUpdate(String.valueOf(stmt.executeUpdate("UPDATE colors " +
                    "SET " +
                    "color_id = '"+ color.getColorID() + "', " +
                    "color_name = '"+ color.getColorName() + "', " +
                    "color_brand = '"+ color.getColorBrand() + "', " +
                    "color_sheen = '"+ color.getColorSheen() + "', " +
                    "color_user_id = '"+ DBUser.LoggedInUser.getUserID() + "', " +
                    "color_purchased_date = '"+ color.getColorPurchasedDate() + "', " +
                    "color_painted_date = '"+ color.getColorPaintedDate() + "' " +
                    "WHERE color_id = " + color.getColorID() + ";")));


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Removes a color from the database by ID number
     * @param id for the color to delete
     */
    public static void deleteColor(int id) {
        Statement stmt;

        try {
            stmt = JDBC.connection.createStatement();
            stmt.execute("DELETE FROM colors WHERE color_id = " + id + ";");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
