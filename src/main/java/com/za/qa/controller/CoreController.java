package com.za.qa.controller; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

import com.za.qa.hessianservice.CoreService;
import com.za.qa.hessianservice.ExecuteService;
/** 
 * @author jujinxin 
 * @version 创建时间：2017年1月20日 下午4:50:34 
 * 类说明 
 */
@Configuration
public class CoreController {
		 
	
	@Autowired
	CoreService coreService;
	
	@Autowired
	ExecuteService executeservice;
	
	  @Bean(name = "/coreAPIservice")
    public HessianServiceExporter CoreAPIService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(coreService);
        exporter.setServiceInterface(CoreService.class);
        return exporter;
    }
	  
	  @Bean(name = "/debugcoreAPIservice")
	    public HessianServiceExporter DebugCoreAPIService() {
	        HessianServiceExporter exporter = new HessianServiceExporter();
	        exporter.setService(coreService);
	        exporter.setServiceInterface(CoreService.class);
	        return exporter;
	    }
	  
	  @Bean(name = "/executeAPIservice")
	    public HessianServiceExporter ExecuteService() {
	        HessianServiceExporter exporter = new HessianServiceExporter();
	        exporter.setService(executeservice);
	        exporter.setServiceInterface(ExecuteService.class);
	        return exporter;
	    }
	  

}
 