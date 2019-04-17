package com.ats.hrmgt.leave.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.leave.model.Holiday;

public interface HolidayRepo extends JpaRepository<Holiday, Integer> {

}
