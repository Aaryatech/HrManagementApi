package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.CalculateYear;

public interface CalculateYearRepository  extends JpaRepository<CalculateYear, Integer>{

	
	@Transactional
	@Modifying
	@Query("update CalculateYear set del_status=0  WHERE cal_yr_id=:calYrId")
	int deleteCalculateYear(int calYrId);

	CalculateYear findByCalYrId(int calYrId);

}
