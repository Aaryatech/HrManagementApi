package com.ats.hrmgt.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.GetEmployeeAuthorityWise;

public interface GetEmployeeAuthorityWiseRepo extends JpaRepository<GetEmployeeAuthorityWise, Integer>{


	@Query(value = " SELECT l.emp_id FROM leave_authority l WHERE l.ini_auth_emp_id=:empId OR l.fin_auth_emp_id=:empId", nativeQuery = true)

	List<GetEmployeeAuthorityWise> getEmpIdList(@Param("empId") int empId
			);
	
	
	
	
}