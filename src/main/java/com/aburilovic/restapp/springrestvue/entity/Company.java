package com.aburilovic.restapp.springrestvue.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Long locationX;
    private Long locationY;

    protected Company() {
    }

    @OneToMany(mappedBy = "company")
    private List<Employee> employees;


    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLocationX() {
        return locationX;
    }

    public void setLocationX(Long locationX) {
        this.locationX = locationX;
    }

    public Long getLocationY() {
        return locationY;
    }

    public void setLocationY(Long locationY) {
        this.locationY = locationY;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", locationX=" + locationX +
                ", locationY=" + locationY +
                '}';
    }
}
