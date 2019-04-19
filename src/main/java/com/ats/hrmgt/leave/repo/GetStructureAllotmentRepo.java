package com.ats.hrmgt.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.leave.model.GetStructureAllotment;

public interface GetStructureAllotmentRepo extends JpaRepository<GetStructureAllotment, Integer> {

	@Query(value = "    SELECT e.emp_id,e.emp_code,e.emp_fname,e.emp_mname,e.emp_sname\n"
			+ "	  ,d.emp_dept_name, c.emp_cat_name ,l.lvs_name FROM emp_info e LEFT JOIN\n"
			+ "	  leave_structure_allotment lsa ON e.emp_id=lsa.emp_id LEFT JOIN\n"
			+ "	  m_emp_department d ON e.emp_dept_id=d.emp_dept_id LEFT JOIN m_emp_category c\n"
			+ "	  ON e.emp_cat_id =c.emp_cat_id LEFT JOIN leave_structure_header l ON\n"
			+ "	  lsa.lvs_id=l.lvs_id AND l.del_status=1 WHERE e.del_status=1 AND e.is_active=1 AND e.company_id=:companyId"
			+ " AND e.loc_id IN(:locIdList)", nativeQuery = true)

	List<GetStructureAllotment> getStructureAllotment(@Param("companyId") int companyId,
			@Param("locIdList") List<Integer> locIdList);

	/*
	 * SELECT e.emp_id,e.emp_code,e.emp_fname,e.emp_mname,e.emp_sname
	 * ,d.emp_dept_name, c.emp_cat_name ,l.lvs_name FROM emp_info e LEFT JOIN
	 * leave_structure_allotment lsa ON e.emp_id=lsa.emp_id LEFT JOIN
	 * m_emp_department d ON e.emp_dept_id=d.emp_dept_id LEFT JOIN m_emp_category c
	 * ON e.emp_cat_id =c.emp_cat_id LEFT JOIN leave_structure_header l ON
	 * lsa.lvs_id=l.lvs_id AND l.del_status=1 WHERE e.del_status=1 AND e.is_active=1
	 */

}
