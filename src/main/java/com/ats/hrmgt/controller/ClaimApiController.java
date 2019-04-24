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

import com.ats.hrmgt.leave.model.GetLeaveAuthority;
import com.ats.hrmgt.leave.model.Holiday;
import com.ats.hrmgt.leave.model.LeaveAuthority;
import com.ats.hrmgt.leave.repo.ClaimAuthorityRepo;
import com.ats.hrmgt.leave.repo.ClaimRepository;
import com.ats.hrmgt.leave.repo.GetClaimAuthorityRepo;
import com.ats.hrmgt.model.ClaimAuthority;
import com.ats.hrmgt.model.ClaimType;
import com.ats.hrmgt.model.GetClaimAuthority;
import com.ats.hrmgt.model.Info;

@RestController
public class ClaimApiController {

	@Autowired
	ClaimRepository claimRepository;

	@Autowired
	ClaimAuthorityRepo claimAuthorityRepo;

	@Autowired
	GetClaimAuthorityRepo getClaimAuthorityRepo;
	
	
	@RequestMapping(value = { "/getClaimAuthorityListByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody ClaimAuthority getLeaveAuthorityListByEmpId(@RequestParam("empId") int empId) {

		ClaimAuthority list = new ClaimAuthority();
		try {

			list = claimAuthorityRepo.findByDelStatusAndEmpId(1, empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getClaimAuthorityList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetClaimAuthority> getClaimAuthorityList(@RequestParam("companyId") int companyId) {

		List<GetClaimAuthority> list = new ArrayList<GetClaimAuthority>();
		try {

			list = getClaimAuthorityRepo.getClaimAuth(companyId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/saveClaimAuthority" }, method = RequestMethod.POST)
	public @ResponseBody ClaimAuthority saveClaimAuthority(@RequestBody ClaimAuthority claimAuthority) {

		ClaimAuthority save = new ClaimAuthority();
		try {

			save = claimAuthorityRepo.saveAndFlush(claimAuthority);

			if (save != null) {
				save.setError(false);
			} else {

				save = new ClaimAuthority();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new ClaimAuthority();
			save.setError(true);
			e.printStackTrace();
		}

		return save;
	}

	@RequestMapping(value = { "/saveClaimType" }, method = RequestMethod.POST)
	public @ResponseBody ClaimType saveClaimType(@RequestBody ClaimType claimType) {

		ClaimType save = new ClaimType();
		try {

			save = claimRepository.saveAndFlush(claimType);

			if (save != null) {
				save.setError(false);
			} else {

				save = new ClaimType();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new ClaimType();
			save.setError(true);
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

	@RequestMapping(value = { "/getClaimListByCompanyId" }, method = RequestMethod.POST)
	public @ResponseBody List<ClaimType> getClaimListByCompanyId(@RequestParam("companyId") int companyId) {

		List<ClaimType> list = new ArrayList<ClaimType>();
		try {

			list = claimRepository.findByDelStatusAndCompanyIdOrderByCompanyIdDesc(1, companyId);

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

	@RequestMapping(value = { "/getClaimById" }, method = RequestMethod.POST)
	public @ResponseBody ClaimType getClaimById(@RequestParam("claimTypeId") int claimTypeId) {

		ClaimType claimType = new ClaimType();
		try {

			claimType = claimRepository.findByClaimTypeIdAndDelStatus(claimTypeId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return claimType;

	}

}
