package com.danielsolawa.mongoquery.example.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Human {

    @Id
    private String id;

    private String name;

    private List<Dog> dogList = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dog> getDogList() {
        return dogList;
    }

    public void setDogList(List<Dog> dogList) {
        this.dogList = dogList;
    }

    @Override
    public String toString() {
        return "Human{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dogList=" + dogList +
                '}';
    }
}
