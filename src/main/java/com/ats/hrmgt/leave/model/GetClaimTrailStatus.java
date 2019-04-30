package com.ats.hrmgt.leave.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class GetClaimTrailStatus {
	
	@Id
	private int claimTrailPkey;
	
	private int claimId ;
	
	private int empId;
	
	private String empRemarks;
	
	private int claimStatus;
	
	private String userName;
	
	private Date makerEnterDatetime;
	
	private String empFname;
	
	private String empMname;
	
	private String empSname;
	
	private String empPhoto;

	public int getClaimTrailPkey() {
		return claimTrailPkey;
	}

	public void setClaimTrailPkey(int claimTrailPkey) {
		this.claimTrailPkey = claimTrailPkey;
	}

	public int getClaimId() {
		return claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpRemarks() {
		return empRemarks;
	}

	public void setEmpRemarks(String empRemarks) {
		this.empRemarks = empRemarks;
	}

	public int getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(int claimStatus) {
		this.claimStatus = claimStatus;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonFormat(locale = "Locale.ENGLISH", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy hh:mm:ss a")// 
	public Date getMakerEnterDatetime() {
		return makerEnterDatetime;
	}

	public void setMakerEnterDatetime(Date makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
	}

	public String getEmpFname() {
		return empFname;
	}

	public void setEmpFname(String empFname) {
		this.empFname = empFname;
	}

	public String getEmpMname() {
		return empMname;
	}

	public void setEmpMname(String empMname) {
		this.empMname = empMname;
	}

	public String getEmpSname() {
		return empSname;
	}

	public void setEmpSname(String empSname) {
		this.empSname = empSname;
	}

	public String getEmpPhoto() {
		return empPhoto;
	}

	public void setEmpPhoto(String empPhoto) {
		this.empPhoto = empPhoto;
	}

	@Override
	public String toString() {
		return "GetClaimTrailStatus [claimTrailPkey=" + claimTrailPkey + ", claimId=" + claimId + ", empId=" + empId
				+ ", empRemarks=" + empRemarks + ", claimStatus=" + claimStatus + ", makerEnterDatetime="
				+ makerEnterDatetime + ", empFname=" + empFname + ", empMname=" + empMname + ", empSname=" + empSname
				+ ", empPhoto=" + empPhoto + "]";
	}

	
}
