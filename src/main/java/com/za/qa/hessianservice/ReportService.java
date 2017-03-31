package com.za.qa.hessianservice; 

import java.net.MalformedURLException;
import java.util.LinkedList;

import com.za.qa.hessianbean.CombineReport;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年3月7日 下午5:37:54 
 * 类说明 
 */
public interface ReportService {
	public LinkedList<CombineReport> getReport() throws MalformedURLException;

}
 