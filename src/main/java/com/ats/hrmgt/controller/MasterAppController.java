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

import com.ats.hrmgt.leave.repo.ClaimAuthorityRepo;
import com.ats.hrmgt.leave.repo.ClaimRepository;
import com.ats.hrmgt.model.ClaimType;
import com.ats.hrmgt.model.Company;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.repository.EmployeeInfoRepository;

@RestController
public class MasterAppController {
	@Autowired
	ClaimRepository claimRepository;
	
	@Autowired
	EmployeeInfoRepository employeeInfoRepository;
	@Autowired
	ClaimAuthorityRepo claimAuthorityRepo;
	@RequestMapping(value = { "/saveClaimType" }, method = RequestMethod.POST)
	public @ResponseBody ClaimType saveClaimType(@RequestBody ClaimType claimType) {

		ClaimType save = new ClaimType();
		try {

			save = claimRepository.saveAndFlush(claimType);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getClaimList" }, method = RequestMethod.GET)
	public @ResponseBody List<ClaimType> getClaimList() {

		List<ClaimType> list = new ArrayList<ClaimType>();
		try {

			list = claimRepository.findByDelStatusOrderByClaimTypeIdDesc(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/deleteClaimType" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteClaimType(@RequestParam("claimTypeId") int claimTypeId) {

		Info info = new Info();

		try {

			int delete = claimRepository.deleteClaimType(claimTypeId);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("deleted");
			} else {
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

	@RequestMapping(value = { "/getCompanyById" }, method = RequestMethod.POST)
	public @ResponseBody Company getCompanyById(@RequestParam("claimTypeId") int claimTypeId) {

		Company company = new Company();
		try {

			company = claimRepository.findByClaimTypeIdAndDelStatus(claimTypeId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return company;

	}
	
	  @RequestMapping(value = { "/updateClaimStatus" }, method =
	  RequestMethod.POST) public @ResponseBody Info
	  updateClaimStatus(@RequestParam("empId") int empId,@RequestParam("claimId")
	  int claimId ,@RequestParam("status") int status,
	  
	  @RequestParam("remark") String remark) {
	  
	  Info info = new Info();
	  
	  try {
	  
	  int updateStatus =
			  claimAuthorityRepo.updateClaimStatus(empId,claimId,status,remark);
	  
	  if (updateStatus > 0) { info.setError(false);
	  info.setMsg("Updated Successfully"); } else { info.setError(true);
	  info.setMsg("failed"); }
	  
	  } catch (Exception e) {
	  
	  e.printStackTrace(); info.setError(true); info.setMsg("failed"); }
	  
	  return info;
	  
	  }
	 
	


}
	
