package com.ats.hrmgt.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmpLeaveHistoryRep;

public interface EmpLeaveHistoryRepRepo extends JpaRepository<EmpLeaveHistoryRep,String> {
	
	
	@Query(value = " SELECT\n" + 
			"\n" + 
			" UUID() AS rec_id,emp_info.emp_id,\n" + 
			"    CONCAT(emp_info.emp_sname, \" \", emp_info.emp_fname) AS emp_name,\n" + 
			"        leave_type.lv_type_id,\n" + 
			"        leave_type.lv_title_short,\n" + 
			"        leave_type.lv_title,\n" + 
			"        leave_structure_details.lvs_alloted_leaves,\n" + 
			"        leave_structure_header.lvs_id,\n" + 
			"        coalesce((select\n" + 
			"            b.op_bal \n" + 
			"        from\n" + 
			"            leave_balance_cal b,\n" + 
			"            dm_cal_year y \n" + 
			"        where\n" + 
			"            b.emp_id=emp_info.emp_id \n" + 
			"            and leave_type.lv_type_id=b.lv_type_id \n" + 
			"            and y.cal_yr_id=b.cal_yr_id \n" + 
			"            and y.is_current=1),\n" + 
			"        0) as bal_leave,\n" + 
			"        coalesce((select\n" + 
			"            sum(b.leave_num_days) \n" + 
			"        from\n" + 
			"            leave_apply b,\n" + 
			"            dm_cal_year y \n" + 
			"        where\n" + 
			"            b.emp_id=emp_info.emp_id \n" + 
			"            and leave_type.lv_type_id=b.lv_type_id \n" + 
			"            and y.cal_yr_id=b.cal_yr_id \n" + 
			"            and y.is_current=1 \n" + 
			"            and b.ex_int1=3),\n" + 
			"        0) as saction_leave,\n" + 
			"        coalesce((select\n" + 
			"            sum(b.leave_num_days) \n" + 
			"        from\n" + 
			"            leave_apply b,\n" + 
			"            dm_cal_year y \n" + 
			"        where\n" + 
			"            b.emp_id=emp_info.emp_id \n" + 
			"            and leave_type.lv_type_id=b.lv_type_id \n" + 
			"            and y.cal_yr_id=b.cal_yr_id \n" + 
			"            and y.is_current=1 \n" + 
			"            and b.ex_int1 in (1,2)),\n" + 
			"        0) as apllied_leaeve    \n" + 
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
			"        AND emp_info.emp_id=:empId \n" + 
			"        and leave_structure_allotment.cal_yr_id=:calYrId \n" + 
			"        and leave_type.del_status=1 ", nativeQuery = true)

	List<EmpLeaveHistoryRep> getEmpLeaveHistoryRep(@Param("empId") int empId,@Param("calYrId") int calYrId
			);

	

	@Query(value = " SELECT\n" + 
			"\n" + 
			" UUID() AS rec_id,emp_info.emp_id,\n" + 
			"    CONCAT(emp_info.emp_sname, \" \", emp_info.emp_fname) AS emp_name,\n" + 
			"        leave_type.lv_type_id,\n" + 
			"        leave_type.lv_title_short,\n" + 
			"        leave_type.lv_title,\n" + 
			"        leave_structure_details.lvs_alloted_leaves,\n" + 
			"        leave_structure_header.lvs_id,\n" + 
			"        coalesce((select\n" + 
			"            b.op_bal \n" + 
			"        from\n" + 
			"            leave_balance_cal b,\n" + 
			"            dm_cal_year y \n" + 
			"        where\n" + 
			"            b.emp_id=emp_info.emp_id \n" + 
			"            and leave_type.lv_type_id=b.lv_type_id \n" + 
			"            and y.cal_yr_id=b.cal_yr_id \n" + 
			"            and y.is_current=1),\n" + 
			"        0) as bal_leave,\n" + 
			"        coalesce((select\n" + 
			"            sum(b.leave_num_days) \n" + 
			"        from\n" + 
			"            leave_apply b,\n" + 
			"            dm_cal_year y \n" + 
			"        where\n" + 
			"            b.emp_id=emp_info.emp_id \n" + 
			"            and leave_type.lv_type_id=b.lv_type_id \n" + 
			"            and y.cal_yr_id=b.cal_yr_id \n" + 
			"            and y.is_current=1 \n" + 
			"            and b.ex_int1=3),\n" + 
			"        0) as saction_leave,\n" + 
			"        coalesce((select\n" + 
			"            sum(b.leave_num_days) \n" + 
			"        from\n" + 
			"            leave_apply b,\n" + 
			"            dm_cal_year y \n" + 
			"        where\n" + 
			"            b.emp_id=emp_info.emp_id \n" + 
			"            and leave_type.lv_type_id=b.lv_type_id \n" + 
			"            and y.cal_yr_id=b.cal_yr_id \n" + 
			"            and y.is_current=1 \n" + 
			"            and b.ex_int1 in (1,2)),\n" + 
			"        0) as apllied_leaeve    \n" + 
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
			"        \n" + 
			"        and leave_structure_allotment.cal_yr_id=:calYrId \n" + 
			"        and leave_type.del_status=1 ", nativeQuery = true)

	List<EmpLeaveHistoryRep> getEmpLeaveHistoryRepAll(@Param("calYrId") int calYrId
			);

}
