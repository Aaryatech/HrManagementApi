package com.ats.hrmgt.leave.model;

import javax.persistence.Entity;

import javax.persistence.Id;

@Entity
public class LeaveHistory {
	
	
	@Id
    private int lvTypeId;
	
	private int lvsId;
	
	private String lvTitleShort;
	
	private String lvTitle;
	
	private int lvsAllotedLeaves;
	
	private int balLeave;
	
	private int aplliedLeaeve;
	
	private int sactionLeave;
	
	

	public int getLvsId() {
		return lvsId;
	}

	public void setLvsId(int lvsId) {
		this.lvsId = lvsId;
	}

	public String getLvTitle() {
		return lvTitle;
	}

	public void setLvTitle(String lvTitle) {
		this.lvTitle = lvTitle;
	}

	public int getLvTypeId() {
		return lvTypeId;
	}

	public void setLvTypeId(int lvTypeId) {
		this.lvTypeId = lvTypeId;
	}

	public String getLvTitleShort() {
		return lvTitleShort;
	}

	public void setLvTitleShort(String lvTitleShort) {
		this.lvTitleShort = lvTitleShort;
	}

	public int getLvsAllotedLeaves() {
		return lvsAllotedLeaves;
	}

	public void setLvsAllotedLeaves(int lvsAllotedLeaves) {
		this.lvsAllotedLeaves = lvsAllotedLeaves;
	}

	public int getBalLeave() {
		return balLeave;
	}

	public void setBalLeave(int balLeave) {
		this.balLeave = balLeave;
	}

	public int getAplliedLeaeve() {
		return aplliedLeaeve;
	}

	public void setAplliedLeaeve(int aplliedLeaeve) {
		this.aplliedLeaeve = aplliedLeaeve;
	}

	public int getSactionLeave() {
		return sactionLeave;
	}

	public void setSactionLeave(int sactionLeave) {
		this.sactionLeave = sactionLeave;
	}

	@Override
	public String toString() {
		return "LeaveHistory [lvTypeId=" + lvTypeId + ", lvTitleShort=" + lvTitleShort + ", lvTitle=" + lvTitle
				+ ", lvsAllotedLeaves=" + lvsAllotedLeaves + ", balLeave=" + balLeave + ", aplliedLeaeve="
				+ aplliedLeaeve + ", sactionLeave=" + sactionLeave + ", getLvTitle()=" + getLvTitle()
				+ ", getLvTypeId()=" + getLvTypeId() + ", getLvTitleShort()=" + getLvTitleShort()
				+ ", getLvsAllotedLeaves()=" + getLvsAllotedLeaves() + ", getBalLeave()=" + getBalLeave()
				+ ", getAplliedLeaeve()=" + getAplliedLeaeve() + ", getSactionLeave()=" + getSactionLeave()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	
	
	


}
