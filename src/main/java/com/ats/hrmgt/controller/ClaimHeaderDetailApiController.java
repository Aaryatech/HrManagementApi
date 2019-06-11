package com.ats.hrmgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.leave.repo.ClaimApplyRepo;
import com.ats.hrmgt.model.ClaimApply;
import com.ats.hrmgt.model.ClaimApplyHeader;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.repository.ClaimHeaderRepo;

@RestController
public class ClaimHeaderDetailApiController {
	
	
	@Autowired
	ClaimHeaderRepo claimHeaderRepo;
	
	@Autowired
	ClaimApplyRepo claimApplyRepo;
	
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
