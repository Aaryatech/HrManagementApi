package com.ats.hrmgt.claim.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.GetLeaveApplyAuthwise;
import com.ats.hrmgt.model.GetClaimApplyAuthwise;


public interface GetClaimApplyAuthwiseRepo   extends JpaRepository<GetClaimApplyAuthwise, Integer>{
	//Pending 
	
	@Query(value = " SELECT\n" + 
			"    la.ca_head_id,\n" + 
			"    la.proj_id,\n" + 
			"    la.emp_id,\n" + 
			"    la.claim_title,\n" + 
			"    la.claim_amount,\n" + 
			"    la.ca_from_dt,\n" + 
			"    la.ca_to_dt,\n" + 
			"    la.claim_status,\n" + 
			"    la.circulated_to,\n" + 
			"    e.emp_code,\n" + 
			"    e.emp_photo,\n" + 
			"    CONCAT(e.emp_sname, \" \", e.emp_fname) AS emp_name,\n" + 
			"    le.ca_ini_auth_emp_id,\n" + 
			"    le.ca_fin_auth_emp_id,\n" + 
			"    la.ex_var1 ,\n" + 
			"    h.project_title\n" + 
			"FROM\n" + 
			"    claim_apply_header la,\n" + 
			"    claim_authority le,\n" + 
			"    emp_info e,\n" + 
			"    project_header h\n" + 
			"WHERE  \n" + 
			"    le.emp_id = la.emp_id AND e.emp_id = le.emp_id AND(\n" + 
			"        (\n" + 
			"            le.ca_ini_auth_emp_id =:empId AND la.claim_status = 1\n" + 
			"        ) OR(\n" + 
			"            le.ca_fin_auth_emp_id =:empId AND la.claim_status = 2\n" + 
			"        )\n" + 
			"    ) AND la.proj_id = h.project_id ", nativeQuery = true)

	List<GetClaimApplyAuthwise> getClaimApplyList(@Param("empId") int empId);
	
	
	//Info
	
	@Query(value = "SELECT\n" + 
			"    la.ca_head_id,\n" + 
			"    la.proj_id,\n" + 
			"    la.emp_id,\n" + 
			"    la.claim_title,\n" + 
			"    la.claim_amount,\n" + 
			"    la.ca_from_dt,\n" + 
			"    la.ca_to_dt,\n" + 
			"    la.claim_status,\n" + 
			"    la.circulated_to,\n" + 
			"    e.emp_code,\n" + 
			"    e.emp_photo,\n" + 
			"    CONCAT(e.emp_sname, \" \", e.emp_fname) AS emp_name,\n" + 
			"    le.ca_ini_auth_emp_id,\n" + 
			"    le.ca_fin_auth_emp_id,\n" + 
			"    la.ex_var1,\n" + 
			"    h.project_title\n" + 
			"FROM\n" + 
			"    claim_apply_header la,\n" + 
			"    claim_authority le,\n" + 
			"    emp_info e,\n" + 
			"    project_header h\n" + 
			"    WHERE\n" + 
			"           le.emp_id = la.emp_id \n" + 
			"        AND e.emp_id = le.emp_id \n" + 
			"        AND(\n" + 
			"            (\n" + 
			"                le.ca_ini_auth_emp_id=:empId \n" + 
			"                AND la.claim_status = 2  \n" + 
			"                AND le.ca_fin_auth_emp_id != le.ca_ini_auth_emp_id          \n" + 
			"            ) \n" + 
			"            OR(\n" + 
			"                le.ca_fin_auth_emp_id=:empId \n" + 
			"                AND la.claim_status = 1  \n" + 
			"                AND le.ca_fin_auth_emp_id != le.ca_ini_auth_emp_id          \n" + 
			"            )  \n" + 
			"            OR(\n" + 
			"                le.emp_id=1                  \n" + 
			"                AND  la.claim_status in (\n" + 
			"                    2,1\n" + 
			"                )                      \n" + 
			"            )        \n" + 
			"        ) \n" + 
			"        AND la.proj_id=h.project_id ", nativeQuery = true)

	List<GetClaimApplyAuthwise> getClaimApplyList2(@Param("empId") int empId);


