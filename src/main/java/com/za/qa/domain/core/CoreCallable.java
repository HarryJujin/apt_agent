package com.za.qa.domain.core; 

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import com.za.qa.hessianbean.CombineData;
import com.za.qa.hessianbean.CombineReport;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年2月7日 上午11:33:01 
 * 类说明 
 */
public class CoreCallable implements Callable<List> {
	
	private CombineData combinedata;
	
	
	public CoreCallable(CombineData combinedata){
		this.combinedata=combinedata;
	}
	public List<CombineReport>  call()throws Exception{	
		 RunClient runclient =new RunClient();
		  LinkedList <CombineReport> List=new LinkedList <CombineReport> ();
		  List.add(runclient.runsuite(combinedata.getData()));
		  return  List;
	}

}
 