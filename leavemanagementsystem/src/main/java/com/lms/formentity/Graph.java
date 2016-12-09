package com.lms.formentity;

/**
 * Created by nuwantha on 12/9/16.
 */
public class Graph {
    int key;
    int firstHalf;
    int secondHalf;
    int fullDay;
    private String month;

    String monthList[]= new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};

    public Graph( ) {

    }

    public Graph(int key, int firstHalf, int secondHalf,int fullDay) {
        this.key = key;
        this.firstHalf = firstHalf;
        this.secondHalf = secondHalf;
        this.fullDay=fullDay;
        this.month=monthList[key-1];
    }

    public int getFullDay() {
        return fullDay;
    }

    public void setFullDay(int fullDay) {
        this.fullDay = fullDay;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getFirstHalf() {
        return firstHalf;
    }

    public void setFirstHalf(int firstHalf) {
        this.firstHalf = firstHalf;
    }

    public int getSecondHalf() {
        return secondHalf;
    }

    public void setSecondHalf(int secondHalf) {
        this.secondHalf = secondHalf;
    }
}
