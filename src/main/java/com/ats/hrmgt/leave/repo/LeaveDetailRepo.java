package com.ats.hrmgt.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.LeaveDetail;

public interface LeaveDetailRepo extends JpaRepository<LeaveDetail, Integer>{

	@Query(value = " SELECT l.*,e.emp_fname,e.emp_mname,e.emp_sname, e.emp_photo,d.emp_dept_name,lt.lv_title FROM leave_apply as l, emp_info as e ,m_emp_department d,leave_type lt \n" + 
			"  WHERE  l.del_status=1 and l.ex_int1=1 and l.emp_id=e.emp_id and d.emp_dept_id=e.emp_dept_id and lt.lv_type_id=l.lv_type_id \n" + 
			"  and l.emp_id IN(SELECT emp_id FROM `leave_authority` WHERE ini_auth_emp_id=:empId OR fin_auth_emp_id=:empId)", nativeQuery = true)
	List<LeaveDetail> getLeaveDetailByEmpId(@Param("empId") int empId);
			
}
