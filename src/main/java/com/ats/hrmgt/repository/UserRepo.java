package com.ats.hrmgt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.User;

public interface UserRepo  extends JpaRepository<User, Integer>{

	User findByDelStatusAndIsActiveAndUserNameAndUserPwd(int delStatus, int isActive, String userName, String userPwd);

	User findByEmpIdAndDelStatus(int empId, int delStatus);

	User findByEmpIdAndUserPwdAndDelStatus(int empId, String password, int i);

	
	@Transactional
	@Modifying
	@Query("update User set user_pwd=:password  WHERE emp_id=:empId")
 	int updateUserPassword(int empId, String password);

}
