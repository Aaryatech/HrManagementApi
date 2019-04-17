package com.ats.hrmgt.leave.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.Holiday;

public interface HolidayRepo extends JpaRepository<Holiday, Integer> {

	Holiday findByHolidayIdAndDelStatus(int holidayId, int i);

	@Transactional
	@Modifying
	@Query("update Holiday set del_status=0  WHERE holiday_id=:holidayId")
	int deleteHoliday(@Param("holidayId") int holidayId);

}
