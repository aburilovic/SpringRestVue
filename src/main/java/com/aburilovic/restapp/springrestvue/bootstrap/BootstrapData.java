package com.aburilovic.restapp.springrestvue.bootstrap;

import com.aburilovic.restapp.springrestvue.entity.Company;
import com.aburilovic.restapp.springrestvue.entity.Employee;
import com.aburilovic.restapp.springrestvue.repository.CompanyRepository;
import com.aburilovic.restapp.springrestvue.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(BootstrapData.class);

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("************ Bootstraping database");

        if (companyRepository.count() == 0) {
            companyRepository.save(new Company("IBM"));
            companyRepository.save(new Company("Google"));
            companyRepository.save(new Company("Amazon"));
            companyRepository.save(new Company("Tesla"));
        }

        for (Company company : companyRepository.findAll()) {
            log.info(company.toString());
        }

        if (employeeRepository.count() == 0) {
            Employee employee = new Employee("Ivan", "12345678");
            employee.setCompany(companyRepository.findFirstByName("Amazon"));
            employeeRepository.save(employee);

            employee = new Employee("Marko", "4256326326");
            employee.setCompany(companyRepository.findFirstByName("Amazon"));
            employeeRepository.save(employee);

            employee = new Employee("Ante", "65465464");
            employee.setCompany(companyRepository.findFirstByName("Tesla"));
            employeeRepository.save(employee);

            for (Employee e : employeeRepository.findAll()) {
                log.info(e.toString());
            }
        }
    }
}
