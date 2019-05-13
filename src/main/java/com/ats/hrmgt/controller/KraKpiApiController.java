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

import com.ats.hrmgt.krakpi.model.FinancialYear;
import com.ats.hrmgt.krakpi.model.GetEmpKra;
import com.ats.hrmgt.krakpi.model.GetEmpKraKpiCount;
import com.ats.hrmgt.krakpi.model.Kpi;
import com.ats.hrmgt.krakpi.model.Kra;
import com.ats.hrmgt.krakpi.repo.FinancialYearRepo;
import com.ats.hrmgt.krakpi.repo.GetEmpKraKpiRepo;
import com.ats.hrmgt.krakpi.repo.GetEmpKraRepo;
import com.ats.hrmgt.krakpi.repo.KpiRepo;
import com.ats.hrmgt.krakpi.repo.KraRepo;
import com.ats.hrmgt.model.ClaimProof;
import com.ats.hrmgt.model.Company;
import com.ats.hrmgt.model.Info;

@RestController
public class KraKpiApiController {
	
	@Autowired
	GetEmpKraKpiRepo getEmpKraKpiRepo;
	
	@Autowired
	FinancialYearRepo  financialYearRepo;
	
	
	@RequestMapping(value = { "/getEmpKraKpiCount" }, method = RequestMethod.POST)
	public @ResponseBody List<GetEmpKraKpiCount> getEmpKraKpiCount(@RequestParam("status") int status,@RequestParam("finYrId") int finYrId) {

		List<GetEmpKraKpiCount> list = new ArrayList<GetEmpKraKpiCount>();
		try {

			if(status==1) {
				list = getEmpKraKpiRepo.getEmpKraAlloted(finYrId );
			}
			else if(status==2) {
				list = getEmpKraKpiRepo.getEmpKpiAlloted(finYrId );
			}
			else if(status==3) {
				list = getEmpKraKpiRepo.getEmpKraNotAlloted(finYrId );
				
			}else if(status==4) {
				list = getEmpKraKpiRepo.getEmpKpiNotAlloted(finYrId );
				
			}else if(status==5) {
				list = getEmpKraKpiRepo.getEmpBothAlloted(finYrId );
				
			}
			else {
				//all
				list = getEmpKraKpiRepo.getEmpAllAlloted(finYrId );
			}
		
		
			  System.err.println("emp List is:"+list.toString());
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	
	@RequestMapping(value = { "/getFinYearList" }, method = RequestMethod.GET)
	public @ResponseBody List<FinancialYear> getFinYearList() {

		List<FinancialYear> list = new ArrayList<FinancialYear>();
		try {

			list = financialYearRepo.findByDelStatusAndIsActive(1,1 );

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/getFinYearByFinYrId" }, method = RequestMethod.POST)
	public @ResponseBody FinancialYear getFinYearByFinYrId(@RequestParam("finYrId") int finYrId) {

		FinancialYear list = new FinancialYear();
		try {

			list = financialYearRepo.findByFinYrIdAndDelStatusAndIsActive(finYrId,1,1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	//***************************KRA****************************************
	
	@Autowired
	GetEmpKraRepo getEmpKraRepo;
	
	@RequestMapping(value = { "/getEmpKraList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetEmpKra> getEmpKraList(@RequestParam("empId") int empId,@RequestParam("finYrId") int finYrId) {

		List<GetEmpKra> list = new ArrayList<GetEmpKra>();
		try {

			list = getEmpKraRepo.getEmpKra(empId,finYrId );
			  System.err.println("emp List is:"+list.toString());
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@Autowired
	KraRepo kraRepo;
	
	@RequestMapping(value = { "/saveKra" }, method = RequestMethod.POST)
	public @ResponseBody Kra saveKra(@RequestBody Kra kra) {

		Kra save = new Kra();
		try {

			save = kraRepo.saveAndFlush(kra);

			if (save != null) {
				save.setError(false);
			} else {

				save = new Kra();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new Kra();
			save.setError(true);
			e.printStackTrace();
		}

		return save;
	}
	
	@RequestMapping(value = { "/getKraByKraId" }, method = RequestMethod.POST)
	public @ResponseBody Kra getKraByKraId(@RequestParam("kraId") int kraId) {

		Kra kra = new Kra();
		try {

			kra = kraRepo.findByKraIdAndDelStatus(kraId,1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return kra;

	}
	
	@RequestMapping(value = { "/deleteKra" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteKra(@RequestParam("kraId") int kraId) {

		Info info = new Info();

		try {

			int delete = kraRepo.deleteKra(kraId);

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
	
	@Autowired
	KpiRepo kpiRepo;
	
	@RequestMapping(value = { "/deleteKpiOfKra" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteKpiOfKra(@RequestParam("kraId") int kraId
			) {

		Info info = new Info();

		try {

			int delete = kpiRepo.updateKpi(kraId);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("kpi of kra deleted");
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
	
	@RequestMapping(value = { "/saveKpi" }, method = RequestMethod.POST)
	public @ResponseBody Kpi saveKpi(@RequestBody Kpi kpi) {

		Kpi save = new Kpi();
		try {

			save = kpiRepo.saveAndFlush(kpi);

			if (save != null) {
				save.setError(false);
			} else {

				save = new Kpi();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new Kpi();
			save.setError(true);
			e.printStackTrace();
		}

		return save;
	}
	
	@RequestMapping(value = { "/deleteKpi" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteKpi(@RequestParam("kpiId") int kpiId) {

		Info info = new Info();

		try {

			int delete = kpiRepo.deleteKpi(kpiId);

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
	
	
	@RequestMapping(value = { "/getKpiByKpiId" }, method = RequestMethod.POST)
	public @ResponseBody Kpi getKpiByKpiId(@RequestParam("kpiId") int kpiId) {

		Kpi kpi = new Kpi();
		try {

			kpi = kpiRepo.findByKpiIdAndDelStatus(kpiId,1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return kpi;

	}

}
