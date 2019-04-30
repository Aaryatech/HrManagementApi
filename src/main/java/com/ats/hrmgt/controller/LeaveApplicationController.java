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

import com.ats.hrmgt.leave.model.GetAuthorityIds;
import com.ats.hrmgt.leave.model.GetLeaveApplyAuthwise;
import com.ats.hrmgt.leave.model.LeaveHistory;
import com.ats.hrmgt.leave.repo.GetAuthorityIdsRepo;
import com.ats.hrmgt.leave.repo.GetEmployeeAuthorityWiseRepo;
import com.ats.hrmgt.leave.repo.GetLeaveApplyAuthwiseRepo;
import com.ats.hrmgt.leave.repo.LeaveHistoryRepo;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveApply;
import com.ats.hrmgt.model.LeaveTrail;
import com.ats.hrmgt.model.LeaveType;
import com.ats.hrmgt.repository.LeaveApplyRepository;
import com.ats.hrmgt.repository.LeaveTrailRepository;

@RestController
public class LeaveApplicationController {
	
	
	@Autowired
	LeaveHistoryRepo leaveHistoryRepo;
	@Autowired
	LeaveTrailRepository leaveTrailRepository;

	@Autowired
	LeaveApplyRepository leaveApplyRepository;
	@Autowired
	GetAuthorityIdsRepo getAuthorityIdsRepo;
	@Autowired
	GetEmployeeAuthorityWiseRepo getEmployeeAuthorityWise;
	
	
	@Autowired
	GetLeaveApplyAuthwiseRepo getLeaveApplyAuthwiseRepo;
	
