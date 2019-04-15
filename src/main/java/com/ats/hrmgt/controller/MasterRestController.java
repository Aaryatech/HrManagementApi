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

import com.ats.hrmgt.model.Company;
import com.ats.hrmgt.model.EmpType;
import com.ats.hrmgt.model.EmployeeCategory;
import com.ats.hrmgt.model.EmployeeDepartment;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.Location;
import com.ats.hrmgt.repository.CompanyRepository;
import com.ats.hrmgt.repository.EmpTypeRepository;
import com.ats.hrmgt.repository.EmployeeCategoryRepository;
import com.ats.hrmgt.repository.EmployeeDepartmentRepository;
import com.ats.hrmgt.repository.LocationRepository; 
 
@RestController
public class MasterRestController {
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	EmpTypeRepository empTypeRepository;
	
	EmployeeCategoryRepository employeeCategoryRepository;
	
	EmployeeDepartmentRepository employeeDepartmentRepository;
	
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
	
	@RequestMapping(value = { "/deleteCompany" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteCompany(@RequestParam("compId") int compId) {

		 
		Info info = new Info();
		
		try {
			 
			int delete = companyRepository.deleteCompany(compId);
  
			if(delete>0) {
				info.setError(false); 
				info.setMsg("deleted");
			}else {
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
	
	@RequestMapping(value = { "/getCompanyById" }, method = RequestMethod.POST)
	public @ResponseBody Company getCompanyById(@RequestParam("compId") int compId) {

		 
		Company company = new Company();
		try {
			 
			company = companyRepository.findByCompanyIdAndDelStatus(compId,1);
  

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return company;

	}
	
	@RequestMapping(value = { "/saveLocation" }, method = RequestMethod.POST)
	public @ResponseBody Location saveLocation(@RequestBody Location location) {

		 
		Location save = new Location();
		try {
			 
			save = locationRepository.saveAndFlush(location);
  

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return save;

	}
	
	@RequestMapping(value = { "/getLocationList" }, method = RequestMethod.GET)
	public @ResponseBody List<Location> getLocationList() {

		 
		List<Location> list = new ArrayList<Location>();
		try {
			 
			list = locationRepository.findByDelStatus(1);
  

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/deleteLocation" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLocation(@RequestParam("locId") int locId) {

		 
		Info info = new Info();
		
		try {
			 
			int delete = locationRepository.deleteLocation(locId);
  
			if(delete>0) {
				info.setError(false); 
				info.setMsg("deleted");
			}else {
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
	
	@RequestMapping(value = { "/getLocationById" }, method = RequestMethod.POST)
	public @ResponseBody Location getLocationById(@RequestParam("locId") int locId) {

		 
		Location location = new Location();
		try {
			 
			location = locationRepository.findByLocIdAndDelStatus(locId,1);
  

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return location;

	}
	
	
	@RequestMapping(value = { "/saveEmpType" }, method = RequestMethod.POST)
	public @ResponseBody EmpType saveEmpType(@RequestBody EmpType empType) {

		 
		EmpType save = new EmpType();
		try {
			 
			save = empTypeRepository.saveAndFlush(empType);
  

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return save;

	}
	
	@RequestMapping(value = { "/getEmpTypeList" }, method = RequestMethod.GET)
	public @ResponseBody List<EmpType> getEmpTypeList() {

		 
		List<EmpType> list = new ArrayList<EmpType>();
		try {
			 
			list = empTypeRepository.findByDelStatus(1);
  

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/deleteEmpType" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteEmpType(@RequestParam("empTypeId") int empTypeId) {

		 
		Info info = new Info();
		
		try {
			 
			int delete = empTypeRepository.deleteEmpType(empTypeId);
  
			if(delete>0) {
				info.setError(false); 
				info.setMsg("deleted");
			}else {
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
	
	@RequestMapping(value = { "/getEmpTypeById" }, method = RequestMethod.POST)
	public @ResponseBody Location getEmpTypeById(@RequestParam("empTypeId") int empTypeId) {

		 
		Location location = new Location();
		try {
			 
			location = empTypeRepository.findByEmpTypeIdAndDelStatus(empTypeId,1);
  

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return location;

	}
	
	@RequestMapping(value = { "/saveEmpCategory" }, method = RequestMethod.POST)
	public @ResponseBody EmployeeCategory saveEmpCategory(@RequestBody EmployeeCategory employeeCategory) {

		 
		EmployeeCategory save = new EmployeeCategory();
		try {
			 
			save = employeeCategoryRepository.saveAndFlush(employeeCategory);
  

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return save;

	}
	
	@RequestMapping(value = { "/getEmpCategoryList" }, method = RequestMethod.GET)
	public @ResponseBody List<EmployeeCategory> getEmpCategoryList() {

		 
		List<EmployeeCategory> list = new ArrayList<EmployeeCategory>();
		try {
			 
			list = employeeCategoryRepository.findByDelStatus(1);
  

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/deleteEmpCategory" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteEmpCategory(@RequestParam("empCatId") int empCatId) {

		 
		Info info = new Info();
		
		try {
			 
			int delete = employeeCategoryRepository.deleteEmpCategory(empCatId);
  
			if(delete>0) {
				info.setError(false); 
				info.setMsg("deleted");
			}else {
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
	
	@RequestMapping(value = { "/getEmpCategoryById" }, method = RequestMethod.POST)
	public @ResponseBody EmployeeCategory getEmpCategoryById(@RequestParam("empCatId") int empCatId) {

		 
		EmployeeCategory employeeCategory = new EmployeeCategory();
		try {
			 
			employeeCategory = employeeCategoryRepository.findByEmpCatIdAndDelStatus(empCatId,1);
  

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return employeeCategory;

	}
	
	@RequestMapping(value = { "/saveEmpDept" }, method = RequestMethod.POST)
	public @ResponseBody EmployeeDepartment saveEmpDept(@RequestBody EmployeeDepartment employeeDepartment) {

		 
		EmployeeDepartment save = new EmployeeDepartment();
		try {
			 
			save = employeeDepartmentRepository.saveAndFlush(employeeDepartment);
  

		} catch (Exception e) {
 
			e.printStackTrace();
		}

		return save;

	}
	 

}
