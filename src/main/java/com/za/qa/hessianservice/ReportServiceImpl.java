package com.za.qa.hessianservice; 

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import com.za.qa.hessianbean.CombineData;
import com.za.qa.hessianbean.CombineReport;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年2月28日 下午3:10:17 
 * 类说明 
 */
public class ReportServiceImpl implements ReportService {
	
	
	private final LinkedList<CombineReport> listcombineReport;
	
	public ReportServiceImpl(LinkedList<CombineData> listcom) throws InterruptedException, ExecutionException{
		CoreService coreService =new CoreServiceImpl();
		this.listcombineReport=coreService.execute(listcom);
	}

	@Override
	public LinkedList<CombineReport> getReport() {
		// TODO Auto-generated method stub
		return listcombineReport;
	}
	
	


}
