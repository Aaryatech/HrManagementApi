package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.ProjectHeader;

public interface ProjectHeaderRpo extends JpaRepository<ProjectHeader, Integer> {

	List<ProjectHeader> findByDelStatusAndCompanyId(int i, int companyId);
	
	

}
