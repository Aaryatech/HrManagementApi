package com.ats.hrmgt.repository;

 import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.ClaimApplyHeader;

public interface ClaimHeaderRepo extends JpaRepository<ClaimApplyHeader, Integer> {
 
	
	@Transactional
	@Modifying
	@Query("update ClaimApplyHeader set ex_int1=:trailId  WHERE ca_head_id=:claimId")
	int updateTrailApply(int claimId,int trailId);
	
}
