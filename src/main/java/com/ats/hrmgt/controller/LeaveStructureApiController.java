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

import com.ats.hrmgt.leave.model.GetStructureAllotment;

import com.ats.hrmgt.leave.model.LeaveStructureDetails;
import com.ats.hrmgt.leave.model.LeaveStructureHeader;

import com.ats.hrmgt.leave.repo.GetStructureAllotmentRepo;
import com.ats.hrmgt.leave.repo.LeaveStructureDetailsRepo;
import com.ats.hrmgt.leave.repo.LeaveStructureHeaderRepo;

import com.ats.hrmgt.model.Info;

@RestController
public class LeaveStructureApiController {

	@Autowired
	LeaveStructureDetailsRepo leaveStructureDetailsRepo;

	@Autowired
	LeaveStructureHeaderRepo leaveStructureHeaderRepo;

	@Autowired
	GetStructureAllotmentRepo getStructureAllotmentRepo;

	@RequestMapping(value = { "/getStructureAllotmentList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetStructureAllotment> getStructureAllotmentList(@RequestParam("companyId") int companyId,
			@RequestParam("locIdList") List<Integer> locIdList) {
		System.out.println("companyId==" + companyId + "====locIdList===" + locIdList);

		List<GetStructureAllotment> list = new ArrayList<GetStructureAllotment>();
		try {

			list = getStructureAllotmentRepo.getStructureAllotment(companyId, locIdList);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/saveLeaveStruture" }, method = RequestMethod.POST)
	public @ResponseBody LeaveStructureHeader saveLeaveStruture(
			@RequestBody LeaveStructureHeader leaveStructureHeader) {

		Info errorMessage = new Info();
		LeaveStructureHeader leaveHeader = new LeaveStructureHeader();
		try {

			leaveHeader = leaveStructureHeaderRepo.save(leaveStructureHeader);

			for (int i = 0; i < leaveStructureHeader.getDetailList().size(); i++) {

				leaveStructureHeader.getDetailList().get(i).setLvsId(leaveStructureHeader.getLvsId());

			}

			List<LeaveStructureDetails> docTermDetailsList = leaveStructureDetailsRepo
					.saveAll(leaveStructureHeader.getDetailList());
			leaveHeader.setDetailList(docTermDetailsList);

			if (leaveHeader != null) {
				leaveHeader.setError(false);
			} else {

				leaveHeader = new LeaveStructureHeader();
				leaveHeader.setError(true);
			}

		} catch (Exception e) {
			leaveHeader = new LeaveStructureHeader();
			leaveHeader.setError(true);
			e.printStackTrace();
		}

		return leaveHeader;

	}

	@RequestMapping(value = { "/getStructureList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveStructureHeader> getStructureList(@RequestParam("companyId") int companyId) {

		List<LeaveStructureHeader> list = new ArrayList<LeaveStructureHeader>();
		try {

			list = leaveStructureHeaderRepo.findByDelStatusAndCompanyId(1, companyId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getStructureById" }, method = RequestMethod.POST)
	public @ResponseBody LeaveStructureHeader getStructureById(@RequestParam("lvsId") int lvsId) {

		LeaveStructureHeader lvs = new LeaveStructureHeader();
		try {

			lvs = leaveStructureHeaderRepo.findByLvsIdAndDelStatus(lvsId, 1);

			List<LeaveStructureDetails> detailList = leaveStructureDetailsRepo.findByLvsIdAndDelStatus(lvsId, 1);
			lvs.setDetailList(detailList);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return lvs;

	}

	@RequestMapping(value = { "/deleteLeaveStructure" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLeaveStructure(@RequestParam("lvsId") int lvsId) {

		Info info = new Info();

		try {

			int delete = leaveStructureHeaderRepo.deleteLeaveStructure(lvsId);

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

}
