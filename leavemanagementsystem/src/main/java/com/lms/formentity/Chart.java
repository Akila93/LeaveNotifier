package com.lms.formentity;

import java.util.ArrayList;

/**
 * Created by nuwantha on 11/18/16.
 */
public class Chart {
    int date;
    ArrayList<Integer> arr;

    public Chart(int date, ArrayList<Integer> arr) {
        this.date = date;
        this.arr = arr;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public ArrayList<Integer> getArr() {
        return arr;
    }

    public void setArr(ArrayList<Integer> arr) {
        this.arr = arr;
    }
}
