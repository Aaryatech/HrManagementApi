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

import com.ats.hrmgt.common.DateConvertor;
import com.ats.hrmgt.leave.model.GetHoliday;
import com.ats.hrmgt.leave.model.Holiday;
import com.ats.hrmgt.leave.repo.GetHolidayRepo;
import com.ats.hrmgt.leave.repo.HolidayRepo;
import com.ats.hrmgt.model.Company;

@RestController
public class LeaveHolidayApiCon {

	@Autowired
	HolidayRepo holidayRepo;

	@Autowired
	GetHolidayRepo getHolidayRepo;

	@RequestMapping(value = { "/saveHoliday" }, method = RequestMethod.POST)
	public @ResponseBody Holiday saveHoliday(@RequestBody Holiday holiday) {

		Holiday save = new Holiday();
		try {

			save = holidayRepo.saveAndFlush(holiday);

			if (save != null) {
				save.setError(false);
			} else {

				save = new Holiday();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new Holiday();
			save.setError(true);
			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getHolidayList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetHoliday> getHolidayList(@RequestParam("companyId") int companyId,
			@RequestParam("locId") int locId) {

		List<GetHoliday> list = new ArrayList<GetHoliday>();
		try {

			list = getHolidayRepo.getHolidayListByCompany(companyId, locId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getHolidayById" }, method = RequestMethod.POST)
	public @ResponseBody Holiday getHolidayById(@RequestParam("holidayId") int holidayId) {

		Holiday holiday = new Holiday();
		try {

			holiday = holidayRepo.findByHolidayIdAndDelStatus(holidayId, 1);
			holiday.setHolidayFromdt(DateConvertor.convertToDMY(holiday.getHolidayFromdt()));
			holiday.setHolidayTodt(DateConvertor.convertToDMY(holiday.getHolidayTodt()));

		} catch (Exception e) {

			e.printStackTrace();
		}

		return holiday;

	}

}
