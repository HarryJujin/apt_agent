package com.za.qa.mapper; 

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.za.qa.domain.dto.Configuration;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年1月17日 上午10:04:45 
 * 类说明 
 */

public interface ConfigurationMapper {
	@Select("select * from auto_test_00.api_data_configuration where env =#{env}")
	List<Configuration> findConfigurationBy(String env);

}
 