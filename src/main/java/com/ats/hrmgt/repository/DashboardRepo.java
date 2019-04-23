package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.DashboardCount;

public interface DashboardRepo extends JpaRepository<DashboardCount, Integer> {

	@Query(value = " select  coalesce (((select count(DISTINCT(le.leave_id)) from leave_authority la,leave_apply le where (la.ini_auth_emp_id=:empId) and le.cal_yr_id=1 and le.emp_id=la.emp_id and le.ex_int1 in (1))+(select count(DISTINCT(le.leave_id)) from leave_authority la,leave_apply le where (la.fin_auth_emp_id=3) and le.cal_yr_id=1 and le.emp_id=la.emp_id and le.ex_int1 in (2))),0) as pending_request,\n" + 
			"coalesce (((select count(DISTINCT(le.leave_id)) from leave_authority la,leave_apply le where (la.ini_auth_emp_id=:empId) and le.cal_yr_id=1 and le.emp_id=la.emp_id and le.ex_int1 in (2))+(select count(DISTINCT(le.leave_id)) from leave_authority la,leave_apply le where (la.fin_auth_emp_id=:empId) and le.cal_yr_id=1 and le.emp_id=la.emp_id and le.ex_int1 in (1))),0) as info,\n" + 
			"coalesce ((select count(DISTINCT(le.leave_id)) from leave_authority la,leave_apply le where la.emp_id=:empId  and le.cal_yr_id=1 and le.emp_id=la.emp_id and le.ex_int1 in (1,2)),0) as my_leave,\n" + 
			"CASE WHEN (coalesce ((select count(DISTINCT(la.emp_id)) from leave_authority la  where (la.ini_auth_emp_id=:empId or la.fin_auth_emp_id=:empId )),0) > 0)  then 1 else 0 END as is_authorized", nativeQuery = true)	
	DashboardCount getDashboardCount(@Param("empId") int empId);
 
}
