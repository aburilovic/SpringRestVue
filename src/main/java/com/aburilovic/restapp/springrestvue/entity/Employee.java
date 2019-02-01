package com.aburilovic.restapp.springrestvue.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String name;
    String socialNumber;


    @ManyToOne
    @JoinColumn(name="company_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Company company;

    @ManyToMany(mappedBy = "employees")
    private List<Department> departments;


    protected Employee() {
    }

    public Employee(String name, String socialNumber) {
        this.name = name;
        this.socialNumber = socialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocialNumber() {
        return socialNumber;
    }

    public void setSocialNumber(String socialNumber) {
        this.socialNumber = socialNumber;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", socialNumber='" + socialNumber + '\'' +
                '}';
    }
}
