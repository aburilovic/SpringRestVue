package com.aburilovic.restapp.springrestvue.repository;

import com.aburilovic.restapp.springrestvue.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "companies", path = "companies")
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByName(@Param("name") String name);
    Company findFirstByName(@Param("name") String name);
}
