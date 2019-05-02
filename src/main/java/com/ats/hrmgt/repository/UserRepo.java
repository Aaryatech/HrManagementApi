package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.User;

public interface UserRepo  extends JpaRepository<User, Integer>{

	User findByDelStatusAndIsActiveAndUserNameAndUserPwd(int delStatus, int isActive, String userName, String userPwd);

	User findByEmpIdAndDelStatus(int empId, int delStatus);

}
