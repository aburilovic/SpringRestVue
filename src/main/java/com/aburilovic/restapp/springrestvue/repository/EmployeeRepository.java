package com.aburilovic.restapp.springrestvue.repository;

import com.aburilovic.restapp.springrestvue.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByName(@Param("name") String name);
    List<Employee> findAllByCompany_Name(@Param("name") String name);
}
