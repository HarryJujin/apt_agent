package com.za.qa.hessianservice; 

import java.net.MalformedURLException;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.client.HessianProxyFactory;
import com.za.qa.hessianbean.CombineReport;
import com.za.qa.hessianbean.TaskCombineReport;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年2月28日 下午3:17:00 
 * 类说明 
 */
public class FutureReport implements ReportService {
	
	private static Logger logger = LoggerFactory.getLogger(FutureReport.class);
	private ReportService reportservice= null; 
	private boolean ready = false;  
    private String rid;
    
	public FutureReport (String rid){
		this.rid = rid;
	}

	
	  public synchronized void setReportData(ReportService reportservice) {  
	    if (ready) {              
	      return;   // 防止setReportData被调用两次以上。 
	    }  
	    this.reportservice = reportservice;  
	    this.ready = true; 
	    logger.info("当前任务接口测试结束");
	    notifyAll();  
	  } 
	  
	  /**
	   * 等待报告
	 * @throws MalformedURLException 
	   */
	@Override
	public synchronized  LinkedList<CombineReport> getReport() throws MalformedURLException {
		while (!ready) {  
		      try {  
		        wait();  
		      } catch (InterruptedException e) {  
		      }  
		    }  
		//调用client服务输出报告
		logger.info("测试完成，发出测试报告数据");
		 HessianProxyFactory factory = new HessianProxyFactory();
		 TaskCombineReport taskCombineReport =new TaskCombineReport();
		 taskCombineReport.setReportEntity(reportservice.getReport());
		 taskCombineReport.setRid(rid);
		 TestReportRecordService testReportRecordService = (TestReportRecordService) factory.create(TestReportRecordService.class,
                    "http://localhost:7090/remoteReportRecord"
				 /*"http://192.168.201.167:9090/remoteReportRecord"*/ );
		 testReportRecordService.remoteReportRecord(taskCombineReport);
		 System.out.println("远程执行");
		// TODO Auto-generated method stub
		return reportservice.getReport();
	}
	


}
