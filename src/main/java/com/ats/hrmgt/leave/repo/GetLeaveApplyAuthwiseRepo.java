package com.ats.hrmgt.leave.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.GetLeaveApplyAuthwise;


public interface GetLeaveApplyAuthwiseRepo extends JpaRepository<GetLeaveApplyAuthwise, Integer>  {
	
	

	
	//to get initial data 
	
	@Query(value = " SELECT\n" + 
			"    la.leave_id,\n" + 
			"    la.cal_yr_id,\n" + 
			"    la.emp_id,\n" + 
			"    la.lv_type_id,\n" + 
			"    la.leave_duration,\n" + 
			"    la.leave_fromdt,\n" + 
			"    la.leave_todt,\n" + 
			"    la.leave_num_days,\n" + 
			"    la.circulated_to,\n" + 
			"    la.leave_emp_reason,\n" + 
			"    e.emp_code,e.emp_photo, \n" + 
			"    lt.lv_title AS leave_title,\n" + 
			"    CONCAT(e.emp_sname, \" \", e.emp_fname) AS emp_name,\n" + 
			"    le.ini_auth_emp_id,\n" + 
			"    le.fin_auth_emp_id,\n" + 
			"    la.ex_int1 ,0 AS leave_type_name\n" + 
			"FROM\n" + 
			"    leave_type lt,\n" + 
			"    leave_apply la,\n" + 
			"    leave_authority le,\n" + 
			"    emp_info e\n" + 
			"WHERE\n" + 
			"    la.lv_type_id = lt.lv_type_id AND le.emp_id = la.emp_id AND e.emp_id = le.emp_id AND(\n" + 
			"        (\n" + 
			"            le.ini_auth_emp_id=:empId AND la.ex_int1 = 1\n" + 
			"        ) OR(\n" + 
			"            le.fin_auth_emp_id=:empId AND la.ex_int1 = 2\n" + 
			"        )     \n" + 
			"    ) AND la.cal_yr_id = :currYrId ", nativeQuery = true)

	List<GetLeaveApplyAuthwise> getLeaveApplyList(@Param("empId") int empId,@Param("currYrId") int currYrId);
	
	//final
	
	
	@Query(value = " SELECT\n" + 
			"    la.leave_id,\n" + 
			"    la.cal_yr_id,\n" + 
			"    la.emp_id,\n" + 
			"    la.lv_type_id,\n" + 
			"    la.leave_duration,\n" + 
			"    la.leave_fromdt,\n" + 
			"    la.leave_todt,\n" + 
			"    la.leave_num_days,\n" + 
			"    la.circulated_to,\n" + 
			"    la.leave_emp_reason,\n" + 
			"    e.emp_code,e.emp_photo, \n" + 
			"    lt.lv_title AS leave_title,\n" + 
			"    CONCAT(e.emp_sname, \\\" \\\", e.emp_fname) AS emp_name,\n" + 
			"    le.ini_auth_emp_id,\n" + 
			"    le.fin_auth_emp_id,\n" + 
			"    la.ex_int1,\n" + 
			"    0 AS leave_type_name\n" + 
			"FROM\n" + 
			"    leave_type lt,\n" + 
			"    leave_apply la,\n" + 
			"    leave_authority le,\n" + 
			"    emp_info e\n" + 
			"WHERE\n" + 
			"    la.lv_type_id = lt.lv_type_id AND le.emp_id = la.emp_id AND e.emp_id = le.emp_id AND( \n" + 
			"        (\n" + 
			"            le.ini_auth_emp_id=:empId  AND la.ex_int1=2 \n" + 
			"        ) OR(\n" + 
			"            le.fin_auth_emp_id=:empId AND la.ex_int1=1 \n" + 
			"        )   OR(\\n\" + \n" + 
			"			\"                le.emp_id=:empId \\n\" + \n" + 
			"			\"                AND  la.ex_int1 in (2,1)         \\n\" + \n" + 
			"			\"            )\n" + 
			"    ) AND le.fin_auth_emp_id != le.ini_auth_emp_id AND la.cal_yr_id =:currYrId ORDER BY la.ex_int1 DESC  ", nativeQuery = true)

	List<GetLeaveApplyAuthwise> getLeaveApplyList2(@Param("empId") int empId,@Param("currYrId") int currYrId);

	

	
	
	/*
	 * @Query(value = " SELECT\n" + "    leave_apply.leave_id,\n" +
	 * "    leave_apply.cal_yr_id,\n" + "    leave_apply.emp_id,\n" +
	 * "    leave_apply.lv_type_id,\n" + "    leave_apply.leave_duration,\n" +
	 * "    leave_apply.leave_fromdt,\n" + "    leave_apply.leave_todt,\n" +
	 * "    leave_apply.leave_num_days,\n" + "    leave_apply.circulated_to,\n" +
	 * "    leave_apply.leave_emp_reason,\n" + "    emp_info.emp_code,\n" +
	 * "    emp_info.emp_fname,\n" + "    emp_info.emp_mname,\n" +
	 * "    emp_info.emp_sname,\n" +
	 * "    leave_type.lv_title as leave_title\n, 0 as leave_type_name " + "FROM\n"
	 * + "    leave_type,\n" + "    leave_apply,\n" + "    emp_info\n" + "WHERE\n" +
	 * "   leave_apply.emp_id IN(:empIdList) AND emp_info.emp_id = leave_apply.emp_id AND leave_apply.lv_type_id = "
	 * +
	 * "leave_type.lv_type_id and leave_apply.del_status=1 and leave_apply.is_active=1 and leave_apply.ex_int1 IN "
	 * + "(:statusList) and leave_apply.cal_yr_id=:currYrId ", nativeQuery = true)
	 * 
	 * List<GetLeaveApplyAuthwise> getLeaveApplyList1(@Param("empIdList")
	 * List<GetEmployeeAuthorityWise> empIdList,
	 * 
	 * @Param("statusList") List<Integer> statusList,@Param("currYrId") int
	 * currYrId);
	 */
	
}