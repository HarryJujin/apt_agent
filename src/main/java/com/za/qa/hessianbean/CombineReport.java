package com.za.qa.hessianbean; 

import java.io.Serializable;
import java.util.List;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年3月7日 下午4:22:21 
 * 类说明 
 */
public class CombineReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2192076399940877535L;

	
	private List<CaseReportDTO> report;

	public List<CaseReportDTO> getReport() {
		return report;
	}

	public void setReport(List<CaseReportDTO> report) {
		this.report = report;
	}

}
 