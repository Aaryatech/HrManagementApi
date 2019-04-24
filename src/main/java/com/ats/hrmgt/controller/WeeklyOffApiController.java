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

import com.ats.hrmgt.leave.model.GetLeaveAuthority;
import com.ats.hrmgt.model.ClaimAuthority;
import com.ats.hrmgt.model.ClaimType;
import com.ats.hrmgt.model.GetWeeklyOff;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.WeeklyOff;
import com.ats.hrmgt.repository.GetWeeklyOffRepo;
import com.ats.hrmgt.repository.WeeklyOffRepo;

@RestController
public class WeeklyOffApiController {

	@Autowired
	WeeklyOffRepo weeklyOffRepo;
	@Autowired
	GetWeeklyOffRepo getWeeklyOffRepo;

	@RequestMapping(value = { "/getWeeklyOffList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetWeeklyOff> getWeeklyOffList(@RequestParam("companyId") int companyId) {

		List<GetWeeklyOff> list = new ArrayList<GetWeeklyOff>();
		try {

			list = getWeeklyOffRepo.getListByCompanyId(companyId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/saveWeeklyOff" }, method = RequestMethod.POST)
	public @ResponseBody WeeklyOff saveWeeklyOff(@RequestBody WeeklyOff weeklyOff) {

		WeeklyOff save = new WeeklyOff();
		try {

			save = weeklyOffRepo.saveAndFlush(weeklyOff);

			if (save != null) {
				save.setError(false);
			} else {

				save = new WeeklyOff();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new WeeklyOff();
			save.setError(true);
			e.printStackTrace();
		}

		return save;
	}

	@RequestMapping(value = { "/getWeeklyOffList" }, method = RequestMethod.GET)
	public @ResponseBody List<WeeklyOff> getWeeklyOffList() {

		List<WeeklyOff> list = new ArrayList<WeeklyOff>();
		try {

			list = weeklyOffRepo.findByDelStatusAndIsActive(1, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/deleteWeeklyOff" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteWeeklyOff(@RequestParam("woId") int woId) {

		Info info = new Info();

		try {

			int delete = weeklyOffRepo.deleteWeeklyOff(woId);

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
