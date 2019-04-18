package com.ats.hrmgt.leave.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.leave.model.LeaveStructureDetails;

public interface LeaveStructureDetailsRepo extends JpaRepository<LeaveStructureDetails, Integer> {

}
