package com.za.qa.hessianbean; 

import java.io.Serializable;
import java.util.List;



/** 
 * @author jujinxin 
 * @version 创建时间：2017年2月13日 上午11:00:54 
 * 类说明 
 */
public class CombineData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -285777615097211126L;
	
	private String type;
	private List<CaseDataDTO> data;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<CaseDataDTO> getData() {
		return data;
	}
	public void setData(List<CaseDataDTO> data) {
		this.data = data;
	} 

}
 