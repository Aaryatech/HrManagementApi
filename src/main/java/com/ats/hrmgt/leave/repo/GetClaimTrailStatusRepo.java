package com.ats.hrmgt.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.GetClaimTrailStatus;

public interface GetClaimTrailStatusRepo extends JpaRepository<GetClaimTrailStatus, Integer> {
	@Query(value = "SELECT\n" + 
			"  \n" + 
			"    e.emp_fname,\n" + 
			"    e.emp_mname,\n" + 
			"    e.emp_sname,\n" + 
			"    e.emp_photo,   \n" + 
			"    ct.emp_remarks,\n" + 
			"    ct.claim_trail_pkey,\n" + 
			"    ct.emp_id,\n" + 
			"    ct.claim_id,\n" + 
			"    ct.claim_status,\n" + 
			"    ct.maker_enter_datetime\n" + 
			"   \n" + 
			"FROM\n" + 
			"    emp_info AS e,\n" + 
			"    claim_trail AS ct\n" + 
			"WHERE\n" + 
			"    e.emp_id = ct.emp_id and ct.claim_id=:claimId", nativeQuery = true)
	List<GetClaimTrailStatus> getClaimTrailByClaimId(@Param("claimId") int claimId);

//	List<GetLeaveStatus> getClaimTrailByClaimId(int claimId);
}
