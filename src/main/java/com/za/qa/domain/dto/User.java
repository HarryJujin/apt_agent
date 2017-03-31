package com.za.qa.domain.dto; 
/** 
 * @author jujinxin 
 * @version 创建时间：2017年1月17日 下午4:09:42 
 * 类说明 
 */
public class User {
	
	private String name;
	private String pwd;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", pwd=" + pwd + "]";
	}
	

}
 