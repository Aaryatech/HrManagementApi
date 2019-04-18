package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetEmployeeInfo;


public interface GetEmpInfoRepo extends JpaRepository<GetEmployeeInfo, Integer> {
	

	//List<GetEmployeeInfo> getEmpListByCompanyId(int companyId, List<Integer> locIdList);

	
	@Query(value = " SELECT	  emp_info.emp_code ,emp_info.emp_id,emp_info.emp_fname,emp_info.emp_mname  \n" + 
			"		,emp_info.emp_sname ,emp_info.emp_mobile1 "
			+ ",emp_info.emp_email  \n" + 
			"		 ,emp_info.emp_rate_perhr  ,emp_info.emp_prev_exp_yrs , \n" + 
			"		m_emp_department.emp_dept_name as emp_dept ,m_emp_type.emp_type_name emp_type,m_emp_category.emp_cat_name as emp_category,\n" + 
			"		emp_info.company_id,emp_info.ex_var1,m_company.company_name   , \n" + 
			"		emp_info.emp_cat_id,emp_info.emp_type_id,emp_info.emp_dept_id  from emp_info,m_emp_department,m_emp_type, \n" + 
			"		m_emp_category,m_company where emp_info.emp_dept_id=m_emp_department.emp_dept_id and emp_info.emp_cat_id= \n" + 
			"	    m_emp_category.emp_cat_id  and emp_info.emp_type_id=m_emp_type.emp_type_id and emp_info.del_status=1 \n" + 
			"		and emp_info.is_active=1 and emp_info.company_id=:companyId  and emp_info.loc_id IN(:locIdList) and emp_info.company_id=m_company.company_id "
			+ "    and emp_info.company_id=m_company.company_id ", nativeQuery = true)

	List<GetEmployeeInfo> getEmpListByCompanyId(@Param("companyId") int companyId,
			@Param("locIdList") List<Integer> locIdList);
}
