package com.ats.hrmgt.krakpi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.krakpi.model.GetEmpKraKpiCount;
 

public interface GetEmpKraKpiRepo extends JpaRepository<GetEmpKraKpiCount, Integer> {
	
	@Query(value = " SELECT\n" + 
			"    e.emp_code,\n" + 
			"    e.emp_id,\n" + 
			"    e.emp_fname,\n" + 
			"    e.emp_mname,\n" + 
			"    e.emp_sname,\n" + 
			"    e.emp_cat_id,\n" + 
			"    e.emp_dept_id,\n" + 
			"    e.emp_type_id,\n" + 
			"    c.emp_category,\n" + 
			"    c.emp_cat_short_name,\n" + 
			"    d.emp_dept,\n" + 
			"    d.emp_dept_short_name,\n" + 
			"    t.emp_type,\n" + 
			"    t.emp_type_short_name,\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        COUNT(kra.kra_id)\n" + 
			"    FROM\n" + 
			"        t_kra kra\n" + 
			"    WHERE\n" + 
			"        kra.emp_id = e.emp_id AND kra.del_status = 1 AND kra.year_id = :finYrId \n" + 
			") AS kra_count,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(kpi.kpi_id)\n" + 
			"    FROM\n" + 
			"        t_kpi kpi\n" + 
			"    WHERE\n" + 
			"        kpi.emp_id = e.emp_id AND kpi.del_status = 1\n" + 
			") AS kpi_count\n" + 
			"FROM\n" + 
			"    emp_info e,\n" + 
			"    m_emp_category c,\n" + 
			"    m_emp_department d,\n" + 
			"    m_emp_type t\n" + 
			"WHERE\n" + 
			"    e.del_status = 1 AND e.ex_int1 =:status  AND e.emp_cat_id = c.emp_cat_id AND e.emp_dept_id = d.emp_dept_id AND e.emp_type_id = t.emp_type_id"
			+ " ", nativeQuery = true)

	List<GetEmpKraKpiCount> getEmpKpiKra(@Param("status") int status,@Param("finYrId") int finYrId
		);
	
	

}
