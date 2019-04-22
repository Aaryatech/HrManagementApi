package com.ats.hrmgt.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.LeaveHistory;


public interface LeaveHistoryRepo  extends JpaRepository<LeaveHistory, Integer>{

	
	@Query(value = "    SELECT\n" + 
			"    emp_info.emp_id,leave_type.lv_title_short as short_name,leave_structure_details.lvs_alloted_leaves as earned_no,\n" + 
			"    COALESCE((SELECT COUNT(leave_apply.leave_id )from leave_apply WHERE leave_apply.emp_id=1 AND "
			+ "leave_apply.final_status=3 and leave_apply.lv_type_id=leave_type.lv_type_id  ),0) as sanction_no,\n" + 
			"    COALESCE((SELECT COUNT(leave_apply.leave_id )from leave_apply WHERE leave_apply.emp_id=1 AND "
			+ "leave_apply.final_status=1 and leave_apply.lv_type_id=leave_type.lv_type_id  ),0) as applied_no \n" + 
			"FROM\n" + 
			"    leave_type,\n" + 
			"    emp_info,\n" + 
			"    leave_structure_header,\n" + 
			"    leave_structure_details,\n" + 
			"    leave_structure_allotment\n" + 
			"   \n" + 
			"  \n" + 
			"WHERE\n" + 
			"    emp_info.emp_id = leave_structure_allotment.emp_id AND leave_structure_allotment.lvs_id = "
			+ "leave_structure_header.lvs_id AND leave_structure_header.lvs_id = leave_structure_details.lvs_id AND"
			+ " leave_structure_details.lv_type_id = leave_type.lv_type_id AND emp_info.emp_id = :empId AND  emp_info.del_status=1", nativeQuery = true)

	List<LeaveHistory> getLeaveHistoryByEmpId(@Param("empId") int empId
		);

	
}
