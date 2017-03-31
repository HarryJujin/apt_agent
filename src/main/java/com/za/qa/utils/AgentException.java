package com.za.qa.utils; 
/** 
 * @author jujinxin 
 * @version 创建时间：2017年3月31日 上午10:41:35 
 * 类说明 
 */
public class AgentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7433641172439389159L;
	
	public AgentException(){
		super();
	}
	
	 public AgentException(String message) {
	        super(message);
	    }
	 
	 public AgentException(String message, Throwable cause) {
	        super(message, cause);
	    }
	


}
 