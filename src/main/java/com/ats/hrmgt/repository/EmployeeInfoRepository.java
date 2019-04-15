package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmployeeInfo;


public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Integer>{

	List<EmployeeInfo> findByDelStatus(int i);

	@Transactional
	@Modifying
	@Query("update EmployeeInfo set del_status=0  WHERE emp_id=:empId")
	int deleteEmpInfo(@Param("empId") int empId);

	EmployeeInfo findByEmpIdAndDelStatus(int empId, int i);

}