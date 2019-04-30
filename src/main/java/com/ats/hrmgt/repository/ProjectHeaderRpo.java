package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.ProjectHeader;

public interface ProjectHeaderRpo extends JpaRepository<ProjectHeader, Integer> {

	List<ProjectHeader> findByDelStatusAndCompanyId(int i, int companyId);

	@Transactional
	@Modifying
	@Query("update ProjectHeader set del_status=0  WHERE project_id=:projectId")
	int deleteProjectHeader(int projectId);

	ProjectHeader findByProjectIdAndDelStatus(int projectId, int i);

}