	@RequestMapping(value = { "/getLeaveHistoryList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveHistory> getLeaveHistoryList(@RequestParam("empId") int empId,@RequestParam("currYrId") int currYrId) {

		 
		List<LeaveHistory> list = new ArrayList<LeaveHistory>();
		try {
			 
			
				list = leaveHistoryRepo.getLeaveHistoryByEmpId(empId,currYrId);
			
				System.err.println("LeaveHistory"+list.toString());

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return list;

	}
	
	
	@RequestMapping(value = { "/getLeaveHistoryByLeaveTypeId" }, method = RequestMethod.POST)
	public @ResponseBody LeaveHistory getLeaveHistoryByLeaveTyprId(@RequestParam("leaveTypeId") int leaveTypeId,@RequestParam("lvsId") int lvsId) {

		 
	LeaveHistory list = new LeaveHistory();
		try {
			 
			
				list = leaveHistoryRepo.getLeaveEarnedByLeaveTypeId(leaveTypeId,lvsId);
			
				System.err.println("LeaveHistory"+list.toString());

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return list;

	}
	@RequestMapping(value = { "/saveLeaveTrail" }, method = RequestMethod.POST)
	public @ResponseBody LeaveTrail saveLeaveTrail(@RequestBody LeaveTrail leaveTrail) {

		LeaveTrail save = new LeaveTrail();
		try {

			save = leaveTrailRepository.saveAndFlush(leaveTrail);
			
			if (save == null) {

				save = new LeaveTrail();
				save.setError(true);

			} else {
				save.setError(false);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}///

	/*
	 * @RequestMapping(value = { "/getLeaveTrailList" }, method = RequestMethod.GET)
	 * public @ResponseBody List<LeaveTrail> getLeaveTrailList() {
	 * 
	 * List<LeaveTrail> list = new ArrayList<LeaveTrail>(); try {
	 * 
	 * list = leaveTrailRepository.findByDelStatus(1);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return list;
	 * 
	 * }
	 */
	/*
	 * @RequestMapping(value = { "/deleteLeaveTrail" }, method = RequestMethod.POST)
	 * public @ResponseBody Info deleteLeaveTrail(@RequestParam("trailPkey") int
	 * trailPkey) {
	 * 
	 * Info info = new Info();
	 * 
	 * try {
	 * 
	 * int delete = leaveTrailRepository.deleteLeaveTrail(trailPkey);
	 * 
	 * if (delete > 0) { info.setError(false); info.setMsg("deleted"); } else {
	 * info.setError(true); info.setMsg("failed"); }
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); info.setError(true); info.setMsg("failed"); }
	 * 
	 * return info;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = { "/getLeaveTrailById" }, method =
	 * RequestMethod.POST) public @ResponseBody LeaveTrail
	 * getLeaveTrailById(@RequestParam("trailPkey") int trailPkey) {
	 * 
	 * LeaveTrail leaveTrail = new LeaveTrail(); try {
	 * 
	 * leaveTrail = leaveTrailRepository.findByTrailPkeyAndDelStatus(trailPkey, 1);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return leaveTrail;
	 * 
	 * }
	 */
	@RequestMapping(value = { "/saveLeaveApply" }, method = RequestMethod.POST)
	public @ResponseBody LeaveApply saveLeaveApply(@RequestBody LeaveApply leave) {

		LeaveApply save = new LeaveApply();
		try {

			save = leaveApplyRepository.saveAndFlush(leave);
			if (save == null) {

				save = new LeaveApply();
				save.setError(true);

			} else {
				save.setError(false);
				int empId=save.getEmpId();
				
				
				
				
				
			}


		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getLeaveApplyList" }, method = RequestMethod.GET)
	public @ResponseBody List<LeaveApply> getLeaveApplyList() {

		List<LeaveApply> list = new ArrayList<LeaveApply>();
		try {

			list = leaveApplyRepository.findByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	
	

	@RequestMapping(value = { "/deleteLeaveApply" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLeaveApply(@RequestParam("leaveId") int leaveId) {

		Info info = new Info();

		try {

			int delete = leaveApplyRepository.deleteLeaveApply(leaveId);

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

	

	@RequestMapping(value = { "getLeaveApplyById" }, method = RequestMethod.POST)
	public @ResponseBody LeaveApply getLeaveApplyById(@RequestParam("leaveId") int leaveId) {

		LeaveApply leaveApply = new LeaveApply();
		try {

			leaveApply = leaveApplyRepository.findByLeaveIdAndDelStatus(leaveId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveApply;

	}
	

	@RequestMapping(value = { "getAuthIdByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody GetAuthorityIds getAuthIdByEmpId(@RequestParam("empId") int empId) {

		System.out.println("emp id is "+empId);
		GetAuthorityIds leaveApply = new GetAuthorityIds();
		try {

			leaveApply = getAuthorityIdsRepo.getAuthIds(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveApply;

	}
	

	
	   //*************WS for leave approvals and updations and rejections*******************************
	
		@RequestMapping(value = { "/getLeaveApplyListForPending" }, method = RequestMethod.POST)
		public @ResponseBody List<GetLeaveApplyAuthwise> getLeaveApplyAuthwiseList(@RequestParam("empId") int empId,
				@RequestParam("currYrId") int currYrId) {
			List<GetLeaveApplyAuthwise> list = new ArrayList<GetLeaveApplyAuthwise>();
			
			try {

				list = getLeaveApplyAuthwiseRepo.getLeaveApplyList(empId,currYrId);

			} catch (Exception e) {

				e.printStackTrace();
			}

			return list;

		}
		
		
		@RequestMapping(value = { "/getLeaveApplyListForInformation" }, method = RequestMethod.POST)
		public @ResponseBody List<GetLeaveApplyAuthwise> getLeaveApplyListForInformation(@RequestParam("empId") int empId,
				@RequestParam("currYrId") int currYrId) {
			List<GetLeaveApplyAuthwise> list = new ArrayList<GetLeaveApplyAuthwise>();
			
			
			try {

				list = getLeaveApplyAuthwiseRepo.getLeaveApplyList2(empId,currYrId);

			} catch (Exception e) {

				e.printStackTrace();
			}

			return list;

		}
	
		@RequestMapping(value = { "/updateLeaveStatus" }, method = RequestMethod.POST)
		public @ResponseBody Info updateLeaveStatus(@RequestParam("leaveId") int leaveId,@RequestParam("status") int status) {

			Info info = new Info();
         System.err.println("in updateLeaveStatus"+status+leaveId);
			try {

				int delete = leaveApplyRepository.updateLeaveStatus(leaveId,status);

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
		
		
		@RequestMapping(value = { "/updateTrailId" }, method = RequestMethod.POST)
		public @ResponseBody Info updateTrailId(@RequestParam("leaveId") int leaveId,@RequestParam("trailId") int trailId) {

			Info info = new Info();

			try {

				int delete = leaveApplyRepository.updateLeaveApply(leaveId,trailId);

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
