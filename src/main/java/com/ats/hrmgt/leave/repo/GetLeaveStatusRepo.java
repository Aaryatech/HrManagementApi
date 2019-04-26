package com.ats.hrmgt.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.GetLeaveStatus;


public interface GetLeaveStatusRepo extends JpaRepository<GetLeaveStatus, Integer> {

	
	@Query(value = "SELECT\n" + 
			"  \n" + 
			"    e.emp_fname,\n" + 
			"    e.emp_mname,\n" + 
			"    e.emp_sname,\n" + 
			"    e.emp_photo,   \n" + 
			"    t.emp_remarks,\n" + 
			"    t.trail_pkey,\n" + 
			"    t.emp_id,\n" + 
			"    t.leave_id,\n" + 
			"    t.leave_status,\n" + 
			"    t.maker_enter_datetime\n" + 
			"   \n" + 
			"FROM\n" + 
			"    emp_info AS e,\n" + 
			"    leave_trail AS t\n" + 
			"WHERE\n" + 
			"    e.emp_id = t.emp_id and t.leave_id=:leaveId ORDER BY t.trail_pkey DESC", nativeQuery = true)
	List<GetLeaveStatus> getLeaveTrailByLeaveId(@Param("leaveId") int leaveId);


	@Query(value = "SELECT\n" + 
			"  \n" + 
			"    e.emp_fname,\n" + 
			"    e.emp_mname,\n" + 
			"    e.emp_sname,\n" + 
			"    e.emp_photo,   \n" + 
			"    t.emp_remarks,\n" + 
			"    t.trail_pkey,\n" + 
			"    t.emp_id,\n" + 
			"    t.leave_id,\n" + 
			"    t.leave_status,\n" + 
			"    t.maker_enter_datetime\n" + 
			"   \n" + 
			"FROM\n" + 
			"    emp_info AS e,\n" + 
			"    leave_trail AS t\n" + 
			"WHERE\n" + 
			"    e.emp_id = t.emp_id And t.leave_id=:leaveId", nativeQuery = true)
	List<GetLeaveStatus> getEmpInfoByLeaveId(@Param("leaveId") int leaveId);

}
