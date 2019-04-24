package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.WeeklyOff;

public interface WeeklyOffRepo extends JpaRepository<WeeklyOff, Integer> {

	List<WeeklyOff> findByDelStatusAndIsActive(int i, int j);

	@Transactional
	@Modifying
	@Query("update WeeklyOff set del_status=0  WHERE wo_id=:woId")
	int deleteWeeklyOff(@Param("woId") int woId);

	WeeklyOff findBywoIdAndDelStatus(int woId,int i);

}
