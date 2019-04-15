package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.LeaveTrail;

public interface LeaveTrailRepository extends JpaRepository<LeaveTrail, Integer> {

	List<LeaveTrail> findByDelStatus(int i);

	@Transactional
	@Modifying
	@Query("update LeaveTrail set del_status=0  WHERE trail_pkey=:trailPkey")
	int deleteLeaveTrail(int trailPkey);

	LeaveTrail findByTrailPkeyAndDelStatus(int trailPkey, int i);

}