	@Query(value = "SELECT\n" + 
			"        la.ca_head_id,\n" + 
			"        la.proj_id,\n" + 
			"        la.emp_id,\n" + 
			"        la.claim_title,\n" + 
			"        la.claim_amount,\n" + 
			"        la.ca_from_dt,\n" + 
			"        la.ca_to_dt,\n" + 
			"        la.claim_status,\n" + 
			"        la.circulated_to,\n" + 
			"        e.emp_code,\n" + 
			"        e.emp_photo,\n" + 
			"        CONCAT(e.emp_sname,\n" + 
			"        \" \",\n" + 
			"        e.emp_fname) AS emp_name,\n" + 
			"        0 as ca_ini_auth_emp_id,\n" + 
			"        0 as ca_fin_auth_emp_id,\n" + 
			"        la.ex_var1 ,\n" + 
			"        h.project_title \n" + 
			"    FROM\n" + 
			"        claim_apply_header la,\n" + 
			"        emp_info e,\n" + 
			"        project_header h \n" + 
			"    WHERE\n" + 
			"         e.emp_id = la.emp_id \n" + 
			"        AND h.project_manager_emp_id in(:empId)\n" + 
			"        AND la.proj_id = h.project_id\n" + 
			"        and claim_status=1 and h.project_manager_emp_id!=la.emp_id", nativeQuery = true)
	List<GetClaimApplyAuthwise> getClaimApplyListForPendingForManager(@Param("empId") int empId);


	@Query(value = "SELECT\n" + 
			"        la.ca_head_id,\n" + 
			"        la.proj_id,\n" + 
			"        la.emp_id,\n" + 
			"        la.claim_title,\n" + 
			"        la.claim_amount,\n" + 
			"        la.ca_from_dt,\n" + 
			"        la.ca_to_dt,\n" + 
			"        la.claim_status,\n" + 
			"        la.circulated_to,\n" + 
			"        e.emp_code,\n" + 
			"        e.emp_photo,\n" + 
			"        CONCAT(e.emp_sname,\n" + 
			"        \" \",\n" + 
			"        e.emp_fname) AS emp_name,\n" + 
			"        0 as ca_ini_auth_emp_id,\n" + 
			"        0 as ca_fin_auth_emp_id,\n" + 
			"        la.ex_var1 ,\n" + 
			"        h.project_title      \n" + 
			"    FROM\n" + 
			"        claim_apply_header la,\n" + 
			"        emp_info e,\n" + 
			"        project_header h      \n" + 
			"    WHERE\n" + 
			"        e.emp_id = la.emp_id          \n" + 
			"        AND h.project_manager_emp_id in(\n" + 
			"           select project_manager_emp_id from project_header where del_status=1\n" + 
			"        )         \n" + 
			"        AND la.proj_id = h.project_id         \n" + 
			"        and claim_status=1\n" + 
			"        and h.project_manager_emp_id=la.emp_id", nativeQuery = true)
	List<GetClaimApplyAuthwise> getClaimApplyListForPendingForAdmin();
	
	/*
	 * @Query(value = "SELECT\n" + "        la.claim_id,\n" +
	 * "        la.project_id,\n" + "        la.emp_id,\n" +
	 * "        la.claim_type_id,\n" + "        la.claim_amount,\n" +
	 * "        la.claim_remarks,\n" + "        la.claim_date,\n" +
	 * "        la.circulated_to,\n" + "        e.emp_code,\n" +
	 * "        e.emp_photo,\n" + "        CONCAT(e.emp_sname,\n" +
	 * "        \\\" \\\",\n" + "        e.emp_fname) AS emp_name,\n" +
	 * "        0 as ca_ini_auth_emp_id,\n" + "        0 as ca_fin_auth_emp_id,\n" +
	 * "        la.ex_var1 ,\n" +
	 * "        lt.claim_type_title as claim_type_name,\n" +
	 * "        h.project_title     \n" + "    FROM\n" + "        claim_type lt,\n"
	 * + "        claim_apply la,\n" + "     \n" + "        emp_info e,\n" +
	 * "        project_header h  \n" + "    WHERE\n" +
	 * "        la.claim_type_id = lt.claim_type_id \n" + "     \n" +
	 * "        AND la.project_id=h.project_id and la.emp_id=e.emp_id and la.claim_id=:claimId "
	 * , nativeQuery = true)
	 * 
	 * GetClaimApplyAuthwise getClaimApplyDetails(@Param("claimId") int claimId);
	 */
	

}
