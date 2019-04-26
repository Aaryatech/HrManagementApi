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

import com.ats.hrmgt.leave.model.ClaimDetail;
import com.ats.hrmgt.leave.model.GetClaimTrailStatus;
import com.ats.hrmgt.leave.model.GetLeaveStatus;
import com.ats.hrmgt.leave.model.LeaveDetail;
import com.ats.hrmgt.leave.repo.ClaimApplyRepo;
import com.ats.hrmgt.leave.repo.ClaimDetailRepo;
import com.ats.hrmgt.leave.repo.ClaimRepository;
import com.ats.hrmgt.leave.repo.GetClaimTrailStatusRepo;
import com.ats.hrmgt.leave.repo.GetLeaveStatusRepo;
import com.ats.hrmgt.leave.repo.LeaveDetailRepo;
import com.ats.hrmgt.leave.repo.ProjectRepository;
import com.ats.hrmgt.model.CalenderYear;
import com.ats.hrmgt.model.DashboardCount;
import com.ats.hrmgt.model.EmployeeInfo;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveApply;
import com.ats.hrmgt.model.ProjectType;
import com.ats.hrmgt.repository.CalculateYearRepository;
import com.ats.hrmgt.repository.DashboardRepo;
import com.ats.hrmgt.repository.EmployeeInfoRepository;

@RestController
public class MasterAppController {
	@Autowired
	ClaimRepository claimRepository;

	@Autowired
	EmployeeInfoRepository employeeInfoRepository;

	@Autowired
	ClaimApplyRepo claimAuthorityRepo;

	@Autowired
	LeaveDetailRepo leaveDetailRepo;

	@Autowired
	GetLeaveStatusRepo getLeaveStatusRepo;

	@Autowired
	DashboardRepo dashboardRepo;

	@Autowired
	ClaimDetailRepo claimDetailRepo;

	@Autowired
	GetClaimTrailStatusRepo getClaimTrailStatusRepo;

	@Autowired
	CalculateYearRepository calculateYearRepository;

