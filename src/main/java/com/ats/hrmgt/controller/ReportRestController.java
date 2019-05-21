package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.ActivityRevenueReport;
import com.ats.hrmgt.model.EmployeeOnBenchReport;
import com.ats.hrmgt.repository.ActivityRevenueReportRepository;
import com.ats.hrmgt.repository.EmployeeOnBenchRepository;

@RestController
public class ReportRestController {

	
	@Autowired
	EmployeeOnBenchRepository employeeOnBenchRepository;
	
	@Autowired
	ActivityRevenueReportRepository activityRevenueReportRepository;
	
	
	@RequestMapping(value = { "/getOnBenchReport" }, method = RequestMethod.POST)
	public @ResponseBody List<EmployeeOnBenchReport> getOnBenchReport(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("locIds") List<Integer> locIds) {

		List<EmployeeOnBenchReport> list = new ArrayList<>();
		try {

			list = employeeOnBenchRepository.getOnBenchReport(fromDate,toDate,locIds);
			 

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/revenueReportProjectCategoryWise" }, method = RequestMethod.POST)
	public @ResponseBody List<ActivityRevenueReport> revenueReportProjectCategoryWise(@RequestParam("compId") int compId) {

		List<ActivityRevenueReport> list = new ArrayList<>();
		try {

			list = activityRevenueReportRepository.revenueReportProjectCategoryWise(compId);
			 

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	/*select ph.project_id,ph.project_title,ph.ex_int1,sum(ph.ex_var1) as revenue ,coalesce((select sum(pallot_daily_hrs*(DATEDIFF ( pallot_todt,pallot_fromdt)+1)) 
	 * from project_allotment,project_header  where project_header.project_type_id =ph.project_type_id and project_header.project_id=project_allotment.project_id),0) 
	 * as resource_cost,pt.project_type_title,pt.project_type_title_short from project_header ph,project_type pt where pt.project_type_id=ph.project_type_id  group by ph.project_type_id */
}
