package com.aburilovic.restapp.springrestvue.api;

import com.aburilovic.restapp.springrestvue.entity.Company;
import com.aburilovic.restapp.springrestvue.entity.Customer;
import com.aburilovic.restapp.springrestvue.entity.Employee;
import com.aburilovic.restapp.springrestvue.repository.CompanyRepository;
import com.aburilovic.restapp.springrestvue.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestRest {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/hello")
    public Customer sayHello() {
        Customer customer = new Customer("Toni");
        customer.setAdress("Velebitska 16A");
        return customer;
    }

    @GetMapping("/company2")
    public Company getCompany(@Param("name") String name) {
        return companyRepository.findFirstByName(name);
    }

    @GetMapping("/companies2")
    public List<Company> getCompany() {
        return companyRepository.findAll();
    }

    @GetMapping("/employees2")
    public List<Employee> getCompanyEmployees(@Param("companyName") String companyName) {
        return employeeRepository.findAllByCompany_Name(companyName);
    }
}
