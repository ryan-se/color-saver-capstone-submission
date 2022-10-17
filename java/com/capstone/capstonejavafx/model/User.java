package com.capstone.capstonejavafx.model;

public class User {
    private int userID;
    private String userName;
    private String userPass;

    public User (int id, String name, String pass) {
        this.userID = id;
        this.userName = name;
        this.userPass = pass;
    }

    public User(String name) {
        this.userName = name;
    }

    public User() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                '}';
    }
}
