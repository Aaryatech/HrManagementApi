package com.ats.hrmgt.leave.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.GetAuthorityIds;

public interface GetAuthorityIdsRepo extends JpaRepository<GetAuthorityIds, Integer> {

	
	
	
	
	@Query(value = "  SELECT leave_authority.emp_id,leave_authority.ini_auth_emp_id,leave_authority.fin_auth_emp_id from "
			+ " leave_authority where leave_authority.emp_id=:empId   AND leave_authority.del_status=1 ", nativeQuery = true)

	GetAuthorityIds getAuthIds(@Param("empId") int empId);


}
