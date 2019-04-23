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

import com.ats.hrmgt.leave.model.GetEmployeeAuthorityWise;
import com.ats.hrmgt.leave.model.GetLeaveApplyAuthwise;
import com.ats.hrmgt.leave.model.LeaveHistory;
import com.ats.hrmgt.leave.repo.GetEmployeeAuthorityWiseRepo;
import com.ats.hrmgt.leave.repo.GetLeaveApplyAuthwiseRepo;
import com.ats.hrmgt.leave.repo.LeaveHistoryRepo;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveApply;
import com.ats.hrmgt.model.LeaveSummary;
import com.ats.hrmgt.model.LeaveTrail;
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
	GetEmployeeAuthorityWiseRepo getEmployeeAuthorityWise;
	
	
	@Autowired
	GetLeaveApplyAuthwiseRepo getLeaveApplyAuthwiseRepo;
	
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
	@RequestMapping(value = { "/saveLeaveTrail" }, method = RequestMethod.POST)
	public @ResponseBody LeaveTrail saveLeaveTrail(@RequestBody LeaveTrail leaveTrail) {

		LeaveTrail save = new LeaveTrail();
		try {

			save = leaveTrailRepository.saveAndFlush(leaveTrail);

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
	

	
	   //*************WS for leave approvals and updations and rejections*******************************
	
		@RequestMapping(value = { "/getLeaveApplyListForAuth" }, method = RequestMethod.GET)
		public @ResponseBody List<GetLeaveApplyAuthwise> getLeaveApplyAuthwiseList(@RequestParam("empId") int empId,@RequestParam("status") int status) {

			List<GetLeaveApplyAuthwise> list = new ArrayList<GetLeaveApplyAuthwise>();
			
				List<GetEmployeeAuthorityWise> empIdList = new ArrayList<GetEmployeeAuthorityWise>();
			
			empIdList=getEmployeeAuthorityWise.getEmpIdList(empId);
			
			System.err.println("empIdList"+empIdList.size());
			try {

				list = getLeaveApplyAuthwiseRepo.getLeaveApplyList(empIdList,status);

			} catch (Exception e) {

				e.printStackTrace();
			}

			return list;

		}
		
		
		@RequestMapping(value = { "/updateLeaveStatus" }, method = RequestMethod.POST)
		public @ResponseBody Info updateLeaveStatus(@RequestParam("leaveId") int leaveId,@RequestParam("status") int status) {

			Info info = new Info();

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
					info.setMsg("updated");
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
