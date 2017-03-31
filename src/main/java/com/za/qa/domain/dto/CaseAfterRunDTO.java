package com.za.qa.domain.dto; 

import java.io.Serializable;

/** 
 * @author jujinxin 
 * @version 创建时间：2016年11月28日 下午3:10:41 
 * 类说明 
 */
public class CaseAfterRunDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String response;
	private String result;
	private String durationTime;
	
	public String getResponse() {
		return response;
	}
	public void setReponse(String response) {
		this.response = response;
	}

	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getDurationTime() {
		return durationTime;
	}
	public void setDurationTime(String durationTime) {
		this.durationTime = durationTime;
	}
	@Override
	public String toString() {
		return "CaseAfterRunDTO [response=" + response + ", result=" + result
				+ ", durationTime=" + durationTime + "]";
	}


	

}
 