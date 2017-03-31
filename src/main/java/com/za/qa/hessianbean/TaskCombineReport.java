package com.za.qa.hessianbean;

import java.io.Serializable;
import java.util.LinkedList;

public class TaskCombineReport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7716252535648264042L;
	
	private LinkedList<CombineReport> reportEntity;
	private String rid; //报告的唯一标示
	
	public LinkedList<CombineReport> getReportEntity() {
		return reportEntity;
	}
	public void setReportEntity(LinkedList<CombineReport> reportEntity) {
		this.reportEntity = reportEntity;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	
}
