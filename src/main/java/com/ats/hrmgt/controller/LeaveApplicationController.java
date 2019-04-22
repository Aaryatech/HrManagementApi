package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.leave.model.LeaveHistory;
import com.ats.hrmgt.leave.repo.LeaveHistoryRepo;
import com.ats.hrmgt.model.LeaveSummary;

@RestController
public class LeaveApplicationController {
	
	
	@Autowired
	LeaveHistoryRepo leaveHistoryRepo;

	
	@RequestMapping(value = { "/getLeaveHistoryList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveHistory> getLeaveHistoryList(@RequestParam("empId") int empId) {

		 
		List<LeaveHistory> list = new ArrayList<LeaveHistory>();
		try {
			 
			
				list = leaveHistoryRepo.getLeaveHistoryByEmpId(empId);
			

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return list;

	}
	
	

}
