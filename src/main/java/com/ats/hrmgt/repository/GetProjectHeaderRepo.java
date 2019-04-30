package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetProjectHeader;

public interface GetProjectHeaderRepo extends JpaRepository<GetProjectHeader, Integer> {

	@Query(value = "SELECT p.*,l.loc_name,pt.project_type_title,pt.project_type_title_short ,c.cust_name ,"
			+ "e.emp_code,e.emp_fname,e.emp_mname,e.emp_sname FROM project_header p,m_location l ,"
			+ "project_type pt,m_customer c,emp_info e WHERE p.del_status=1 AND p.company_id=:companyId AND "
			+ "p.loc_id=l.loc_id AND pt.project_type_id=p.project_type_id AND c.cust_id=p.cust_id AND "
			+ "e.emp_id=p.project_manager_emp_id ", nativeQuery = true)

	List<GetProjectHeader> getProListByCompanyId(@Param("companyId") int companyId);

}
