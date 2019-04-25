package com.ats.hrmgt.leave.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.GetAuthorityIds;

public interface GetAuthorityIdsRepo extends JpaRepository<GetAuthorityIds, Integer> {

	
	
	
	
	@Query(value = "  SELECT leave_authority.emp_id,leave_authority.ini_auth_emp_id,leave_authority.fin_auth_emp_id from "
			+ " leave_authority where leave_authority.emp_id=:empId   AND leave_authority.del_status=1 ", nativeQuery = true)

	GetAuthorityIds getAuthIds(@Param("empId") int empId);
	
	@Query(value = "  SELECT claim_authority.emp_id,claim_authority.ca_ini_auth_emp_id as ini_auth_emp_id,"
			+ "claim_authority.ca_fin_auth_emp_id  as fin_auth_emp_id from "
			+ " claim_authority where claim_authority.emp_id=:empId   AND claim_authority.del_status=1 AND claim_authority.company_id=:companyId ", nativeQuery = true)

	GetAuthorityIds getClaimAuthIds(@Param("empId") int empId,@Param("companyId") int companyId);


}
