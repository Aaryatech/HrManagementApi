package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "claim_authority")
public class ClaimAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="claim_id")
	private int claimId;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="project_id")
	private int projectId;
	
	@Column(name="claim_type_id")
	private int claimTypeId;
	
	@Column(name="claim_date")
	private String claimDate;
	
	@Column(name="claim_amount")
	private float claimAmount;
	
	@Column(name="claim_remarks")
	private String claimRemarks ;
	
	@Column(name="claim_final_status")
	private int claimFinalStatus;
		
	@Column(name="circulated_to")
	private String circulatedTo;
	
	@Column(name="del_status")
	private int delStatus;
	
	@Column(name="is_active")
	private int isActive;
	
	@Column(name="maker_user_id ")
	private int makerUserId ;
	
	@Column(name="maker_enter_datetime")
	private String makerEnterDatetime;
	
	@Column(name="ex_int1")
	private int exInt1;
	
	@Column(name="ex_int2")
	private int exInt2;
	
	@Column(name="ex_int3")
	private int exInt3;
	
	@Column(name="ex_var1")
	private String exVar1; 
	
	@Column(name="ex_var2")
	private String exVar2; 
	
	@Column(name="ex_var3")
	private String exVar3;

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

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getClaimTypeId() {
		return claimTypeId;
	}

	public void setClaimTypeId(int claimTypeId) {
		this.claimTypeId = claimTypeId;
	}

	public String getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}

	public float getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(float claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getClaimRemarks() {
		return claimRemarks;
	}

	public void setClaimRemarks(String claimRemarks) {
		this.claimRemarks = claimRemarks;
	}

	public int getClaimFinalStatus() {
		return claimFinalStatus;
	}

	public void setClaimFinalStatus(int claimFinalStatus) {
		this.claimFinalStatus = claimFinalStatus;
	}

	public String getCirculatedTo() {
		return circulatedTo;
	}

	public void setCirculatedTo(String circulatedTo) {
		this.circulatedTo = circulatedTo;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getMakerUserId() {
		return makerUserId;
	}

	public void setMakerUserId(int makerUserId) {
		this.makerUserId = makerUserId;
	}

	public String getMakerEnterDatetime() {
		return makerEnterDatetime;
	}

	public void setMakerEnterDatetime(String makerEnterDatetime) {
		this.makerEnterDatetime = makerEnterDatetime;
	}

	public int getExInt1() {
		return exInt1;
	}

	public void setExInt1(int exInt1) {
		this.exInt1 = exInt1;
	}

	public int getExInt2() {
		return exInt2;
	}

	public void setExInt2(int exInt2) {
		this.exInt2 = exInt2;
	}

	public int getExInt3() {
		return exInt3;
	}

	public void setExInt3(int exInt3) {
		this.exInt3 = exInt3;
	}

	public String getExVar1() {
		return exVar1;
	}

	public void setExVar1(String exVar1) {
		this.exVar1 = exVar1;
	}

	public String getExVar2() {
		return exVar2;
	}

	public void setExVar2(String exVar2) {
		this.exVar2 = exVar2;
	}

	public String getExVar3() {
		return exVar3;
	}

	public void setExVar3(String exVar3) {
		this.exVar3 = exVar3;
	}

	@Override
	public String toString() {
		return "ClaimAuthority [claimId=" + claimId + ", empId=" + empId + ", projectId=" + projectId + ", claimTypeId="
				+ claimTypeId + ", claimDate=" + claimDate + ", claimAmount=" + claimAmount + ", claimRemarks="
				+ claimRemarks + ", claimFinalStatus=" + claimFinalStatus + ", circulatedTo=" + circulatedTo
				+ ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId
				+ ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2
				+ ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + "]";
	}
	
	
}