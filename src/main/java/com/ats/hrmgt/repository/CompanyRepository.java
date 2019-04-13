package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

	List<Company> findByDelStatus(int i);

}
