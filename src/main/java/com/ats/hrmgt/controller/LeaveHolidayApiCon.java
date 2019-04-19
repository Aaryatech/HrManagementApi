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
import com.ats.hrmgt.leave.model.LeaveAuthority;
import com.ats.hrmgt.leave.model.LeaveStructureHeader;
import com.ats.hrmgt.leave.repo.GetHolidayRepo;
import com.ats.hrmgt.leave.repo.HolidayRepo;
import com.ats.hrmgt.model.Company;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.repository.CalculateYearRepository;
import com.ats.hrmgt.repository.LeaveAuthorityRepository;

@RestController
public class LeaveHolidayApiCon {

	@Autowired
	HolidayRepo holidayRepo;

	@Autowired
	GetHolidayRepo getHolidayRepo;

	@Autowired
	LeaveAuthorityRepository leaveAuthorityRepository;

	@Autowired
	CalculateYearRepository calculateYearRepository;

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
			@RequestParam("locIdList") List<Integer> locIdList) {

		List<GetHoliday> list = new ArrayList<GetHoliday>();
		try {

			list = getHolidayRepo.getHolidayListByCompany(companyId, locIdList);

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

	@RequestMapping(value = { "/deleteHoliday" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteHoliday(@RequestParam("holidayId") int holidayId) {

		Info info = new Info();

		try {

			int delete = holidayRepo.deleteHoliday(holidayId);

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

	@RequestMapping(value = { "/saveLeaveAuthority" }, method = RequestMethod.POST)
	public @ResponseBody LeaveAuthority saveLeaveAuthority(@RequestBody LeaveAuthority leavesAllotment) {

		LeaveAuthority save = new LeaveAuthority();
		try {

			save = leaveAuthorityRepository.saveAndFlush(leavesAllotment);

			if (save != null) {
				save.setError(false);
			} else {

				save = new LeaveAuthority();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new LeaveAuthority();
			save.setError(true);

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getLeaveAuthorityList" }, method = RequestMethod.GET)
	public @ResponseBody List<LeaveAuthority> getLeaveAuthorityList() {

		List<LeaveAuthority> list = new ArrayList<LeaveAuthority>();
		try {

			list = leaveAuthorityRepository.findByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/deleteLeaveAuthority" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLeaveAuthority(@RequestParam("laPkey") int laPkey) {

		Info info = new Info();

		try {

			int delete = leaveAuthorityRepository.deleteLeaveAuthority(laPkey);

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

	@RequestMapping(value = { "getLeaveAuthorityById" }, method = RequestMethod.POST)
	public @ResponseBody LeaveAuthority getLeaveAuthorityById(@RequestParam("laPkey") int laPkey) {

		LeaveAuthority leaveAuthority = new LeaveAuthority();
		try {

			leaveAuthority = leaveAuthorityRepository.findByLaPkeyAndDelStatus(laPkey, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveAuthority;

	}

}
