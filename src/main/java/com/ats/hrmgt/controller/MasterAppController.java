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

import com.ats.hrmgt.leave.model.GetLeaveStatus;
import com.ats.hrmgt.leave.model.LeaveDetail;
import com.ats.hrmgt.leave.repo.ClaimApplyRepo;
import com.ats.hrmgt.leave.repo.ClaimRepository;
import com.ats.hrmgt.leave.repo.GetLeaveStatusRepo;
import com.ats.hrmgt.leave.repo.LeaveDetailRepo;
import com.ats.hrmgt.leave.repo.ProjectRepository;
import com.ats.hrmgt.model.DashboardCount;
import com.ats.hrmgt.model.EmployeeInfo;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.ProjectType;
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
	ProjectRepository projectRepository;

	@Autowired
	GetLeaveStatusRepo getLeaveStatusRepo;
	
	@Autowired
	DashboardRepo dashboardRepo;

	@RequestMapping(value = { "/updateClaimStatus" }, method = RequestMethod.POST)
	public @ResponseBody Info updateClaimStatus(@RequestParam("empId") int empId, @RequestParam("claimId") int claimId,
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

	@RequestMapping(value = { "/saveProjectType" }, method = RequestMethod.POST)
	public @ResponseBody ProjectType saveProjectType(@RequestBody ProjectType projectType) {

		ProjectType save = new ProjectType();
		try {

			save = projectRepository.saveAndFlush(projectType);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getProjectList" }, method = RequestMethod.GET)
	public @ResponseBody List<ProjectType> getProjectList() {

		List<ProjectType> list = new ArrayList<ProjectType>();
		try {

			list = projectRepository.findByDelStatusOrderByProjectTypeIdDesc(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/deleteProjectType" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteProjectType(@RequestParam("projectTypeId") int projectTypeId) {

		Info info = new Info();

		try {

			int delete = projectRepository.deleteProjectType(projectTypeId);

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

	@RequestMapping(value = { "/getProjectById" }, method = RequestMethod.POST)
	public @ResponseBody ProjectType getProjectById(@RequestParam("projectTypeId") int projectTypeId) {

		ProjectType projectType = new ProjectType();
		try {

			projectType = projectRepository.findByProjectTypeIdAndDelStatus(projectTypeId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return projectType;

	}

	@RequestMapping(value = { "/getLeaveStatusList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveDetail> getLeaveStatuslist(@RequestParam("empId") int empId,
			@RequestParam("status") String status) {

		List<LeaveDetail> list = new ArrayList<LeaveDetail>();
		// List<LeaveDetail> result = new ArrayList<LeaveDetail>();

		try {

			list = leaveDetailRepo.getLeaveStatus(empId, status);
			if (list != null) {
				for (int i = 1; i < list.size(); i++) {
					List<GetLeaveStatus> leaveStatus = new ArrayList<GetLeaveStatus>();
					leaveStatus = getLeaveStatusRepo.getLeaveTrailByLeaveId(list.get(i).getLeaveId());
					list.get(i).setGetLeaveStatusList(leaveStatus);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

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
			@RequestParam("statusList") List<Integer> statusList,
			@RequestParam("currYrId") int currYrId) {

	LeaveDetail list = new LeaveDetail();
		try {

			 	list = leaveDetailRepo.getLeaveStatus(empId,statusList,currYrId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/getDashboardCount" }, method = RequestMethod.POST)
	public @ResponseBody DashboardCount getDashboardCount(@RequestParam("empId") int empId) {

		DashboardCount dashboardCount = new DashboardCount();
		try {

			dashboardCount = dashboardRepo.getDashboardCount(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return dashboardCount;

	}
}
