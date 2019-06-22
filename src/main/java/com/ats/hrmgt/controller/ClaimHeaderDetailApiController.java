package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.common.EmailUtility;
import com.ats.hrmgt.common.Firebase;
import com.ats.hrmgt.leave.model.GetAuthorityIds;
import com.ats.hrmgt.leave.repo.ClaimApplyRepo;
import com.ats.hrmgt.leave.repo.GetAuthorityIdsRepo;
import com.ats.hrmgt.model.ClaimApply;
import com.ats.hrmgt.model.ClaimApplyHeader;
import com.ats.hrmgt.model.EmployeeInfo;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.Setting;
import com.ats.hrmgt.repository.ClaimHeaderRepo;
import com.ats.hrmgt.repository.EmployeeInfoRepository;
import com.ats.hrmgt.repository.SettingRepo;

@RestController
public class ClaimHeaderDetailApiController {
	@Autowired
	EmployeeInfoRepository employeeInfoRepository;

	
	@Autowired
	GetAuthorityIdsRepo getAuthorityIdsRepo;
	
	@Autowired
	ClaimHeaderRepo claimHeaderRepo;
	
	@Autowired
	ClaimApplyRepo claimApplyRepo;
	
	@Autowired
	SettingRepo settingRepo;
	
	@RequestMapping(value = { "/saveClaimHeaderAndDetail" }, method = RequestMethod.POST)
	public @ResponseBody ClaimApplyHeader saveClaimHeaderAndDetail(@RequestBody ClaimApplyHeader claimHead) {

		Info errorMessage = new Info();
		ClaimApplyHeader claimHeader = new ClaimApplyHeader();
		try {

			claimHeader = claimHeaderRepo.save(claimHead);

			for (int i = 0; i < claimHead.getDetailList().size(); i++) {

				claimHead.getDetailList().get(i).setExInt2(claimHeader.getCaHeadId());

			}

			List<ClaimApply> claimDetailsList = claimApplyRepo.saveAll(claimHead.getDetailList());
			claimHeader.setDetailList(claimDetailsList);

			errorMessage.setError(false);
			
			
			int empId = claimHead.getEmpId();

			EmployeeInfo empInfo1 = new EmployeeInfo();

			empInfo1 = employeeInfoRepository.findByEmpIdAndDelStatus(empId, 1);
			String name = empInfo1.getEmpFname() + " " + empInfo1.getEmpSname();

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
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

			String claimDate = "";
			String claimDate1 = "";
			String claimDate2 = "";

			try {

				Date d1 = sdf1.parse(claimHead.getCafromDt());

				claimDate1 = sdf2.format(d1.getTime());

				Date d2 = sdf1.parse(claimHead.getCaToDt());

				claimDate2 = sdf2.format(d2.getTime());
				
				claimDate = claimDate1 + "To" + claimDate2;


			} catch (Exception e) {

				e.printStackTrace();
			}
			
			String claimMsg =name + " registered Claim for Rs. "
					+ claimHead.getClaimAmount() + " Duration: " + claimDate + " Please Check";
			for (int i = 0; i < al.size(); i++) {

			EmployeeInfo empInfo = new EmployeeInfo();

			empInfo = employeeInfoRepository.findByEmpIdAndDelStatus(Integer.parseInt(al.get(i)), 1);

			

			try {

			Firebase.sendPushNotification(
			empInfo.getExVar1(), "HRMS Claim Application", " " + name + " has applied for Claim for Rs. "
			+ claimHead.getClaimAmount() + " Duration: " + claimDate + ", Please check ",
			21);
			
			 
			
			Info emailRes = EmailUtility.sendEmail("atsinfosoft@gmail.com", "atsinfosoft@123", empInfo.getEmpEmail(),
					" HRMS Claim Application Status", claimMsg,"" );

			} catch (Exception e) {
			e.printStackTrace();
			}

			}
			
			Setting setting = new Setting();
			setting = settingRepo.findByKey("hremail");
			String hrEmail = (setting.getValue());
			System.out.println(hrEmail);
			Info emailRes = EmailUtility.sendEmail("atsinfosoft@gmail.com", "atsinfosoft@123", hrEmail,"HRMS Claim Application",
					  ""," " + name + " has applied for Claim for Rs. "
							+ claimHead.getClaimAmount() + " Duration: " + claimDate + ", Please check ");

			
 
		} catch (Exception e) {

			e.printStackTrace();
			errorMessage.setError(true);
		 

		}
		return claimHeader;

}
	
	
	
	
	@RequestMapping(value = { "/updateClaimTrailId" }, method = RequestMethod.POST)
	public @ResponseBody Info updateTrailId(@RequestParam("claimId") int claimId,
			@RequestParam("trailId") int trailId) {

		Info info = new Info();

		try {

			int delete = claimHeaderRepo.updateTrailApply(claimId, trailId);

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
