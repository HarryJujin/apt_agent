package com.za.qa.hessianservice; 

import java.util.LinkedList;

import com.za.qa.hessianbean.CombineData;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年3月7日 下午5:35:21 
 * 类说明 
 */
public interface ExecuteService {
	public boolean  remoteExecute(String rid,LinkedList<CombineData> listcom);

}
 