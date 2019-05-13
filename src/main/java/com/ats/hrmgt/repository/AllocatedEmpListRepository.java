package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.AllocatedEmpList;

public interface AllocatedEmpListRepository extends JpaRepository<AllocatedEmpList, Integer> {

	@Query(value = "select\n" + 
			"                pa.*,\n" + 
			"                e.emp_fname,\n" + 
			"                e.emp_mname,\n" + 
			"                e.emp_sname\n" + 
			"                \n" + 
			"            from\n" + 
			"                project_allotment pa,emp_info e \n" + 
			"            where\n" + 
			"                pa.project_id=:projectId\n" + 
			"                and pa.del_status=1\n" + 
			"                and pa.is_active=1 and e.emp_id=pa.emp_id ", nativeQuery = true)
	List<AllocatedEmpList> getAllocatedEmployeeList(@Param("projectId") int projectId);

}
