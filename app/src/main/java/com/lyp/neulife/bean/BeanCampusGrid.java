package com.lyp.neulife.bean;

/**
 * Created by lyp on 2016/4/7.
 */
public class BeanCampusGrid {

    private int campusGridImgID;
    private String campusGridName;

    public BeanCampusGrid(String campusGridName, int campusGridImgID) {
        this.campusGridImgID = campusGridImgID;
        this.campusGridName = campusGridName;
    }

    public int getCampusGridImgID() {
        return campusGridImgID;
    }

    public void setCampusGridImgID(int campusGridImgID) {
        this.campusGridImgID = campusGridImgID;
    }

    public String getCampusGridName() {
        return campusGridName;
    }

    public void setCampusGridName(String campusGridName) {
        this.campusGridName = campusGridName;
    }
}
