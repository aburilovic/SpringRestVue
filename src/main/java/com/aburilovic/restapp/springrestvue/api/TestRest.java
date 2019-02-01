package com.aburilovic.restapp.springrestvue.api;

import com.aburilovic.restapp.springrestvue.entity.Customer;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRest {

    @GetMapping("/hello")
    public Customer sayHello() {
        Customer customer = new Customer("Tono");
        customer.setAdress("Velebitska 16A");
        return customer;
    }
}
