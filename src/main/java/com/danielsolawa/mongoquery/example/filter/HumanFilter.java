package com.danielsolawa.mongoquery.example.filter;

import com.danielsolawa.mongoquery.filter.MFilter;

public class HumanFilter implements MFilter {

    private String name;
    private String dogName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    @Override
    public String toString() {
        return "HumanFilter{" +
                "name='" + name + '\'' +
                ", dogName='" + dogName + '\'' +
                '}';
    }
}
