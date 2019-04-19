package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.LeavesAllotment;

public interface LeaveAllotmentRepository extends JpaRepository<LeavesAllotment, Integer>{

	@Transactional
	@Modifying
	@Query("update LeavesAllotment set del_status=0  WHERE lvsa_pkey=:lvsaPkey")
	int deleteLeaveAllotment(int lvsaPkey);

	List<LeavesAllotment> findByDelStatus(int i);

	LeavesAllotment findByLvsaPkeyAndDelStatus(int lvsaPkey, int i);

	List<LeavesAllotment> findByEmpIdAndLvsIdAndDelStatus(int empId, int lvsId, int i);

}
