package com.za.qa.hessianservice; 

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import com.za.qa.hessianbean.CombineData;
import com.za.qa.hessianbean.CombineReport;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年3月7日 下午5:33:45 
 * 类说明 
 */
public interface CoreService {
	public LinkedList<CombineReport> execute(LinkedList<CombineData> listcom) throws InterruptedException, ExecutionException;

}
 