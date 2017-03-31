package com.za.qa.hessianservice; 

import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.za.qa.hessianbean.CombineData;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年2月28日 下午3:51:04 
 * 类说明 
 */
@Service("host")
public class Host {
	
    private static Logger logger = LoggerFactory.getLogger(Host.class);
	 public ReportService request(String rid,final LinkedList<CombineData> listcom) {
		 logger.info(listcom.toString()+"请求开始执行接入");
		    //建立FutureReport的实体  
		    final FutureReport future = new FutureReport(rid);  
		    //启动新的线程  
		    new Thread() {                     
		      public void run() {        
		    	  ReportServiceImpl reportdata=null;
				try {
					reportdata = new ReportServiceImpl(listcom);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
		        future.setReportData(reportdata);
		        try {
					future.getReport();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
		      }                         
		    }.start();   
			 logger.info(listcom.toString()+"请求开始执行结束");
		    // (3) 取回FutureData实体，作为传回值
		    return future;  
		  }  

}
