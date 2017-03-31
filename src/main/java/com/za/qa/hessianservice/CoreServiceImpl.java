package com.za.qa.hessianservice; 

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import com.za.qa.domain.core.CoreCallable;
import com.za.qa.hessianbean.CombineData;
import com.za.qa.hessianbean.CombineReport;
import com.za.qa.utils.SimpleDateUtils;
/** 
 * @author jujinxin 
 * @version 创建时间：2017年2月13日 上午10:08:00 
 * 类说明 
 */
@Service("coreService")
public class CoreServiceImpl implements CoreService {
	

	@SuppressWarnings("rawtypes")
	@Override
	public LinkedList<CombineReport> execute(LinkedList<CombineData> listcom) throws InterruptedException, ExecutionException {
		Date start = new Date(System.currentTimeMillis());
		String BeginTime= SimpleDateUtils.getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
		int threadNum =40;
		List<Future> list = new ArrayList<Future>();
		ExecutorService pool = Executors.newFixedThreadPool(threadNum);		
		LinkedList<CombineReport> CombineReportList = new LinkedList<CombineReport>();
		for(int i =0;i<listcom.size();i++){
			CombineData  combinedata= listcom.get(i);											
					Callable <List> coreService =  new CoreCallable(combinedata);
					Future <List>  f= pool.submit(coreService);
			        list.add(f);			
				}					
		pool.shutdown();
		for (Future<?> f : list) {
			CombineReportList.addAll((LinkedList<CombineReport>) f.get());
		}
		return CombineReportList;
	}


}
