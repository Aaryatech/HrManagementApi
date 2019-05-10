package com.ats.hrmgt.leave.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class GetLeaveApplyAuthwise {
	
	
	@Id
	private int leaveId;
	
	private int calYrId;
	
	private String leaveTitle;
	
	private int empId;
	
	private String empName;
	
	private String iniAuthEmpId;
	
	private String finAuthEmpId;
	
	private int exInt1;
	
	private String empCode;
	
 	private int lvTypeId;
 	
 	private String leaveTypeName;
	
 	private String leaveDuration;
	
 	private String leaveFromdt;
	
 	private String leaveTodt ;
	
 	private float leaveNumDays;
	
 	private String leaveEmpReason;
 	
 	private String circulatedTo;
 	
 	private String empPhoto;
 	
 	
 	

	public String getEmpPhoto() {
		return empPhoto;
	}

	public void setEmpPhoto(String empPhoto) {
		this.empPhoto = empPhoto;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public int getCalYrId() {
		return calYrId;
	}

	public void setCalYrId(int calYrId) {
		this.calYrId = calYrId;
	}

	public String getLeaveTitle() {
		return leaveTitle;
	}

	public void setLeaveTitle(String leaveTitle) {
		this.leaveTitle = leaveTitle;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getIniAuthEmpId() {
		return iniAuthEmpId;
	}

	public void setIniAuthEmpId(String iniAuthEmpId) {
		this.iniAuthEmpId = iniAuthEmpId;
	}

	public String getFinAuthEmpId() {
		return finAuthEmpId;
	}

	public void setFinAuthEmpId(String finAuthEmpId) {
		this.finAuthEmpId = finAuthEmpId;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public int getLvTypeId() {
		return lvTypeId;
	}

	public void setLvTypeId(int lvTypeId) {
		this.lvTypeId = lvTypeId;
	}

	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	public String getLeaveDuration() {
		return leaveDuration;
	}

	public void setLeaveDuration(String leaveDuration) {
		this.leaveDuration = leaveDuration;
	}
	
 	public String getLeaveFromdt() {
		return leaveFromdt;
	}

	public void setLeaveFromdt(String leaveFromdt) {
		this.leaveFromdt = leaveFromdt;
	}
	
  	public String getLeaveTodt() {
		return leaveTodt;
	}

	public void setLeaveTodt(String leaveTodt) {
		this.leaveTodt = leaveTodt;
	}

	public float getLeaveNumDays() {
		return leaveNumDays;
	}

	public void setLeaveNumDays(float leaveNumDays) {
		this.leaveNumDays = leaveNumDays;
	}

	public String getLeaveEmpReason() {
		return leaveEmpReason;
	}

	public void setLeaveEmpReason(String leaveEmpReason) {
		this.leaveEmpReason = leaveEmpReason;
	}

	public String getCirculatedTo() {
		return circulatedTo;
	}

	public void setCirculatedTo(String circulatedTo) {
		this.circulatedTo = circulatedTo;
	}

	@Override
	public String toString() {
		return "GetLeaveApplyAuthwise [leaveId=" + leaveId + ", calYrId=" + calYrId + ", leaveTitle=" + leaveTitle
				+ ", empId=" + empId + ", empName=" + empName + ", iniAuthEmpId=" + iniAuthEmpId + ", finAuthEmpId="
				+ finAuthEmpId + ", exInt1=" + exInt1 + ", empCode=" + empCode + ", lvTypeId=" + lvTypeId
				+ ", leaveTypeName=" + leaveTypeName + ", leaveDuration=" + leaveDuration + ", leaveFromdt="
				+ leaveFromdt + ", leaveTodt=" + leaveTodt + ", leaveNumDays=" + leaveNumDays + ", leaveEmpReason="
				+ leaveEmpReason + ", circulatedTo=" + circulatedTo + ", getLeaveId()=" + getLeaveId()
				+ ", getCalYrId()=" + getCalYrId() + ", getLeaveTitle()=" + getLeaveTitle() + ", getEmpId()="
				+ getEmpId() + ", getEmpName()=" + getEmpName() + ", getIniAuthEmpId()=" + getIniAuthEmpId()
				+ ", getFinAuthEmpId()=" + getFinAuthEmpId() + ", getExInt1()=" + getExInt1() + ", getEmpCode()="
				+ getEmpCode() + ", getLvTypeId()=" + getLvTypeId() + ", getLeaveTypeName()=" + getLeaveTypeName()
				+ ", getLeaveDuration()=" + getLeaveDuration() + ", getLeaveFromdt()=" + getLeaveFromdt()
				+ ", getLeaveTodt()=" + getLeaveTodt() + ", getLeaveNumDays()=" + getLeaveNumDays()
				+ ", getLeaveEmpReason()=" + getLeaveEmpReason() + ", getCirculatedTo()=" + getCirculatedTo()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	
	

}