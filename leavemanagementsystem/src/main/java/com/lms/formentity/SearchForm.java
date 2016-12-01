package com.lms.formentity;

/**
 * Created by akila on 12/1/16.
 */
public class SearchForm implements Form {
    private String name;
    private int year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
