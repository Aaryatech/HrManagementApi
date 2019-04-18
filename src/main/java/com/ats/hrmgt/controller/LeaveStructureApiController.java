package com.ats.hrmgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.leave.model.LeaveStructureDetails;
import com.ats.hrmgt.leave.model.LeaveStructureHeader;
import com.ats.hrmgt.leave.repo.LeaveStructureDetailsRepo;
import com.ats.hrmgt.leave.repo.LeaveStructureHeaderRepo;
import com.ats.hrmgt.model.Info;

@RestController
public class LeaveStructureApiController {

	@Autowired
	LeaveStructureDetailsRepo leaveStructureDetailsRepo;

	@Autowired
	LeaveStructureHeaderRepo leaveStructureHeaderRepo;

	@RequestMapping(value = { "/saveLeaveStruture" }, method = RequestMethod.POST)
	public @ResponseBody LeaveStructureHeader saveLeaveStruture(
			@RequestBody LeaveStructureHeader leaveStructureHeader) {

		Info errorMessage = new Info();
		LeaveStructureHeader leaveHeader = new LeaveStructureHeader();
		try {

			leaveHeader = leaveStructureHeaderRepo.save(leaveStructureHeader);

			for (int i = 0; i < leaveHeader.getDetailList().size(); i++) {

				leaveStructureHeader.getDetailList().get(i).setLvsId(leaveStructureHeader.getLvsId());

			}

			List<LeaveStructureDetails> docTermDetailsList = leaveStructureDetailsRepo
					.saveAll(leaveStructureHeader.getDetailList());
			leaveHeader.setDetailList(docTermDetailsList);

			errorMessage.setError(false);
			errorMessage.setMsg("successfully Saved ");

		} catch (Exception e) {

			e.printStackTrace();
			errorMessage.setError(true);
			errorMessage.setMsg("failed to Save ");

		}
		return leaveHeader;

	}

}
