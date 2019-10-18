package com.ats.hrmgt.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.LeaveHistory;


public interface LeaveHistoryRepo  extends JpaRepository<LeaveHistory, Integer>{

	
	@Query(value = " SELECT\n" + 
			"    leave_type.lv_type_id,\n" + 
			"    leave_type.lv_title_short,leave_type.lv_title, \n" + 
			"    leave_structure_details.lvs_alloted_leaves,leave_structure_header.lvs_id, \n" + 
			"    coalesce((select b.op_bal from leave_balance_cal b, dm_cal_year y where b.emp_id=emp_info.emp_id and leave_type.lv_type_id=b.lv_type_id and y.cal_yr_id=b.cal_yr_id and y.is_current=1),0) as bal_leave,\n" + 
			"    coalesce((select sum(b.leave_num_days) from leave_apply b, dm_cal_year y where b.emp_id=emp_info.emp_id and leave_type.lv_type_id=b.lv_type_id and y.cal_yr_id=b.cal_yr_id and y.is_current=1 and b.ex_int1=3),0) as saction_leave,\n" + 
			"    coalesce((select sum(b.leave_num_days) from leave_apply b, dm_cal_year y where b.emp_id=emp_info.emp_id and leave_type.lv_type_id=b.lv_type_id and y.cal_yr_id=b.cal_yr_id and y.is_current=1 and b.ex_int1 in (1,2)),0) as apllied_leaeve\n" + 
			"FROM\n" + 
			"    leave_type,\n" + 
			"    emp_info,\n" + 
			"    leave_structure_header,\n" + 
			"    leave_structure_details,\n" + 
			"    leave_structure_allotment\n" + 
			"WHERE\n" + 
			"    emp_info.emp_id = leave_structure_allotment.emp_id AND leave_structure_allotment.lvs_id = "
			+ "leave_structure_header.lvs_id AND leave_structure_header.lvs_id = leave_structure_details.lvs_id "
			+ "AND leave_structure_details.lv_type_id = leave_type.lv_type_id AND emp_info.emp_id =:empId and "
			+ "leave_structure_allotment.cal_yr_id=:currYrId and leave_type.del_status=1 ", nativeQuery = true)

	List<LeaveHistory> getLeaveHistoryByEmpId(@Param("empId") int empId,@Param("currYrId") int currYrId);
	
	
	@Query(value = " SELECT\n" + 
			"    leave_structure_details.lvs_alloted_leaves as bal_leave,0 as lv_type_id,0 as lv_title_short, 0 as lv_title,0 as lvs_alloted_leaves,"
			+ " 0 as saction_leave, 0 as apllied_leaeve,0 as lvs_id  \n" + 
			"FROM\n" + 
			"    leave_structure_details\n" + 
			"WHERE\n" + 
			"    leave_structure_details.lv_type_id =:leaveTypeId AND leave_structure_details.lvs_id =:lvsId", nativeQuery = true)


	LeaveHistory getLeaveEarnedByLeaveTypeId(@Param("leaveTypeId") int leaveTypeId,@Param("lvsId") int lvsId);


	@Query(value = "SELECT\n" + 
			"        leave_type.lv_type_id,\n" + 
			"        leave_type.lv_title_short,\n" + 
			"        leave_type.lv_title,\n" + 
			"        leave_structure_details.lvs_alloted_leaves,\n" + 
			"        leave_structure_header.lvs_id,\n" + 
			"        coalesce((select\n" + 
			"            b.op_bal \n" + 
			"        from\n" + 
			"            leave_balance_cal b \n" + 
			"        where\n" + 
			"            b.emp_id=emp_info.emp_id \n" + 
			"            and leave_type.lv_type_id=b.lv_type_id \n" + 
			"            and b.cal_yr_id=leave_structure_allotment.cal_yr_id),\n" + 
			"        0) as bal_leave,\n" + 
			"        coalesce((select\n" + 
			"            sum(b.leave_num_days) \n" + 
			"        from\n" + 
			"            leave_apply b \n" + 
			"        where\n" + 
			"            b.emp_id=emp_info.emp_id \n" + 
			"            and leave_type.lv_type_id=b.lv_type_id \n" + 
			"            and b.cal_yr_id=leave_structure_allotment.cal_yr_id \n" + 
			"            and b.ex_int1=3),\n" + 
			"        0) as saction_leave,\n" + 
			"        coalesce((select\n" + 
			"            sum(b.leave_num_days) \n" + 
			"        from\n" + 
			"            leave_apply b  \n" + 
			"        where\n" + 
			"            b.emp_id=emp_info.emp_id \n" + 
			"            and leave_type.lv_type_id=b.lv_type_id \n" + 
			"            and b.cal_yr_id=leave_structure_allotment.cal_yr_id \n" + 
			"            and b.ex_int1 in (1,2)),\n" + 
			"        0) as apllied_leaeve \n" + 
			"    FROM\n" + 
			"        leave_type,\n" + 
			"        emp_info,\n" + 
			"        leave_structure_header,\n" + 
			"        leave_structure_details,\n" + 
			"        leave_structure_allotment \n" + 
			"    WHERE\n" + 
			"        emp_info.emp_id = leave_structure_allotment.emp_id \n" + 
			"        AND leave_structure_allotment.lvs_id = leave_structure_header.lvs_id \n" + 
			"        AND leave_structure_header.lvs_id = leave_structure_details.lvs_id \n" + 
			"        AND leave_structure_details.lv_type_id = leave_type.lv_type_id \n" + 
			"        AND emp_info.emp_id =:empId \n" + 
			"        and leave_structure_allotment.cal_yr_id=(select max(cal_yr_id) as cal_yr_id from dm_cal_year  where is_current=0)", nativeQuery = true)
	List<LeaveHistory> getPreviousleaveHistory(@Param("empId") int empId);
		

	
	
}
