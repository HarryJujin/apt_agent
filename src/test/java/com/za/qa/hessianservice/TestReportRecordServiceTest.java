package com.za.qa.hessianservice; 

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.LinkedList;

import org.junit.Test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.za.qa.hessianbean.CombineReport;
import com.za.qa.hessianbean.TaskCombineReport;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年3月15日 上午10:39:50 
 * 类说明 
 */
public class TestReportRecordServiceTest {

	@Test
	public void test() throws MalformedURLException {
		HessianProxyFactory factory = new HessianProxyFactory();
		 LinkedList<CombineReport> listcombine=null;
		 TaskCombineReport taskCombineReport =new TaskCombineReport();
		 taskCombineReport.setReportEntity(listcombine);
		 taskCombineReport.setRid("88888");
		 TestReportRecordService testReportRecordService = (TestReportRecordService) factory.create(TestReportRecordService.class,
                   "http://192.168.201.167:9090/remoteReportRecord");
		 testReportRecordService.remoteReportRecord(taskCombineReport);
	}

}
 