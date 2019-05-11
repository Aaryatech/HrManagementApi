package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.EmployeeInfo;

public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Integer> {

	List<EmployeeInfo> findByDelStatus(int i);

	@Transactional
	@Modifying
	@Query("update EmployeeInfo set del_status=0  WHERE emp_id=:empId")
	int deleteEmpInfo(@Param("empId") int empId);

	EmployeeInfo findByEmpIdAndDelStatus(int empId, int i);

	List<EmployeeInfo> findByEmpMobile1AndDelStatusAndIsActive(String empMobile, int delStatus, int isActive);

	List<EmployeeInfo> findByEmpEmailAndDelStatusAndIsActive(String empEmail, int delStatus, int isActive);

	@Query(value = "SELECT\n" + "    e.*\n" + "FROM\n" + "    emp_info AS e\n" + "WHERE\n" + "    e.emp_id IN(\n"
			+ "    SELECT\n" + "        emp_id\n" + "    FROM\n" + "        leave_authority\n" + "    WHERE\n"
			+ "        ini_auth_emp_id =:empId OR fin_auth_emp_id = :empId AND del_status = 1\n"
			+ ")", nativeQuery = true)
	List<EmployeeInfo> getEmployeeListByEmpId(@Param("empId") int empId);

	@Query(value = "SELECT\n" + "    e.*\n" + "FROM\n" + "    emp_info AS e\n" + "WHERE\n" + "    e.emp_id IN(\n"
			+ "    SELECT\n" + "        emp_id\n" + "    FROM\n" + "        claim_authority\n" + "    WHERE\n"
			+ "        ca_ini_auth_emp_id = :empId OR ca_fin_auth_emp_id = :empId AND del_status = 1\n"
			+ ")", nativeQuery = true)
	List<EmployeeInfo> getEmpListForClaimByEmpId(@Param("empId") int empId);

	@Query(value = "SELECT\n" + "    e.*\n" + "FROM\n" + "    emp_info AS e\n" + "WHERE\n"
			+ "    e.loc_id IN(:locationId) and e.del_status=:i", nativeQuery = true)
	List<EmployeeInfo> getEmpInfoByLocId(@Param("locationId") List<Integer> locationId, int i);

	List<EmployeeInfo> findByEmpIdAndLocIdAndDelStatus(int empId, int calYrId, int i);

	EmployeeInfo findByDelStatusAndIsActiveAndEmpEmail(int i, int j, String inputValue);

	EmployeeInfo findByDelStatusAndIsActiveAndEmpMobile1(int i, int j, String inputValue);
	
	
	@Transactional
	@Modifying
	@Query("update EmployeeInfo set ex_var1=:token  WHERE emp_id=:empId")
	int updateUserToken(@Param("empId") int empId,@Param("token") String token);

	@Query(value = "select * from emp_info where emp_id not in (select emp_id from project_allotment where (pallot_fromdt<=:fromDate"
			+ " and pallot_todt>=:fromDate) or (pallot_fromdt<=:toDate and pallot_todt>=:toDate) ) and del_status=1 and is_active=1 "
			+ " and loc_id in (:locationIds) and emp_cat_id=:catId", nativeQuery = true)
	List<EmployeeInfo> getFullTimeFreeEmpList(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("catId") int catId, @Param("locationIds") List<Integer> locationIds);


	@Query(value = "select * from emp_info where emp_id not in (select emp_id from project_allotment where (pallot_fromdt<=:fromDate"
			+ " and pallot_todt>=:fromDate) or (pallot_fromdt<=:toDate and pallot_todt>=:toDate) ) and del_status=1 and is_active=1 "
			+ "   and emp_cat_id=:catId", nativeQuery = true)
	List<EmployeeInfo> getFullTimeFreeEmpList(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("catId") int catId);
	

}
