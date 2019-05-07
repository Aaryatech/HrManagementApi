package com.ats.hrmgt.claim.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.GetLeaveApplyAuthwise;
import com.ats.hrmgt.model.GetClaimApplyAuthwise;


public interface GetClaimApplyAuthwiseRepo   extends JpaRepository<GetClaimApplyAuthwise, Integer>{
	
	
	@Query(value = " SELECT\n" + 
			"    la.claim_id,\n" + 
			"    la.project_id,\n" + 
			"    la.emp_id,\n" + 
			"    la.claim_type_id,\n" + 
			"    la.claim_amount,\n" + 
			"    la.claim_remarks,\n" + 
			"    la.claim_date,\n" + 
			"    la.circulated_to,\n" + 
			"    e.emp_code,e.emp_photo,  \n" + 
			"    CONCAT(e.emp_sname, \" \", e.emp_fname) AS emp_name,\n" + 
			"    le.ca_ini_auth_emp_id,\n" + 
			"    le.ca_fin_auth_emp_id,\n" + 
			"    la.ex_int1 ,lt.claim_type_title as claim_type_name,h.project_title    \n" + 
			"FROM\n" + 
			"    claim_type lt,\n" + 
			"    claim_apply la,\n" + 
			"    claim_authority le,\n" + 
			"    emp_info e, project_header h \n" + 
			"WHERE\n" + 
			"   la.claim_type_id = lt.claim_type_id AND le.emp_id = la.emp_id AND e.emp_id = le.emp_id AND(\n" + 
			"        (\n" + 
			"            le.ca_ini_auth_emp_id=:empId AND la.ex_int1 = 1\n" + 
			"        ) OR(\n" + 
			"            le.ca_fin_auth_emp_id=:empId AND la.ex_int1 = 2\n" + 
			"        )    \n" + 
			"    ) AND la.project_id=h.project_id ", nativeQuery = true)

	List<GetClaimApplyAuthwise> getClaimApplyList(@Param("empId") int empId);
	
	
	
	
	@Query(value = " SELECT\n" + 
			"    la.claim_id,\n" + 
			"    la.project_id,\n" + 
			"    la.emp_id,\n" + 
			"    la.claim_type_id,\n" + 
			"    la.claim_amount,\n" + 
			"    la.claim_remarks,\n" + 
			"    la.claim_date,\n" + 
			"    la.circulated_to,\n" + 
			"    e.emp_code,e.emp_photo, \n" + 
			"   CONCAT(e.emp_sname, \\\" \\\", e.emp_fname) AS emp_name,\n" + 
			"    le.ca_ini_auth_emp_id,\n" + 
			"    le.ca_fin_auth_emp_id,\n" + 
			"    la.ex_int1 ,lt.claim_type_title as claim_type_name,h.project_title    \n" + 
			"FROM\n" + 
			"    claim_type lt,\n" + 
			"    claim_apply la,\n" + 
			"    claim_authority le,\n" + 
			"    emp_info e, project_header h \n" + 
			"WHERE\n" + 
			"   la.claim_type_id = lt.claim_type_id AND le.emp_id = la.emp_id AND e.emp_id = le.emp_id AND(\n" + 
			"        (\n" + 
			"            le.ca_ini_auth_emp_id=:empId AND la.ex_int1 = 2  AND le.ca_fin_auth_emp_id != le.ca_ini_auth_emp_id \n" + 
			"        ) OR(\n" + 
			"            le.ca_fin_auth_emp_id=:empId AND la.ex_int1 = 1  AND le.ca_fin_auth_emp_id != le.ca_ini_auth_emp_id \n" + 
			"        )  OR(\n" + 
			"                le.emp_id=:empId \n" + 
			"                AND  la.ex_int1 in (2,1)         \n" + 
			"            )\n" + 
			"  \n" + 
			"    ) AND la.project_id=h.project_id ", nativeQuery = true)

	List<GetClaimApplyAuthwise> getClaimApplyList2(@Param("empId") int empId);
	
	@Query(value = "SELECT\n" + 
			"        la.claim_id,\n" + 
			"        la.project_id,\n" + 
			"        la.emp_id,\n" + 
			"        la.claim_type_id,\n" + 
			"        la.claim_amount,\n" + 
			"        la.claim_remarks,\n" + 
			"        la.claim_date,\n" + 
			"        la.circulated_to,\n" + 
			"        e.emp_code,\n" + 
			"        e.emp_photo,\n" + 
			"        CONCAT(e.emp_sname,\n" + 
			"        \\\" \\\",\n" + 
			"        e.emp_fname) AS emp_name,\n" + 
			"        0 as ca_ini_auth_emp_id,\n" + 
			"        0 as ca_fin_auth_emp_id,\n" + 
			"        la.ex_int1 ,\n" + 
			"        lt.claim_type_title as claim_type_name,\n" + 
			"        h.project_title     \n" + 
			"    FROM\n" + 
			"        claim_type lt,\n" + 
			"        claim_apply la,\n" + 
			"     \n" + 
			"        emp_info e,\n" + 
			"        project_header h  \n" + 
			"    WHERE\n" + 
			"        la.claim_type_id = lt.claim_type_id \n" + 
			"     \n" + 
			"        AND la.project_id=h.project_id and la.emp_id=e.emp_id and la.claim_id=:claimId "
			 , nativeQuery = true)

	GetClaimApplyAuthwise getClaimApplyDetails(@Param("claimId") int claimId);
	

}
