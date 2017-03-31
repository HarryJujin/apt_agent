package com.za.qa.hessianservice; 

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年3月7日 下午5:42:40 
 * 类说明 
 */
public interface HeartService {
	public List<Object> heart() throws UnknownHostException, IOException; 

}
 