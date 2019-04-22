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
import com.ats.hrmgt.model.CalenderYear;
import com.ats.hrmgt.model.Company;
import com.ats.hrmgt.model.EmpDocType;
import com.ats.hrmgt.model.EmpType;
import com.ats.hrmgt.model.EmployeDoc;
import com.ats.hrmgt.model.EmployeeCategory;
import com.ats.hrmgt.model.EmployeeDepartment;
import com.ats.hrmgt.model.EmployeeInfo;
import com.ats.hrmgt.model.GetEmployeeInfo;
import com.ats.hrmgt.model.Info;
import com.ats.hrmgt.model.LeaveApply;
import com.ats.hrmgt.model.LeaveStructure;
import com.ats.hrmgt.model.LeaveTrail;
import com.ats.hrmgt.model.LeaveType;
import com.ats.hrmgt.model.LeavesAllotment;
import com.ats.hrmgt.model.Location;
import com.ats.hrmgt.model.User;
import com.ats.hrmgt.repository.CalculateYearRepository;
import com.ats.hrmgt.repository.CompanyRepository;
import com.ats.hrmgt.repository.EmpTypeRepository;
import com.ats.hrmgt.repository.EmployeeCategoryRepository;
import com.ats.hrmgt.repository.EmployeeDepartmentRepository;
import com.ats.hrmgt.repository.EmployeeDeptTypeRepository;
import com.ats.hrmgt.repository.EmployeeDocsRepository;
import com.ats.hrmgt.repository.EmployeeInfoRepository;
import com.ats.hrmgt.repository.GetEmpInfoRepo;
import com.ats.hrmgt.repository.LeaveAllotmentRepository;
import com.ats.hrmgt.repository.LeaveApplyRepository;
import com.ats.hrmgt.repository.LeaveAuthorityRepository;
import com.ats.hrmgt.repository.LeaveStructureRepository;
import com.ats.hrmgt.repository.LeaveTrailRepository;
import com.ats.hrmgt.repository.LeaveTypeRepository;
import com.ats.hrmgt.repository.LocationRepository;
import com.ats.hrmgt.repository.UserRepo;

@RestController
public class MasterRestController {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	UserRepo userRepo;

	@Autowired
	EmpTypeRepository empTypeRepository;

	@Autowired
	EmployeeCategoryRepository employeeCategoryRepository;

	@Autowired
	EmployeeDepartmentRepository employeeDepartmentRepository;

	@Autowired
	EmployeeInfoRepository employeeInfoRepository;

	@Autowired
	EmployeeDeptTypeRepository employeeDocTypeRepository;

	@Autowired
	EmployeeDocsRepository employeeDocsRepository;

	@Autowired
	LeaveTypeRepository leaveTypeRepository;

	@Autowired
	LeaveStructureRepository leaveStructureRepository;

	@Autowired
	LeaveTrailRepository leaveTrailRepository;

	@Autowired
	LeaveApplyRepository leaveApplyRepository;

	@Autowired
	LeaveAllotmentRepository leaveAllotmentRepository;

	@Autowired
	CalculateYearRepository calculateYearRepository;

	@Autowired
	GetEmpInfoRepo getEmpInfo;

	@RequestMapping(value = { "/checkUniqueField" }, method = RequestMethod.POST)
	public @ResponseBody Info checkUniqueField(@RequestParam String inputValue, @RequestParam int valueType) {

		Info info = new Info();

		List<EmployeeInfo> empList = new ArrayList<>();

		if (valueType == 1) {
			System.err.println("Its Contact No check");

			System.err.println("Its New Record Insert ");
			empList = employeeInfoRepository.findByEmpMobile1AndDelStatusAndIsActive(inputValue.trim(), 1, 1);

		} else if (valueType == 2) {
			System.err.println("Its Email check");

			System.err.println("Its New Record Insert ");
			empList = employeeInfoRepository.findByEmpEmailAndDelStatusAndIsActive(inputValue.trim(), 1, 1);

		}

		if (empList.size() > 0) {
			info.setError(true);
			info.setMsg("duplicate");
		} else {
			info.setError(false);
			info.setMsg("unique");
		}

		return info;

	}

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

			list = companyRepository.findByDelStatusOrderByCompanyIdDesc(1);

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

