package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.krakpi.model.GetEmpKraKpiCount;
import com.ats.hrmgt.krakpi.repo.GetEmpKraKpiRepo;

@RestController
public class KraKpiApiController {
	
	@Autowired
	GetEmpKraKpiRepo getEmpKraKpiRepo;
	
	@RequestMapping(value = { "/getEmpKraKpiCount" }, method = RequestMethod.POST)
	public @ResponseBody List<GetEmpKraKpiCount> getEmpKraKpiCount(@RequestParam("status") int status,@RequestParam("finYrId") int finYrId) {

		List<GetEmpKraKpiCount> list = new ArrayList<GetEmpKraKpiCount>();
		try {

			list = getEmpKraKpiRepo.getEmpKpiKra(status,finYrId );

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	

}
