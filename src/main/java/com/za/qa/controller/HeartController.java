package com.za.qa.controller; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Component;

import com.za.qa.hessianservice.CoreService;
import com.za.qa.hessianservice.HeartService;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年2月14日 下午4:25:43 
 * 类说明 
 */
@Component
public class HeartController {

	@Autowired
	HeartService heartService;
	
/*	  @Bean(name = "/heartService")
    public HessianServiceExporter CoreAPIService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(heartService);
        exporter.setServiceInterface(HeartService.class);
        return exporter;
    }*/
}
 