	@RequestMapping(value = { "/getCompanyById" }, method = RequestMethod.POST)
	public @ResponseBody Company getCompanyById(@RequestParam("compId") int compId) {

		Company company = new Company();
		try {

			company = companyRepository.findByCompanyIdAndDelStatus(compId, 1);

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

	@RequestMapping(value = { "/getLocationList" }, method = RequestMethod.POST)
	public @ResponseBody List<Location> getLocationList(@RequestParam("companyId") int companyId) {

		List<Location> list = new ArrayList<Location>();
		try {

			if (companyId != 0) {
				list = locationRepository.findByDelStatusAndCompId(1, companyId);
			} else {
				list = locationRepository.findByDelStatus(1);
			}

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

	@RequestMapping(value = { "/getLocationById" }, method = RequestMethod.POST)
	public @ResponseBody Location getLocationById(@RequestParam("locId") int locId) {

		Location location = new Location();
		try {

			location = locationRepository.findByLocIdAndDelStatus(locId, 1);

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

			if (save == null) {

				save = new EmpType();
				save.setError(true);

			} else {
				save.setError(false);
			}

		} catch (Exception e) {

			e.printStackTrace();
			save.setError(true);
		}

		return save;

	}

	@RequestMapping(value = { "/getEmpTypeList" }, method = RequestMethod.POST)
	public @ResponseBody List<EmpType> getEmpTypeList(@RequestParam("compId") int compId) {

		List<EmpType> list = new ArrayList<EmpType>();
		try {

			if (compId != 0) {
				list = empTypeRepository.findByDelStatusAndCompanyId(1, compId);
			} else {
				list = empTypeRepository.findByDelStatus(1);
			}

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

	@RequestMapping(value = { "/getEmpTypeById" }, method = RequestMethod.POST)
	public @ResponseBody EmpType getEmpTypeById(@RequestParam("empTypeId") int empTypeId) {

		EmpType location = new EmpType();
		try {

			location = empTypeRepository.findByEmpTypeIdAndDelStatus(empTypeId, 1);

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

			if (save != null) {
				save.setError(false);
			} else {

				save = new EmployeeCategory();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new EmployeeCategory();
			save.setError(true);
			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getEmpCategoryList" }, method = RequestMethod.POST)
	public @ResponseBody List<EmployeeCategory> getEmpCategoryList(@RequestParam("compId") int compId) {

		List<EmployeeCategory> list = new ArrayList<EmployeeCategory>();
		try {

			if (compId != 0) {

				list = employeeCategoryRepository.findByDelStatusAndCompanyId(1, compId);

			} else {

				list = employeeCategoryRepository.findByDelStatus(1);

			}

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

	@RequestMapping(value = { "/getEmpCategoryById" }, method = RequestMethod.POST)
	public @ResponseBody EmployeeCategory getEmpCategoryById(@RequestParam("empCatId") int empCatId) {

		EmployeeCategory employeeCategory = new EmployeeCategory();
		try {

			employeeCategory = employeeCategoryRepository.findByEmpCatIdAndDelStatus(empCatId, 1);

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

			if (save != null) {
				save.setError(false);
			} else {
				save = new EmployeeDepartment();
				save.setError(true);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getEmpDeptList" }, method = RequestMethod.POST)
	public @ResponseBody List<EmployeeDepartment> getEmpDeptList(@RequestParam("compId") int compId) {

		List<EmployeeDepartment> list = new ArrayList<EmployeeDepartment>();
		try {
			if (compId != 0) {
				list = employeeDepartmentRepository.findByDelStatusAndCompanyId(1, compId);
			} else {
				list = employeeDepartmentRepository.findByDelStatus(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/deleteEmpDept" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteEmpDept(@RequestParam("empDeptId") int empDeptId) {

		Info info = new Info();

		try {

			int delete = employeeDepartmentRepository.deleteEmpDept(empDeptId);

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

	@RequestMapping(value = { "/getEmpDeptById" }, method = RequestMethod.POST)
	public @ResponseBody EmployeeDepartment getEmpDeptById(@RequestParam("empDeptId") int empDeptId) {

		EmployeeDepartment company = new EmployeeDepartment();
		try {

			company = employeeDepartmentRepository.findByEmpDeptIdAndDelStatus(empDeptId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return company;

	}

	@RequestMapping(value = { "/saveEmpInfo" }, method = RequestMethod.POST)
	public @ResponseBody EmployeeInfo saveEmpInfo(@RequestBody EmployeeInfo employeeDepartment) {

		EmployeeInfo save = new EmployeeInfo();
		try {

			save = employeeInfoRepository.saveAndFlush(employeeDepartment);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/saveUserInfo" }, method = RequestMethod.POST)
	public @ResponseBody User saveUserInfo(@RequestBody User userInfo) {

		User save = new User();
		try {

			save = userRepo.saveAndFlush(userInfo);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	/*
	 * @RequestMapping(value = { "/getEmpInfoList" }, method = RequestMethod.GET)
	 * public @ResponseBody List<EmployeeInfo> getEmpInfoList() {
	 * 
	 * List<EmployeeInfo> list = new ArrayList<EmployeeInfo>(); try {
	 * 
	 * list = employeeInfoRepository.findByDelStatus(1);
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return list;
	 * 
	 * }
	 */

	@RequestMapping(value = { "/getEmpInfoList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetEmployeeInfo> getEmployeeInfo(@RequestParam("companyId") int companyId,
			@RequestParam("locIdList") List<Integer> locIdList) {

		List<GetEmployeeInfo> list = new ArrayList<GetEmployeeInfo>();
		try {

			list = getEmpInfo.getEmpListByCompanyId(companyId, locIdList);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getEmpInfoListForLeaveAuth" }, method = RequestMethod.POST)
	public @ResponseBody List<GetEmployeeInfo> getEmpInfoListForLeaveAuth(@RequestParam("companyId") int companyId) {

		List<GetEmployeeInfo> list = new ArrayList<GetEmployeeInfo>();
		try {

			list = getEmpInfo.getEmpListByCompanyIdForAuth(companyId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/deleteEmpInfo" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteEmpInfo(@RequestParam("empId") int empId) {

		Info info = new Info();

		try {

			int delete = employeeInfoRepository.deleteEmpInfo(empId);

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

	@RequestMapping(value = { "/getEmpInfoById" }, method = RequestMethod.POST)
	public @ResponseBody EmployeeInfo getEmpInfoById(@RequestParam("empId") int empId) {

		EmployeeInfo company = new EmployeeInfo();
		try {

			company = employeeInfoRepository.findByEmpIdAndDelStatus(empId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return company;

	}
	/*
	 * @RequestMapping(value = { "/saveEmpInfo" }, method = RequestMethod.POST)
	 * public @ResponseBody EmpDocType saveEmpInfo(@RequestBody EmpDocType
	 * employeeDepartment) {
	 * 
	 * 
	 * EmpDocType save = new EmpDocType(); try {
	 * 
	 * save = employeeDocTypeRepository.saveAndFlush(employeeDepartment);
	 * 
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return save;
	 * 
	 * }
	 */

	@RequestMapping(value = { "/getEmpDocTypeList" }, method = RequestMethod.GET)
	public @ResponseBody List<EmpDocType> getEmpDocTypeList() {

		List<EmpDocType> list = new ArrayList<EmpDocType>();
		try {

			list = employeeDocTypeRepository.findByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/deleteEmpDocType" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteEmpDocType(@RequestParam("doctypeId") int doctypeId) {

		Info info = new Info();

		try {

			int delete = employeeDocTypeRepository.deleteEmpDocType(doctypeId);

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

	@RequestMapping(value = { "/getEmpDocTypeById" }, method = RequestMethod.POST)
	public @ResponseBody EmpDocType getEmpDocTypeById(@RequestParam("doctypeId") int doctypeId) {

		EmpDocType company = new EmpDocType();
		try {

			company = employeeDocTypeRepository.findByDoctypeIdAndDelStatus(doctypeId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return company;

	}

	@RequestMapping(value = { "/saveEmpDoc" }, method = RequestMethod.POST)
	public @ResponseBody EmployeDoc saveEmpDoc(@RequestBody EmployeDoc employeeDepartment) {

		EmployeDoc save = new EmployeDoc();
		try {

			save = employeeDocsRepository.saveAndFlush(employeeDepartment);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getEmpDocList" }, method = RequestMethod.GET)
	public @ResponseBody List<EmployeDoc> getEmpDocList() {

		List<EmployeDoc> list = new ArrayList<EmployeDoc>();
		try {

			list = employeeDocsRepository.findByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	/*
	 * @RequestMapping(value = { "/deleteEmpDocType" }, method = RequestMethod.POST)
	 * public @ResponseBody Info deleteEmpDoc(@RequestParam("docId") int docId) {
	 * 
	 * 
	 * Info info = new Info();
	 * 
	 * try {
	 * 
	 * int delete = employeeDocsRepository.deleteEmpDoc(docId);
	 * 
	 * if(delete>0) { info.setError(false); info.setMsg("deleted"); }else {
	 * info.setError(true); info.setMsg("failed"); }
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); info.setError(true); info.setMsg("failed"); }
	 * 
	 * return info;
	 * 
	 * }
	 */

	@RequestMapping(value = { "/getEmpDocById" }, method = RequestMethod.POST)
	public @ResponseBody EmployeDoc getEmpDocById(@RequestParam("docId") int docId) {

		EmployeDoc empDoc = new EmployeDoc();
		try {

			empDoc = employeeDocsRepository.findByDocIdAndDelStatus(docId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return empDoc;

	}

	@RequestMapping(value = { "/saveLeaveType" }, method = RequestMethod.POST)
	public @ResponseBody LeaveType saveLeaveType(@RequestBody LeaveType leaveType) {

		LeaveType save = new LeaveType();
		try {

			save = leaveTypeRepository.saveAndFlush(leaveType);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getLeaveTypeList" }, method = RequestMethod.POST)
	public @ResponseBody List<LeaveType> getLeaveTypeList() {

		List<LeaveType> list = new ArrayList<LeaveType>();
		try {

			list = leaveTypeRepository.findByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getLeaveTypeListIsStructure" }, method = RequestMethod.GET)
	public @ResponseBody List<LeaveType> getLeaveTypeListIsStructure() {

		List<LeaveType> list = new ArrayList<LeaveType>();
		try {

			list = leaveTypeRepository.findByDelStatusAndIsStructured(1, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/deleteLeaveType" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLeaveType(@RequestParam("lvTypeId") int lvTypeId) {

		Info info = new Info();

		try {

			int delete = leaveTypeRepository.deleteLeaveType(lvTypeId);

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

	@RequestMapping(value = { "/getLeaveTypeById" }, method = RequestMethod.POST)
	public @ResponseBody LeaveType getLeaveTypeById(@RequestParam("lvTypeId") int lvTypeId) {

		LeaveType leaveType = new LeaveType();
		try {

			leaveType = leaveTypeRepository.findByLvTypeIdAndDelStatus(lvTypeId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveType;

	}

	@RequestMapping(value = { "/saveLeaveStructure" }, method = RequestMethod.POST)
	public @ResponseBody LeaveStructure saveLeaveStructure(@RequestBody LeaveStructure leaveStructure) {

		LeaveStructure save = new LeaveStructure();
		try {

			save = leaveStructureRepository.saveAndFlush(leaveStructure);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getLeaveStructureList" }, method = RequestMethod.GET)
	public @ResponseBody List<LeaveStructure> getLeaveStructureList() {

		List<LeaveStructure> list = new ArrayList<LeaveStructure>();
		try {

			list = leaveStructureRepository.findByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getLeaveStructureById" }, method = RequestMethod.POST)
	public @ResponseBody LeaveType getLeaveStructureById(@RequestParam("lvsPkey") int lvsPkey) {

		LeaveType leaveType = new LeaveType();
		try {

			leaveType = leaveStructureRepository.findByLvsPkeyAndDelStatus(lvsPkey, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveType;

	}

	@RequestMapping(value = { "/saveLeaveTrail" }, method = RequestMethod.POST)
	public @ResponseBody LeaveTrail saveLeaveTrail(@RequestBody LeaveTrail leaveTrail) {

		LeaveTrail save = new LeaveTrail();
		try {

			save = leaveTrailRepository.saveAndFlush(leaveTrail);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getLeaveTrailList" }, method = RequestMethod.GET)
	public @ResponseBody List<LeaveTrail> getLeaveTrailList() {

		List<LeaveTrail> list = new ArrayList<LeaveTrail>();
		try {

			list = leaveTrailRepository.findByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/deleteLeaveTrail" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLeaveTrail(@RequestParam("trailPkey") int trailPkey) {

		Info info = new Info();

		try {

			int delete = leaveTrailRepository.deleteLeaveTrail(trailPkey);

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

	@RequestMapping(value = { "/getLeaveTrailById" }, method = RequestMethod.POST)
	public @ResponseBody LeaveTrail getLeaveTrailById(@RequestParam("trailPkey") int trailPkey) {

		LeaveTrail leaveTrail = new LeaveTrail();
		try {

			leaveTrail = leaveTrailRepository.findByTrailPkeyAndDelStatus(trailPkey, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveTrail;

	}

	@RequestMapping(value = { "/saveLeaveApply" }, method = RequestMethod.POST)
	public @ResponseBody LeaveApply saveLeaveApply(@RequestBody LeaveApply leave) {

		LeaveApply save = new LeaveApply();
		try {

			save = leaveApplyRepository.saveAndFlush(leave);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getLeaveApplyList" }, method = RequestMethod.GET)
	public @ResponseBody List<LeaveApply> getLeaveApplyList() {

		List<LeaveApply> list = new ArrayList<LeaveApply>();
		try {

			list = leaveApplyRepository.findByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/deleteLeaveApply" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteLeaveApply(@RequestParam("leaveId") int leaveId) {

		Info info = new Info();

		try {

			int delete = leaveApplyRepository.deleteLeaveApply(leaveId);

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

	@RequestMapping(value = { "getLeaveApplyById" }, method = RequestMethod.POST)
	public @ResponseBody LeaveApply getLeaveApplyById(@RequestParam("leaveId") int leaveId) {

		LeaveApply leaveApply = new LeaveApply();
		try {

			leaveApply = leaveApplyRepository.findByLeaveIdAndDelStatus(leaveId, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveApply;

	}

	@RequestMapping(value = { "/saveLeaveAllotment" }, method = RequestMethod.POST)
	public @ResponseBody LeavesAllotment saveLeaveAllotment(@RequestBody LeavesAllotment leavesAllotment) {

		LeavesAllotment save = new LeavesAllotment();
		try {

			LeavesAllotment leavesAllot = leaveAllotmentRepository.findByEmpIdAndDelStatus(leavesAllotment.getEmpId(),
					1);
			System.out.println(leavesAllotment.toString());
			if (leavesAllot != null) {

				leavesAllot.setLvsId(leavesAllotment.getLvsId());
				save = leaveAllotmentRepository.saveAndFlush(leavesAllot);
			} else {
				save = leaveAllotmentRepository.saveAndFlush(leavesAllotment);
			}

			if (save != null) {
				save.setError(false);
			} else {

				save = new LeavesAllotment();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new LeavesAllotment();
			save.setError(true);
			e.printStackTrace();
		}
		return save;

	}

	@RequestMapping(value = { "getLeaveAllotmentById" }, method = RequestMethod.POST)
	public @ResponseBody LeavesAllotment getLeaveAllotmentById(@RequestParam("lvsaPkey") int lvsaPkey) {

		LeavesAllotment leavesAllotment = new LeavesAllotment();
		try {

			leavesAllotment = leaveAllotmentRepository.findByLvsaPkeyAndDelStatus(lvsaPkey, 1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leavesAllotment;

	}

	@RequestMapping(value = { "/saveCalculateYear" }, method = RequestMethod.POST)
	public @ResponseBody CalenderYear saveCalculateYear(@RequestBody CalenderYear leavesAllotment) {

		CalenderYear save = new CalenderYear();
		try {

			save = calculateYearRepository.saveAndFlush(leavesAllotment);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return save;

	}

	@RequestMapping(value = { "/getCalculateYearListIsCurrent" }, method = RequestMethod.GET)
	public @ResponseBody List<CalenderYear> getCalculateYearListIsCurrent() {

		List<CalenderYear> list = new ArrayList<CalenderYear>();
		try {

			list = calculateYearRepository.findByIsCurrent(1);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setCalYrFromDate(DateConvertor.convertToDMY(list.get(i).getCalYrFromDate()));
				list.get(i).setCalYrToDate(DateConvertor.convertToDMY(list.get(i).getCalYrToDate()));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "/getCalculateYearList" }, method = RequestMethod.GET)
	public @ResponseBody List<CalenderYear> getCalculateYearList() {

		List<CalenderYear> list = new ArrayList<CalenderYear>();
		try {

			list = calculateYearRepository.findAll();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	@RequestMapping(value = { "geCalculateYearById" }, method = RequestMethod.POST)
	public @ResponseBody CalenderYear getCalculateYearById(@RequestParam("calYrId") int calYrId) {

		CalenderYear leaveAuthority = new CalenderYear();
		try {

			leaveAuthority = calculateYearRepository.findByCalYrId(calYrId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveAuthority;

	}

}
