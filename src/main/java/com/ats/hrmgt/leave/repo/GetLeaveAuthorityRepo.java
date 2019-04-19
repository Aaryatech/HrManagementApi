package com.ats.hrmgt.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.GetLeaveAuthority;

public interface GetLeaveAuthorityRepo extends JpaRepository<GetLeaveAuthority, Integer> {

	@Query(value = " SELECT auth.* \n"
			+ ",coalesce((SELECT e.emp_fname FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.emp_id),null) as emp_fname\n"
			+ ",coalesce((SELECT e.emp_mname FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.emp_id),null) as emp_mname\n"
			+ ",coalesce((SELECT e.emp_sname FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.emp_id),null) as emp_sname\n"
			+ "\n"
			+ ",coalesce((SELECT e.emp_fname FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.ini_auth_emp_id),null) as ini_emp_fname\n"
			+ ",coalesce((SELECT e.emp_mname FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.ini_auth_emp_id),null) as ini_emp_mname\n"
			+ ",coalesce((SELECT e.emp_sname FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.ini_auth_emp_id),null) as ini_emp_sname\n"
			+ "\n"
			+ ",coalesce((SELECT e.emp_fname FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.fin_auth_emp_id),null) as fini_emp_fname\n"
			+ ",coalesce((SELECT e.emp_mname FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.fin_auth_emp_id),null) as fini_emp_mname\n"
			+ ",coalesce((SELECT e.emp_sname FROM emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.fin_auth_emp_id),null) as fini_emp_sname\n"
			+ "\n" + "FROM leave_authority auth\n"
			+ "WHERE auth.del_status=1 AND auth.company_id=:companyId  ", nativeQuery = true)

	List<GetLeaveAuthority> getLeaveAuth(@Param("companyId") int companyId);

	/*
	 * SELECT auth.* ,coalesce((SELECT e.emp_fname FROM emp_info e WHERE
	 * auth.del_status=1 AND e.emp_id=auth.emp_id),null) as emp_fname
	 * ,coalesce((SELECT e.emp_mname FROM emp_info e WHERE auth.del_status=1 AND
	 * e.emp_id=auth.emp_id),null) as emp_mname ,coalesce((SELECT e.emp_sname FROM
	 * emp_info e WHERE auth.del_status=1 AND e.emp_id=auth.emp_id),null) as
	 * emp_sname
	 * 
	 * ,coalesce((SELECT e.emp_fname FROM emp_info e WHERE auth.del_status=1 AND
	 * e.emp_id=auth.ini_auth_emp_id),null) as ini_emp_fname ,coalesce((SELECT
	 * e.emp_mname FROM emp_info e WHERE auth.del_status=1 AND
	 * e.emp_id=auth.ini_auth_emp_id),null) as ini_emp_mname ,coalesce((SELECT
	 * e.emp_sname FROM emp_info e WHERE auth.del_status=1 AND
	 * e.emp_id=auth.ini_auth_emp_id),null) as ini_emp_sname
	 * 
	 * ,coalesce((SELECT e.emp_fname FROM emp_info e WHERE auth.del_status=1 AND
	 * e.emp_id=auth.fin_auth_emp_id),null) as fini_emp_fname ,coalesce((SELECT
	 * e.emp_mname FROM emp_info e WHERE auth.del_status=1 AND
	 * e.emp_id=auth.fin_auth_emp_id),null) as fini_emp_mname ,coalesce((SELECT
	 * e.emp_sname FROM emp_info e WHERE auth.del_status=1 AND
	 * e.emp_id=auth.fin_auth_emp_id),null) as fini_emp_sname
	 * 
	 * FROM leave_authority auth WHERE auth.del_status=1 AND auth.company_id=1
	 */

}
