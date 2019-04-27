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

import com.ats.hrmgt.claim.repo.ClaimTrailRepo;
import com.ats.hrmgt.claim.repo.GetClaimApplyAuthwiseRepo;
import com.ats.hrmgt.leave.model.ClaimDetail;
import com.ats.hrmgt.leave.model.GetAuthorityIds;
import com.ats.hrmgt.leave.model.GetClaimTrailStatus;
import com.ats.hrmgt.leave.model.GetEmployeeAuthorityWise;
import com.ats.hrmgt.leave.model.GetLeaveApplyAuthwise;
import com.ats.hrmgt.leave.repo.ClaimApplyRepo;
import com.ats.hrmgt.leave.repo.ClaimDetailRepo;
import com.ats.hrmgt.leave.repo.ClaimRepository;
import com.ats.hrmgt.leave.repo.GetAuthorityIdsRepo;
import com.ats.hrmgt.leave.repo.GetClaimTrailStatusRepo;
import com.ats.hrmgt.leave.repo.GetEmployeeAuthorityWiseRepo;
import com.ats.hrmgt.model.ClaimApply;
import com.ats.hrmgt.model.ClaimTrail;
import com.ats.hrmgt.model.GetClaimApplyAuthwise;
import com.ats.hrmgt.model.GetEmployeeInfo;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveApply;
import com.ats.hrmgt.repository.GetEmpInfoRepo;

@RestController
public class ClaimApplicationApiController {
	
	@Autowired
	GetEmployeeAuthorityWiseRepo getEmployeeAuthorityWise;
	
	@Autowired
	GetEmpInfoRepo getEmpInfo;
	
	@Autowired
	GetAuthorityIdsRepo getAuthorityIdsRepo;
	
	@Autowired
	ClaimApplyRepo claimApplyRepository;

	@Autowired
	ClaimTrailRepo claimTrailRepository;
	
	@Autowired
	ClaimDetailRepo claimDetailRepo;
	
	@Autowired
	GetClaimTrailStatusRepo getClaimTrailStatusRepo;

	
	
	@RequestMapping(value = { "/getClaimListByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<ClaimDetail> getClaimStatusList(@RequestParam("empId") int empId
			) {

		List<ClaimDetail> list = new ArrayList<ClaimDetail>();

		try {

			list = claimDetailRepo.getClaimList(empId);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					List<GetClaimTrailStatus> leaveStatus = new ArrayList<GetClaimTrailStatus>();
					leaveStatus = getClaimTrailStatusRepo.getClaimTrailByClaimId(list.get(i).getClaimId());
					list.get(i).setGetClaimTrailStatus(leaveStatus);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	

	@RequestMapping(value = { "/getEmpInfoClaimAuthWise" }, method = RequestMethod.POST)
	public @ResponseBody List<GetEmployeeInfo> getEmpInfoClaimAuthWise(@RequestParam("companyId") int companyId,
			 @RequestParam("empId") int empId) {

		List<GetEmployeeInfo> list = new ArrayList<GetEmployeeInfo>();

		List<GetEmployeeAuthorityWise> empIdList = new ArrayList<GetEmployeeAuthorityWise>();

		empIdList = getEmployeeAuthorityWise.getEmpIdListInClaimAuth(empId);

		System.err.println("empIdList" + empIdList.size());
		try {

			list = getEmpInfo.getEmpIdListByCompanyIdForClaim(companyId,empIdList);

			System.err.println("GetEmployeeAuthorityWise::::" + list.size());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	
	@RequestMapping(value = { "getClaimAuthIds" }, method = RequestMethod.POST)
	public @ResponseBody GetAuthorityIds getClaimAuthIds(@RequestParam("empId") int empId,@RequestParam("companyId") int companyId) {

		System.out.println("emp id is "+empId);
		GetAuthorityIds leaveApply = new GetAuthorityIds();
		try {

			leaveApply = getAuthorityIdsRepo.getClaimAuthIds(empId,companyId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveApply;

	}
	
	
	//*********************************************Claim Apply********************************************

	@RequestMapping(value = { "/saveClaimApply" }, method = RequestMethod.POST)
	public @ResponseBody ClaimApply saveClaimApply(@RequestBody ClaimApply claim) {

		ClaimApply save = new ClaimApply();
		try {

			save = claimApplyRepository.saveAndFlush(claim);
			if (save == null) {

				save = new ClaimApply();
				save.setError(true);

			} else {
				save.setError(false);
			}


		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}
	
	

	//*********************************************Claim Trail********************************************
	
	
	@RequestMapping(value = { "/saveClaimTrail" }, method = RequestMethod.POST)
	public @ResponseBody ClaimTrail saveClaimTrail(@RequestBody ClaimTrail claim) {

		ClaimTrail save = new ClaimTrail();
		try {

			save = claimTrailRepository.saveAndFlush(claim);
			if (save == null) {

				save = new ClaimTrail();
				save.setError(true);

			} else {
				save.setError(false);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}
	//*************WS for claim approvals and updations and rejections*******************************
	
	
	      @Autowired
	      GetClaimApplyAuthwiseRepo getClaimApplyAuthwiseRepo;
	
			@RequestMapping(value = { "/getClaimApplyListForPending" }, method = RequestMethod.POST)
			public @ResponseBody List<GetClaimApplyAuthwise> getClaimApplyListForPending(@RequestParam("empId") int empId
					) {
				List<GetClaimApplyAuthwise> list = new ArrayList<GetClaimApplyAuthwise>();
				
				try {

					list = getClaimApplyAuthwiseRepo.getClaimApplyList(empId);

				} catch (Exception e) {

					e.printStackTrace();
				}

				return list;

			}
			
			
			@RequestMapping(value = { "/getClaimApplyListForInformation" }, method = RequestMethod.POST)
			public @ResponseBody List<GetClaimApplyAuthwise> getLeaveApplyListForInformation(@RequestParam("empId") int empId
				) {
				List<GetClaimApplyAuthwise> list = new ArrayList<GetClaimApplyAuthwise>();
				
				
				try {

					list = getClaimApplyAuthwiseRepo.getClaimApplyList2(empId);

				} catch (Exception e) {

					e.printStackTrace();
				}

				return list;

			}
		
			@RequestMapping(value = { "/updateClaimStatus" }, method = RequestMethod.POST)
			public @ResponseBody Info updateLeaveStatus(@RequestParam("claimId") int claimId,@RequestParam("status") int status) {

				Info info = new Info();
	         System.err.println("in updateLeaveStatus"+status+claimId);
				try {

					int delete = claimApplyRepository.updateClaimStatus(claimId,status);

					if (delete > 0) {
						info.setError(false);
						info.setMsg("updated status");
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
			
			
	
	@RequestMapping(value = { "/updateClaimTrailId" }, method = RequestMethod.POST)
	public @ResponseBody Info updateTrailId(@RequestParam("claimId") int claimId,@RequestParam("trailId") int trailId) {

		Info info = new Info();

		try {

			int delete = claimApplyRepository.updateTrailApply(claimId,trailId);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("updated status");
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
	

}
