package com.za.qa.hessianservice; 

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.za.qa.hessianservice.ReportService;
import com.za.qa.hessianbean.CombineData;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年2月28日 下午4:15:22 
 * 类说明 
 */
@Service("executeservice")
public class ExecuteServiceImpl implements ExecuteService {
    private static Logger logger = LoggerFactory.getLogger(ExecuteServiceImpl.class);


	

	@Override
	public boolean  remoteExecute(String rid,LinkedList<CombineData> listcom)  {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss"); 
		logger.info("开始执行测试用例"+df.format(System.currentTimeMillis()));
		boolean ready = false;
		try {
			Host host = new Host();
			ReportService reportservice = host.request(rid,listcom);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ready;
		}
		logger.info("开始执行测试用例结束"+df.format(System.currentTimeMillis()));
		ready=true;
		return ready;
	}
	
}
