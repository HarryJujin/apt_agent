package com.za.qa; 

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年1月17日 上午9:54:20 
 * 类说明 
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.za.qa.mapper")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
		
	}

}
 