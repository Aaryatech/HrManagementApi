package com.ats.hrmgt.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.GetClaimTrailStatus;

public interface GetClaimTrailStatusRepo extends JpaRepository<GetClaimTrailStatus, Integer> {
	@Query(value = "SELECT\n" + 
			"    e.emp_fname,\n" + 
			"    e.emp_mname,\n" + 
			"    e.emp_sname,\n" + 
			"    e.emp_photo,\n" + 
			"    t.emp_remarks, t.claim_trail_pkey, \n" + 
			"    t.emp_id,\n" + 
			"    t.claim_status,t.claim_id,     \n" + 
			"    \n" + 
			"    t.maker_enter_datetime,\n" + 
			"    COALESCE(\n" + 
			"        (\n" + 
			"        SELECT DISTINCT\n" + 
			"            CONCAT(\n" + 
			"                e.emp_fname,\n" + 
			"                \" \",\n" + 
			"                e.emp_mname,\n" + 
			"                \" \",\n" + 
			"                e.emp_sname\n" + 
			"            ) AS user_name\n" + 
			"        FROM\n" + 
			"            emp_info AS e,\n" + 
			"            m_user u\n" + 
			"        WHERE\n" + 
			"            u.user_id = t.maker_user_id AND e.emp_id = u.emp_id\n" + 
			"    ),\n" + 
			"    NULL\n" + 
			"    ) AS user_name\n" + 
			"FROM\n" + 
			"    emp_info AS e,\n" + 
			"    claim_trail AS t\n" + 
			"WHERE\n" + 
			"    e.emp_id = t.emp_id AND t.claim_id =:claimId", nativeQuery = true)
	List<GetClaimTrailStatus> getClaimTrailByClaimId(@Param("claimId") int claimId);

//	List<GetLeaveStatus> getClaimTrailByClaimId(int claimId);
	/*
	 * SELECT e.emp_fname, e.emp_mname, e.emp_sname, e.emp_photo, t.emp_remarks,
	 * t.emp_id, t.claim_status, t.maker_user_id, t.maker_enter_datetime, COALESCE(
	 * ( SELECT DISTINCT CONCAT( e.emp_fname, " ", e.emp_mname, " ", e.emp_sname )
	 * AS user_name FROM emp_info AS e, m_user u WHERE u.user_id = t.maker_user_id
	 * AND e.emp_id = u.emp_id ), NULL ) AS user_name FROM emp_info AS e,
	 * claim_trail AS t WHERE e.emp_id = t.emp_id AND t.claim_id = 1
	 */
}
