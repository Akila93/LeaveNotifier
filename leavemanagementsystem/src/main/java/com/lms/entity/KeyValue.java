package com.lms.entity;

/**
 * Created by nuwantha on 11/16/16.
 */
public class KeyValue {
    private int key;
    private int value;
    private String month;

    String monthList[]= new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};

    public KeyValue(){

    }

    public KeyValue(int key, int value) {
        this.key = key;
        this.value = value;
        month=monthList[key-1];
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
