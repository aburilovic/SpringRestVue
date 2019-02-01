package com.aburilovic.restapp.springrestvue.api;

import com.aburilovic.restapp.springrestvue.SpringRestVueApplication;
import com.aburilovic.restapp.springrestvue.entity.Company;
import com.aburilovic.restapp.springrestvue.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringRestVueApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringDataRelationshipsTest {

    @Autowired
    private TestRestTemplate template;

    private static String EMPLOYEE_ENDPOINT = "http://localhost:8080/employees/";
    private static String COMPANY_ENDPOINT = "http://localhost:8080/companies/";
    private static String DEPARTMENT_ENDPOINT = "http://localhost:8080/departments/";

    private static String COMPANY_NAME = "Amazon";
    private static String EMPLOYEE_NAME = "Ante Antisa";

    @Test
    public void whenSaveOneToManyRelationship_thenCorrect() {
        Company company = new Company(COMPANY_NAME);
        ResponseEntity<Company> companyPostResponseEntity = template.postForEntity(COMPANY_ENDPOINT, company, Company.class);

        Employee employee1 = new Employee("Ante", "12345678");
        ResponseEntity<Employee> employeePostResponseEntity1 = template.postForEntity(EMPLOYEE_ENDPOINT, employee1, Employee.class);

        Employee employee2 = new Employee("Marko", "5645654");
        ResponseEntity<Employee> employeePostResponseEntity2 = template.postForEntity(EMPLOYEE_ENDPOINT, employee2, Employee.class);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "text/uri-list");
        HttpEntity<String> employeeHttpEntity
                = new HttpEntity<>(companyPostResponseEntity.getHeaders().get("Location").get(0), requestHeaders);

        template.exchange(employeePostResponseEntity1.getHeaders().get("Location").get(0) + "/company",
                HttpMethod.PUT, employeeHttpEntity, String.class);
        template.exchange(employeePostResponseEntity2.getHeaders().get("Location").get(0) + "/company",
                HttpMethod.PUT, employeeHttpEntity, String.class);

        ResponseEntity<Company> companyGetResponse =
                template.getForEntity(employeePostResponseEntity2.getHeaders().get("Location").get(0) + "/company", Company.class);
        assertEquals("library is incorrect",
                companyGetResponse.getBody().getName(), COMPANY_NAME);
    }
}
