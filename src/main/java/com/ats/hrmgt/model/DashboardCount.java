package com.ats.hrmgt.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DashboardCount {	
	
	@Id
	private int pendingRequest;
		
	private int myLeave;
	
	private int info;
	
	private int isAuthorized;

	

	public int getPendingRequest() {
		return pendingRequest;
	}

	public void setPendingRequest(int pendingRequest) {
		this.pendingRequest = pendingRequest;
	}

	public int getMyLeave() {
		return myLeave;
	}

	public void setMyLeave(int myLeave) {
		this.myLeave = myLeave;
	}

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}

	public int getIsAuthorized() {
		return isAuthorized;
	}

	public void setIsAuthorized(int isAuthorized) {
		this.isAuthorized = isAuthorized;
	}

	@Override
	public String toString() {
		return "DashboardCount [pendingRequest=" + pendingRequest + ", myLeave=" + myLeave + ", info=" + info
				+ ", isAuthorized=" + isAuthorized + "]";
	}

	
}
