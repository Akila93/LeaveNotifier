package com.lms.entity;

/**
 * Created by nuwantha on 12/14/16.
 */
public class Department {
    private int depID;
    private String depName;

    public Department(){

    }

    public Department(int depID, String depName) {
        this.depID = depID;
        this.depName = depName;
    }

    public int getDepID() {
        return depID;
    }

    public void setDepID(int depID) {
        this.depID = depID;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }
}
