package com.za.qa.common; 

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年1月17日 下午5:31:03 
 * 类说明 MyBatis 配置
 */
@Configuration
public class BatisConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(BatisConfiguration.class);
	
    @Bean
    public PageHelper pageHelper() {
        logger.info("注册MyBatis分页插件PageHelper");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }

}
 