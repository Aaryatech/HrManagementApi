package com.ats.hrmgt.controller;

import java.text.SimpleDateFormat;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.claim.repo.ClaimTrailRepo;
import com.ats.hrmgt.claim.repo.GetClaimApplyAuthwiseRepo;
import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.common.EmailUtility;
import com.ats.hrmgt.common.Firebase;
import com.ats.hrmgt.leave.model.ClaimDetail;
import com.ats.hrmgt.leave.model.GetAuthorityIds;
import com.ats.hrmgt.leave.model.GetClaimHead;
import com.ats.hrmgt.leave.model.GetClaimTrailStatus;
import com.ats.hrmgt.leave.model.GetEmployeeAuthorityWise;
import com.ats.hrmgt.leave.model.GetLeaveApplyAuthwise;
import com.ats.hrmgt.leave.model.GetLeaveStatus;
import com.ats.hrmgt.leave.model.LeaveDetail;
import com.ats.hrmgt.leave.repo.ClaimApplyRepo;
import com.ats.hrmgt.leave.repo.ClaimDetailRepo;
import com.ats.hrmgt.leave.repo.GetAuthorityIdsRepo;
import com.ats.hrmgt.leave.repo.GetClaimHeadRepo;
import com.ats.hrmgt.leave.repo.GetClaimTrailStatusRepo;
import com.ats.hrmgt.leave.repo.GetEmployeeAuthorityWiseRepo;
import com.ats.hrmgt.leave.repo.GetLeaveStatusRepo;
import com.ats.hrmgt.leave.repo.LeaveDetailRepo;
import com.ats.hrmgt.model.ClaimApply;
import com.ats.hrmgt.model.ClaimApplyHeader;
import com.ats.hrmgt.model.ClaimTrail;
import com.ats.hrmgt.model.EmployeeInfo;
import com.ats.hrmgt.model.GetClaimApplyAuthwise;
import com.ats.hrmgt.model.GetEmployeeInfo;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.repository.ClaimHeaderRepo;
import com.ats.hrmgt.repository.EmployeeInfoRepository;
import com.ats.hrmgt.repository.GetEmpInfoRepo;
import com.ats.hrmgt.repository.SettingRepo;

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

	@Autowired
	GetClaimHeadRepo getClaimHeadRepo;

	@Autowired
	ClaimHeaderRepo claimHeaderRepo;
	/*
	 * @RequestMapping(value = { "/getClaimListByEmpId" }, method =
	 * RequestMethod.POST) public @ResponseBody List<ClaimDetail>
	 * getClaimStatusList(@RequestParam("empId") int empId) {
	 * 
	 * List<ClaimDetail> list = new ArrayList<ClaimDetail>();
	 * 
	 * try {
	 * 
	 * list = claimDetailRepo.getClaimList(empId); if (list != null) { for (int i =
	 * 0; i < list.size(); i++) { List<GetClaimTrailStatus> leaveStatus = new
	 * ArrayList<GetClaimTrailStatus>(); leaveStatus =
	 * getClaimTrailStatusRepo.getClaimTrailByClaimId(list.get(i).getClaimId());
	 * list.get(i).setGetClaimTrailStatus(leaveStatus); } }
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return list;
	 * 
	 * }
	 */

	static String senderEmail = "atsinfosoft@gmail.com";
	static String senderPassword = "atsinfosoft@123";
	static String mailsubject = " HRMS Password Recovery";

	@RequestMapping(value = { "/getClaimHeadListByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetClaimHead> getClaimHeadListByEmpId(@RequestParam("empId") int empId) {

		List<GetClaimHead> list = new ArrayList<GetClaimHead>();

		try {

			System.err.println("empId************************" + empId);
			list = getClaimHeadRepo.getClaimHeadByEmpId(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getClaimDetailListByEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<ClaimDetail> getClaimDetailListByEmpId(@RequestParam("claimId") int claimId) {

		List<ClaimDetail> list = new ArrayList<ClaimDetail>();

		try {

			list = claimDetailRepo.getClaimDetailList(claimId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getEmpClaimInfoListByTrailEmpId" }, method = RequestMethod.POST)
	public @ResponseBody List<GetClaimTrailStatus> getEmpClaimInfoListByTrailEmpId(
			@RequestParam("claimId") int claimId) {

		List<GetClaimTrailStatus> leaveStatus = new ArrayList<GetClaimTrailStatus>();
		try {
			leaveStatus = getClaimTrailStatusRepo.getClaimTrailByClaimId(claimId);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveStatus;

	}

	@RequestMapping(value = { "/getEmpInfoClaimAuthWise" }, method = RequestMethod.POST)
	public @ResponseBody List<GetEmployeeInfo> getEmpInfoClaimAuthWise(@RequestParam("companyId") int companyId,
			@RequestParam("empId") int empId) {

		List<GetEmployeeInfo> list = new ArrayList<GetEmployeeInfo>();

		List<GetEmployeeAuthorityWise> empIdList = new ArrayList<GetEmployeeAuthorityWise>();

		empIdList = getEmployeeAuthorityWise.getEmpIdListInClaimAuth(empId);

		System.err.println("empIdList" + empIdList.size());
		try {

			list = getEmpInfo.getEmpIdListByCompanyIdForClaim(companyId, empIdList);

			System.err.println("GetEmployeeAuthorityWise::::" + list.size());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "getClaimAuthIds" }, method = RequestMethod.POST)
	public @ResponseBody GetAuthorityIds getClaimAuthIds(@RequestParam("empId") int empId,
			@RequestParam("companyId") int companyId) {

		System.out.println("emp id is " + empId);
		GetAuthorityIds leaveApply = new GetAuthorityIds();
		try {

			leaveApply = getAuthorityIdsRepo.getClaimAuthIds(empId, companyId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveApply;

	}

	// *********************************************Claim
	// Apply********************************************
	@Autowired
	EmployeeInfoRepository employeeInfoRepository;

	/*
	 * @RequestMapping(value = { "/saveClaimApply" }, method = RequestMethod.POST)
	 * public @ResponseBody ClaimApply saveClaimApply(@RequestBody ClaimApply claim)
	 * {
	 * 
	 * ClaimApply save = new ClaimApply(); try {
	 * 
	 * save = claimApplyRepository.saveAndFlush(claim); if (save == null) {
	 * 
	 * save = new ClaimApply(); save.setError(true);
	 * 
	 * } else { save.setError(false); int empId = save.getEmpId();
	 * 
	 * EmployeeInfo empInfo1 = new EmployeeInfo();
	 * 
	 * empInfo1 = employeeInfoRepository.findByEmpIdAndDelStatus(empId, 1); String
	 * name = empInfo1.getEmpFname() + " " + empInfo1.getEmpSname();
	 * 
	 * GetAuthorityIds claimApply = new GetAuthorityIds(); claimApply =
	 * getAuthorityIdsRepo.getClaimAuthIdsDict(empId);
	 * 
	 * String empIds = claimApply.getRepToEmpIds(); String[] values =
	 * empIds.split(","); System.err.println("emp ids for notification are::" +
	 * empIds); List<String> al = new ArrayList<String>(Arrays.asList(values));
	 * 
	 * Set<String> set = new HashSet<>(al); al.clear(); al.addAll(set);
	 * System.err.println("emp ids for notification are:--------------:" +
	 * al.toString());
	 * 
	 * for (int i = 0; i < al.size(); i++) {
	 * 
	 * EmployeeInfo empInfo = new EmployeeInfo();
	 * 
	 * empInfo =
	 * employeeInfoRepository.findByEmpIdAndDelStatus(Integer.parseInt(al.get(i)),
	 * 1);
	 * 
	 * SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); SimpleDateFormat
	 * sdf2 = new SimpleDateFormat("dd-MM-yyyy");
	 * 
	 * String claimDate = "";
	 * 
	 * try {
	 * 
	 * Date d1 = sdf1.parse(save.getClaimDate());
	 * 
	 * claimDate = sdf2.format(d1.getTime());
	 * 
	 * } catch (Exception e) { claimDate = save.getClaimDate();
	 * 
	 * e.printStackTrace(); }
	 * 
	 * try {
	 * 
	 * Firebase.sendPushNotification( empInfo.getExVar1(), "HRMS", " " + name +
	 * " has applied for Claim for Rs. " + save.getClaimAmount() + " on " +
	 * claimDate + ", Please check for Approval", 21);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return save;
	 * 
	 * }
	 */
	@RequestMapping(value = { "getClaimApplyByClaimId" }, method = RequestMethod.POST)
	public @ResponseBody ClaimApply getClaimApplyByClaimId(@RequestParam("claimId") int claimId) {

		ClaimApply leaveApply = new ClaimApply();
		try {

			leaveApply = claimApplyRepository.findByClaimIdAndDelStatus(claimId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveApply;

	}

	// *********************************************Claim
	// Trail********************************************

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
	// *************WS for claim approvals and updations and
	// rejections*******************************

	@Autowired
	GetClaimApplyAuthwiseRepo getClaimApplyAuthwiseRepo;

	@RequestMapping(value = { "/getClaimApplyListForPending" }, method = RequestMethod.POST)
	public @ResponseBody List<GetClaimApplyAuthwise> getClaimApplyListForPending(@RequestParam("empId") int empId) {
		List<GetClaimApplyAuthwise> list = new ArrayList<GetClaimApplyAuthwise>();

		try {

			list = getClaimApplyAuthwiseRepo.getClaimApplyList(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getClaimApplyListForInformation" }, method = RequestMethod.POST)
	public @ResponseBody List<GetClaimApplyAuthwise> getLeaveApplyListForInformation(@RequestParam("empId") int empId) {
		List<GetClaimApplyAuthwise> list = new ArrayList<GetClaimApplyAuthwise>();

		try {

			list = getClaimApplyAuthwiseRepo.getClaimApplyList2(empId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@Autowired
	SettingRepo settingRepo;

	@RequestMapping(value = { "/updateClaimStatus" }, method = RequestMethod.POST)
	public @ResponseBody Info updateLeaveStatus(@RequestParam("claimId") int claimId,
			@RequestParam("status") int status) {

		Info info = new Info();
		System.err.println("in updateclaimStatus" + status + claimId);
		try {

			int delete = claimHeaderRepo.updateClaimStatus(claimId, status);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("updated status");

				ClaimApplyHeader leaveApply = new ClaimApplyHeader();

				leaveApply = claimHeaderRepo.findByCaHeadIdAndDelStatus(claimId, 1);
				int empId = leaveApply.getEmpId();

				// empInfo = employeeInfoRepository.findByEmpIdAndDelStatus(empId, 1);

				GetAuthorityIds claimApply = new GetAuthorityIds();
				claimApply = getAuthorityIdsRepo.getClaimAuthIdsDict(empId);

				String empIds = claimApply.getRepToEmpIds();
				String[] values = empIds.split(",");
				System.err.println("emp ids for notification are::" + empIds);
				List<String> al = new ArrayList<String>(Arrays.asList(values));

				Set<String> set = new HashSet<>(al);
				al.clear();
				al.addAll(set);
				System.err.println("emp ids for notification are:--------------:" + al.toString());

				EmployeeInfo emp = new EmployeeInfo();
				emp = employeeInfoRepository.findByEmpIdAndDelStatus(empId, 1);

				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

				String claimDate = "";
				String claimDate1 = "";
				String claimDate2 = "";

				try {

					Date d1 = sdf1.parse(leaveApply.getCafromDt());

					claimDate1 = sdf2.format(d1.getTime());

					Date d2 = sdf1.parse(leaveApply.getCaToDt());

					claimDate2 = sdf2.format(d2.getTime());

				} catch (Exception e) {
					claimDate = claimDate1 + "To" + claimDate2;

					e.printStackTrace();
				}

				try {
					String claimMsg = null;
					if (status == 2) {

						claimMsg = emp.getEmpFname() + " " + emp.getEmpSname() + " your Claim for Rs. "
								+ leaveApply.getClaimAmount() + " Duration: " + claimDate + " Approved By Initial Authority";

					} else if (status == 3) {

						claimMsg = emp.getEmpFname() + " " + emp.getEmpSname() + " your Claim for Rs. "
								+ leaveApply.getClaimAmount() + " Duration: " + claimDate + " Approved By Final Authority";

					} else if (status == 8) {

						claimMsg = emp.getEmpFname() + " " + emp.getEmpSname() + " your Claim for Rs. "
								+ leaveApply.getClaimAmount() + " Duration: " + claimDate + " Rejected By Initial Authority";

					} else if (status == 9) {

						claimMsg = emp.getEmpFname() + " " + emp.getEmpSname() + " your Claim for Rs. "
								+ leaveApply.getClaimAmount() + " Duration: " + claimDate + " Rejected By Final Authority";

					}

					Firebase.sendPushNotification(emp.getExVar1(), "HRMS", claimMsg, 2);

					Setting setting = new Setting();
					setting = settingRepo.findByKey("hremail");
					String hrEmail = (setting.getValue());
					System.out.println(hrEmail);
					Info emailRes = EmailUtility.sendEmail("atsinfosoft@gmail.com", "atsinfosoft@123", hrEmail,
							" HRMS Claim Application Status", "", claimMsg);
				} catch (Exception e) {
					e.printStackTrace();
				}

				for (int i = 0; i < al.size(); i++) {

					EmployeeInfo empInfo = new EmployeeInfo();

					empInfo = employeeInfoRepository.findByEmpIdAndDelStatus(Integer.parseInt(al.get(i)), 1);

					// Firebase.sendPushNotification(empInfo.getExVar1(), "HRMS",
					// " " + empInfo.getEmpFname()+" "+empInfo.getEmpSname() + " has applied for
					// Claim Please check for Approval", 1);

					try {

						String claimMsg = new String();
						if (status == 2) {

							claimMsg = emp.getEmpFname() + " " + emp.getEmpSname() + " Claim for Rs. "
									+ leaveApply.getClaimAmount() + " Duration: " + claimDate
									+ " Approved By Initial Authority";

						} else if (status == 3) {

							claimMsg = emp.getEmpFname() + " " + emp.getEmpSname() + " Claim for Rs. "
									+ leaveApply.getClaimAmount() + " Duration: " + claimDate + " Approved By Final Authority";

						} else if (status == 8) {

							claimMsg = emp.getEmpFname() + " " + emp.getEmpSname() + " Claim for Rs. "
									+ leaveApply.getClaimAmount() + " Duration: " + claimDate
									+ " Rejected By Initial Authority";

						} else if (status == 9) {

							claimMsg = emp.getEmpFname() + " " + emp.getEmpSname() + " Claim for Rs. "
									+ leaveApply.getClaimAmount() + " Duration: " + claimDate + " Rejected By Final Authority";

						} else if (status == 7) {

							claimMsg = emp.getEmpFname() + " " + emp.getEmpSname() + " Claim for Rs. "
									+ leaveApply.getClaimAmount() + " Duration: " + claimDate + " Cancelled";

						}

						Firebase.sendPushNotification(emp.getExVar1(), "HRMS", claimMsg, 2);

						Setting setting = new Setting();
						setting = settingRepo.findByKey("hremail");
						String hrEmail = (setting.getValue());
						System.out.println(hrEmail);
						Info emailRes = EmailUtility.sendEmail("atsinfosoft@gmail.com", "atsinfosoft@123", hrEmail,
								" HRMS Claim Application Status", "", claimMsg);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

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

	/*
	 * @RequestMapping(value = { "/getClaimApplyDetailsByClaimId" }, method =
	 * RequestMethod.POST) public @ResponseBody GetClaimApplyAuthwise
	 * getLeaveApplyDetailsByLeaveId(@RequestParam("claimId") int claimId) {
	 * GetClaimApplyAuthwise list = new GetClaimApplyAuthwise();
	 * System.out.println("inside getLeaveApplyDetailsByLeaveId");
	 * 
	 * try {
	 * 
	 * list = getClaimApplyAuthwiseRepo.getClaimApplyDetails(claimId);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return list;
	 * 
	 * }
	 */

}
