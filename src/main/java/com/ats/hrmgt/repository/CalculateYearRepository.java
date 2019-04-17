package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.CalenderYear;

public interface CalculateYearRepository extends JpaRepository<CalenderYear, Integer> {

	CalenderYear findByCalYrId(int calYrId);

	List<CalenderYear> findByIsCurrent(int i);

}
