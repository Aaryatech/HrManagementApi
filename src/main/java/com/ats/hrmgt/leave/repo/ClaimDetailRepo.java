package com.ats.hrmgt.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.ClaimDetail;
import com.ats.hrmgt.leave.model.LeaveDetail;

public interface ClaimDetailRepo extends JpaRepository<ClaimDetail, Integer>{

	@Query(value = " SELECT\n" + 
			"ca.*,\n" + 
			"e.emp_fname,\n" + 
			"e.emp_mname,"
			+"e.emp_code,"+
			"e.emp_sname,\n" + 
			"e.emp_photo,\n" + 
			"d.emp_dept_name,\n" + 
			"ct.claim_type_title,"+ 
			"p.project_type_title \n" + 
			"FROM\n" + 
			"claim_apply AS ca,\n" + 
			"emp_info AS e,\n" + 
			"m_emp_department d,\n" + 
			"claim_type AS ct,"
			+ "project_Type as p\n" + 
			"WHERE \n" + 
			"ca.emp_id =:empId AND ca.ex_int1 IN(:status) AND ca.emp_id = e.emp_id AND ca.del_status = 1 AND d.emp_dept_id = e.emp_dept_id AND ca.claim_type_id = ct.claim_type_id AND ca.project_id=p.project_id ", nativeQuery = true)
	List<ClaimDetail> getClaimStatus(@Param("empId") int empId,@Param("status") List<Integer> status);

	
}
