package com.capstone.capstonejavafx.model;

import java.sql.Date;

public class Color {
    private int colorID;
    private String colorName;
    private String colorBrand;
    private String colorSheen;
    private String colorArea;
    private Date colorPurchasedDate;
    private Date colorPaintedDate;
    private int userID;

    private int timesUsed;

    public Color(int colorID, String colorName, String colorBrand, String colorSheen, String colorArea, Date colorPurchasedDate, Date colorPaintedDate, int userID) {
        this.colorID = colorID;
        this.colorName = colorName;
        this.colorBrand = colorBrand;
        this.colorSheen = colorSheen;
        this.colorArea = colorArea;
        this.colorPurchasedDate = colorPurchasedDate;
        this.colorPaintedDate = colorPaintedDate;
        this.userID = userID;
    }

    public Color(String colorName, String colorBrand, String colorSheen, String colorArea, Date colorPurchasedDate, Date colorPaintedDate, int userID) {
        this.colorName = colorName;
        this.colorBrand = colorBrand;
        this.colorSheen = colorSheen;
        this.colorArea = colorArea;
        this.colorPurchasedDate = colorPurchasedDate;
        this.colorPaintedDate = colorPaintedDate;
        this.userID = userID;
    }

    public Color() {
    }

    public Color(String colorName, int timesUsed) {
        this.colorName = colorName;
        this.timesUsed = timesUsed;
    }

    public int getColorID() {
        return colorID;
    }

    public void setColorID(int colorID) {
        this.colorID = colorID;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorBrand() {
        return colorBrand;
    }

    public void setColorBrand(String colorBrand) {
        this.colorBrand = colorBrand;
    }

    public String getColorSheen() {
        return colorSheen;
    }

    public void setColorSheen(String colorSheen) {
        this.colorSheen = colorSheen;
    }

    public String getColorArea() {
        return colorArea;
    }

    public void setColorArea(String colorArea) {
        this.colorArea = colorArea;
    }

    public Date getColorPurchasedDate() {
        return colorPurchasedDate;
    }

    public void setColorPurchasedDate(Date colorPurchasedDate) {
        this.colorPurchasedDate = colorPurchasedDate;
    }

    public Date getColorPaintedDate() {
        return colorPaintedDate;
    }

    public void setColorPaintedDate(Date colorPaintedDate) {
        this.colorPaintedDate = colorPaintedDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTimesUsed() {
        return timesUsed;
    }

    public void setTimesUsed(int timesUsed) {
        this.timesUsed = timesUsed;
    }

    @Override
    public String toString() {
        return "Color{" +
                "colorID=" + colorID +
                ", colorName='" + colorName + '\'' +
                ", colorBrand='" + colorBrand + '\'' +
                ", colorSheen='" + colorSheen + '\'' +
                ", colorArea='" + colorArea + '\'' +
                ", colorPurchasedDate=" + colorPurchasedDate +
                ", colorPaintedDate=" + colorPaintedDate +
                ", userID=" + userID +
                ", timesUsed=" + timesUsed +
                '}';
    }
}
