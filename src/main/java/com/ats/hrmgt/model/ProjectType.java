package com.ats.hrmgt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_Type")
public class ProjectType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name="project_type_id")
	private int projectTypeId;
	
	@Column(name="company_id")
	private int companyId;
	
	@Column(name="project_type_title")
	private String projectTypeTitle;	
	
	@Column(name="project_type_title_short")
	private String projectTypeTitleShort;
	
	@Column(name="project_type_color ")
	private String projectTypeColor ;	
	
	@Column(name="project_type_remarks")
	private String projectTypeRemarks ;	
		
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

	public int getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(int projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getProjectTypeTitle() {
		return projectTypeTitle;
	}

	public void setProjectTypeTitle(String projectTypeTitle) {
		this.projectTypeTitle = projectTypeTitle;
	}

	public String getProjectTypeTitleShort() {
		return projectTypeTitleShort;
	}

	public void setProjectTypeTitleShort(String projectTypeTitleShort) {
		this.projectTypeTitleShort = projectTypeTitleShort;
	}

	public String getProjectTypeColor() {
		return projectTypeColor;
	}

	public void setProjectTypeColor(String projectTypeColor) {
		this.projectTypeColor = projectTypeColor;
	}

	public String getProjectTypeRemarks() {
		return projectTypeRemarks;
	}

	public void setProjectTypeRemarks(String projectTypeRemarks) {
		this.projectTypeRemarks = projectTypeRemarks;
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
		return "ProjectType [projectTypeId=" + projectTypeId + ", companyId=" + companyId + ", projectTypeTitle="
				+ projectTypeTitle + ", projectTypeTitleShort=" + projectTypeTitleShort + ", projectTypeColor="
				+ projectTypeColor + ", projectTypeRemarks=" + projectTypeRemarks + ", delStatus=" + delStatus
				+ ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime="
				+ makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1="
				+ exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", getProjectTypeId()=" + getProjectTypeId()
				+ ", getCompanyId()=" + getCompanyId() + ", getProjectTypeTitle()=" + getProjectTypeTitle()
				+ ", getProjectTypeTitleShort()=" + getProjectTypeTitleShort() + ", getProjectTypeColor()="
				+ getProjectTypeColor() + ", getProjectTypeRemarks()=" + getProjectTypeRemarks() + ", getDelStatus()="
				+ getDelStatus() + ", getIsActive()=" + getIsActive() + ", getMakerUserId()=" + getMakerUserId()
				+ ", getMakerEnterDatetime()=" + getMakerEnterDatetime() + ", getExInt1()=" + getExInt1()
				+ ", getExInt2()=" + getExInt2() + ", getExInt3()=" + getExInt3() + ", getExVar1()=" + getExVar1()
				+ ", getExVar2()=" + getExVar2() + ", getExVar3()=" + getExVar3() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}