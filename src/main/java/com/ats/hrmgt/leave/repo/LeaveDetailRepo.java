package com.ats.hrmgt.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.LeaveDetail;

public interface LeaveDetailRepo extends JpaRepository<LeaveDetail, Integer>{

	@Query(value = " SELECT l.*,e.emp_fname,e.emp_mname,e.emp_sname,e.empCode, e.emp_photo,d.emp_dept_name,lt.lv_title FROM leave_apply as l, emp_info as e ,m_emp_department d,leave_type lt \n" + 
			"  WHERE  l.del_status=1 and l.ex_int1=1 and l.emp_id=e.emp_id and d.emp_dept_id=e.emp_dept_id and lt.lv_type_id=l.lv_type_id \n" + 
			"  and l.emp_id IN(SELECT emp_id FROM `leave_authority` WHERE ini_auth_emp_id=:empId OR fin_auth_emp_id=:empId)", nativeQuery = true)
	List<LeaveDetail> getLeaveDetailByEmpId(@Param("empId") int empId);

	@Query(value = " SELECT\n" + 
			"l.*,\n" + 
			"e.emp_fname,\n" + 
			"e.emp_mname,"
			+"e.empCode,"+
			"e.emp_sname,\n" + 
			"e.emp_photo,\n" + 
			"d.emp_dept_name,\n" + 
			"lt.lv_title\n" + 
			"FROM\n" + 
			"leave_apply AS l,\n" + 
			"emp_info AS e,\n" + 
			"m_emp_department d,\n" + 
			"leave_type AS lt\n" + 
			"WHERE \n" + 
			"l.emp_id =:empId AND l.ex_int1 IN(:status) AND l.emp_id = e.emp_id AND l.del_status = 1 AND d.emp_dept_id = e.emp_dept_id AND lt.lv_type_id = l.lv_type_id ", nativeQuery = true)
	List<LeaveDetail> getLeaveStatus(@Param("empId") int empId,@Param("status") String status);

	
	@Query(value = " SELECT\n" + 
			"l.*,\n" + 
			"e.emp_fname,\n" + 
			"e.emp_mname,\n" + 
			"e.emp_sname,e.empCode,\n" + 
			"e.emp_photo,\n" + 
			"d.emp_dept_name,\n" + 
			"lt.lv_title\n" + 
			"FROM\n" + 
			"leave_apply AS l,\n" + 
			"emp_info AS e,\n" + 
			"m_emp_department d,\n" + 
			"leave_type AS lt\n" + 
			"WHERE \n" + 
			"l.emp_id =:empId AND l.emp_id = e.emp_id AND l.del_status = 1 AND d.emp_dept_id = e.emp_dept_id AND lt.lv_type_id = l.lv_type_id ", nativeQuery = true)
	List<LeaveDetail> getLeaveStatusByEmpId(@Param("empId") int empId);

	//List<LeaveDetail> getLeaveStatus(int empId, int status);
	
	
			
}