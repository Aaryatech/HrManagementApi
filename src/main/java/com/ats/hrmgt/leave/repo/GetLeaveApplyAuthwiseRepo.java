package com.ats.hrmgt.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.GetEmployeeAuthorityWise;
import com.ats.hrmgt.leave.model.GetLeaveApplyAuthwise;


public interface GetLeaveApplyAuthwiseRepo extends JpaRepository<GetLeaveApplyAuthwise, Integer>  {
	
	
	@Query(value = " SELECT\n" + 
			"    leave_apply.leave_id,\n" + 
			"    leave_apply.cal_yr_id,\n" + 
			"    leave_apply.emp_id,\n" + 
			"    leave_apply.lv_type_id,\n" + 
			"    leave_apply.leave_duration,\n" + 
			"    leave_apply.leave_fromdt,\n" + 
			"    leave_apply.leave_todt,\n" + 
			"    leave_apply.leave_num_days,\n" + 
			"    leave_apply.circulated_to,\n" + 
			"    leave_apply.leave_emp_reason,\n" + 
			"    emp_info.emp_code,\n" + 
			"    emp_info.emp_fname,\n" + 
			"    emp_info.emp_mname,\n" + 
			"    emp_info.emp_sname,\n" + 
			"    emp_info.emp_photo ,\n" + 
			"    leave_type.lv_title as leave_title\n, 0 as leave_type_name " + 
			"FROM\n" + 
			"    leave_type,\n" + 
			"    leave_apply,\n" + 
			"    emp_info\n" + 
			"WHERE\n" + 
			"   leave_apply.emp_id IN(:empIdList) AND emp_info.emp_id = leave_apply.emp_id AND leave_apply.lv_type_id = "
			+ "leave_type.lv_type_id and leave_apply.del_status=1 and leave_apply.is_active=1 and leave_apply.ex_int1 IN "
			+ "(:statusList) and leave_apply.cal_yr_id=:currYrId ", nativeQuery = true)

	List<GetLeaveApplyAuthwise> getLeaveApplyList(@Param("empIdList") List<GetEmployeeAuthorityWise> empIdList,
			@Param("statusList") List<Integer> statusList,@Param("currYrId") int currYrId);
	
	
	

}
