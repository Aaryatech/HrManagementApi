package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.EmployeeCategory;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveSummary;
import com.ats.hrmgt.model.Location;
import com.ats.hrmgt.repository.LeaveSummaryRepository;
import com.ats.hrmgt.repository.LocationRepository;

@RestController
public class MasterWebApiController {
	
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	LeaveSummaryRepository leaveSummaryRepository;
	
	@RequestMapping(value = { "/checkUniqueEmail" }, method = RequestMethod.POST)
	public @ResponseBody Info checkUniqueEmail(@RequestParam("email") String email) {
  
		Info info=new Info();
	   Location location = new Location();
		try {
			 
			location = locationRepository.findByLocHrContactEmailAndDelStatus(email,1);
			System.out.print("Loction :"+location);
			if(location!=null)
			{
				info.setError(false);
				info.setMsg("Record Found");
			}
			else
			{
				info.setError(true);
				info.setMsg("Record Not Found");
			}
		

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("Record Not Found");
			e.printStackTrace();
		}

		return info;

	}
	@RequestMapping(value = { "/checkUniqueMobileNumber" }, method = RequestMethod.POST)
	public @ResponseBody Info checkUniqueMobileNumber(@RequestParam("mobileNo") String mobileNo) {
  
		Info info=new Info();
	   Location location = new Location();
		try {
			 
			location = locationRepository.findByLocHrContactNumberAndDelStatus(mobileNo,1);
			System.out.print("Loction :"+location);
			if(location!=null)
			{
				info.setError(false);
				info.setMsg("Record Found");
			}
			else
			{
				info.setError(true);
				info.setMsg("Record Not Found");
			}
		

		} catch (Exception e) {
			info.setError(true);
			info.setMsg("Record Not Found");
			e.printStackTrace();
		}

		return info;

	}
	
	@RequestMapping(value = { "/saveLeaveSummary" }, method = RequestMethod.POST)
	public @ResponseBody LeaveSummary saveLeaveSummary(@RequestBody LeaveSummary employeeCategory) {

		 
		LeaveSummary save = new LeaveSummary();
		try {
			 
			save = leaveSummaryRepository.saveAndFlush(employeeCategory);
			
			if(save!=null) {
				save.setError(false);
			}else {
				
				save = new LeaveSummary();
				save.setError(true);
			}
  

		} catch (Exception e) {
			save = new LeaveSummary();
			save.setError(true);
			e.printStackTrace();
		}

		return save;

	}
	
	@RequestMapping(value = { "/getLeaveSummaryList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveSummary> getLeaveSummaryList(@RequestParam("compId") int compId) {

		 
		List<LeaveSummary> list = new ArrayList<LeaveSummary>();
		try {
			 
			if(compId!=0) {
				
				list = leaveSummaryRepository.findByDelStatusAndCompanyId(1,compId);
				
			}else {
				
				list = leaveSummaryRepository.findByDelStatus(1);
				
			}
			
  

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/deleteLeaveSummary" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLeaveSummary(@RequestParam("lvSumupId") int lvSumupId) {

		 
		Info info = new Info();
		
		try {
			 
			int delete = leaveSummaryRepository.deleteLeaveSummary(lvSumupId);
  
			if(delete>0) {
				info.setError(false); 
				info.setMsg("deleted");
			}else {
				info.setError(true); 
				info.setMsg("failed");
			}

		} catch (Exception e) {
 
			e.printStackTrace();
			info.setError(true); 
			info.setMsg("failed");
		}

		return info;

	}
	
	@RequestMapping(value = { "/getLeaveSummaryById" }, method = RequestMethod.POST)
	public @ResponseBody LeaveSummary getLeaveSummaryById(@RequestParam("lvSumupId") int lvSumupId) {

		 
		LeaveSummary employeeCategory = new LeaveSummary();
		try {
			 
			employeeCategory = leaveSummaryRepository.findByLvSumupIdAndDelStatus(lvSumupId,1);
  

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return employeeCategory;

	}
}