	@RequestMapping(value = { "/updateClaimStatusApp" }, method = RequestMethod.POST)
	public @ResponseBody Info updateClaimStatusApp(@RequestParam("empId") int empId, @RequestParam("claimId") int claimId,
			@RequestParam("status") int status,

			@RequestParam("remark") String remark) {

		Info info = new Info();

		try {

			int updateStatus = claimAuthorityRepo.updateClaimStatus(empId, claimId, status, remark);

			if (updateStatus > 0) {
				info.setError(false);
				info.setMsg("Updated Successfully");
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

	@RequestMapping(value = { "/getLeaveDetailList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveDetail> getLeaveDetailList(@RequestParam("empId") int empId) {

		List<LeaveDetail> list = new ArrayList<LeaveDetail>();
		try {

			list = leaveDetailRepo.getLeaveDetailByEmpId(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getLeaveStatusList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveDetail> getLeaveStatuslist(@RequestParam("empId") int empId,
			@RequestParam("status") List<Integer> status) {

		List<LeaveDetail> resList = new ArrayList<LeaveDetail>();
		// List<LeaveDetail> result = new ArrayList<LeaveDetail>();

		try {

			CalenderYear calendearYear = new CalenderYear();
			calendearYear = calculateYearRepository.findByIsCurrent(1);

			int curYrId = 0;
			if (calendearYear != null) {
				curYrId = calendearYear.getCalYrId();
			}
			//System.err.println("list "+leaveDetailRepo.getLeaveStatus1(empId, status, curYrId));
System.err.println("curYrId " +curYrId);
			resList = leaveDetailRepo.getLeaveStatus1(empId, status, curYrId);
System.err.println("list "+resList.toString());
			if (resList != null) {
				for (int i = 0; i < resList.size(); i++) {
					List<GetLeaveStatus> leaveStatus = new ArrayList<GetLeaveStatus>();
					leaveStatus = getLeaveStatusRepo.getLeaveTrailByLeaveId(resList.get(i).getLeaveId());
					resList.get(i).setGetLeaveStatusList(leaveStatus);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return resList;

	}

	@RequestMapping(value = { "/getLeaveStatusByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveDetail> getLeaveStatusByEmpId(@RequestParam("empId") int empId) {

		List<LeaveDetail> list = new ArrayList<LeaveDetail>();
		try {

			list = leaveDetailRepo.getLeaveStatusByEmpId(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getEmployeeListByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<EmployeeInfo> getEmployeeListByEmpId(@RequestParam("empId") int empId) {

		List<EmployeeInfo> list = new ArrayList<EmployeeInfo>();
		try {

			list = employeeInfoRepository.getEmployeeListByEmpId(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getEmployeeLeaveByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody LeaveDetail getEmployeeLeaveByEmpId(@RequestParam("empId") int empId,
			@RequestParam("statusList") List<Integer> statusList, @RequestParam("currYrId") int currYrId) {

		LeaveDetail list = new LeaveDetail();
		try {

			list = leaveDetailRepo.getLeaveStatus(empId, statusList, currYrId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getDashboardCount" }, method = RequestMethod.POST)
	public @ResponseBody DashboardCount getDashboardCount(@RequestParam("empId") int empId) {

		DashboardCount dashboardCount = new DashboardCount();
		try {

			CalenderYear calendearYear = new CalenderYear();
			calendearYear = calculateYearRepository.findByIsCurrent(1);

			int curYrId = 0;
			if (calendearYear != null) {
				curYrId = calendearYear.getCalYrId();
			}

			dashboardCount = dashboardRepo.getDashboardCount(empId, curYrId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return dashboardCount;

	}

	@RequestMapping(value = { "/getLeaveTrailList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetLeaveStatus> getLeaveTrailList(@RequestParam("leaveId") int leaveId) {

		List<GetLeaveStatus> leaveStatus = new ArrayList<GetLeaveStatus>();
		try {
			leaveStatus = getLeaveStatusRepo.getLeaveTrailByLeaveId(leaveId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveStatus;

	}

	@RequestMapping(value = { "/getEmpListForClaimAuthByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<EmployeeInfo> getEmpListForClaimAuthByEmpId(@RequestParam("empId") int empId) {

		List<EmployeeInfo> list = new ArrayList<EmployeeInfo>();
		try {

			list = employeeInfoRepository.getEmpListForClaimByEmpId(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getClaimStatusList" }, method = RequestMethod.POST)
	public @ResponseBody List<ClaimDetail> getClaimStatusList(@RequestParam("empId") int empId,
			@RequestParam("status") List<Integer> status) {

		List<ClaimDetail> list = new ArrayList<ClaimDetail>();
		// List<LeaveDetail> result = new ArrayList<LeaveDetail>();

		try {

			list = claimDetailRepo.getClaimStatus(empId, status);
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

	@RequestMapping(value = { "getEmpInfoByLocId" }, method = RequestMethod.POST)
	public @ResponseBody List<EmployeeInfo> getEmpInfoByLocId(@RequestParam("locationId") List<Integer> locationId) {

		List<EmployeeInfo> employeeInfo = new ArrayList<EmployeeInfo>();

		try {

			employeeInfo = employeeInfoRepository.getEmpInfoByLocId(locationId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return employeeInfo;

	}
 
	@RequestMapping(value = { "getLeaveListByLocIdAndEmp" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveDetail> getLeaveListByLocIdAndEmp(@RequestParam("empId") int empId,
			@RequestParam("calYrId") int calYrId) {

		List<LeaveDetail> employeeInfo = new ArrayList<LeaveDetail>();

		try {

			employeeInfo = leaveDetailRepo.getLeaveListByLocIdAndEmp(empId, calYrId, 1);
  
		} catch (Exception e) {

			e.printStackTrace();
		} 

		return employeeInfo; 

	}
	 
	@RequestMapping(value = { "/getEmpInfoListByTrailEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetLeaveStatus> getEmpInfoListByTrailEmpId(@RequestParam("leaveId") int leaveId) {

		List<GetLeaveStatus> leaveStatus = new ArrayList<GetLeaveStatus>();
		try {
			leaveStatus = getLeaveStatusRepo.getEmpInfoByLeaveId(leaveId);

		} catch (Exception e) { 

			e.printStackTrace();
		}

		return leaveStatus;

	}

}
