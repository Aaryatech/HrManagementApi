package com.ats.hrmgt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.model.Company;
import com.ats.hrmgt.repository.CompanyRepository;
 
@RestController
public class MasterRestController {
	
	@Autowired
	CompanyRepository companyRepository;
	
	
	@RequestMapping(value = { "/saveCompany" }, method = RequestMethod.POST)
	public @ResponseBody Company saveLoginLogs(@RequestBody Company company) {

		 
		Company save = new Company();
		try {
			 
			save = companyRepository.saveAndFlush(company);
  

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return save;

	}
	
	@RequestMapping(value = { "/getCompanyList" }, method = RequestMethod.GET)
	public @ResponseBody List<Company> getCompanyList() {

		 
		List<Company> list = new ArrayList<Company>();
		try {
			 
			list = companyRepository.findByDelStatus(1);
  

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return list;

	}
	

}
