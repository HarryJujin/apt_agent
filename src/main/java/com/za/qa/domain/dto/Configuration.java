package com.za.qa.domain.dto; 
/** 
 * @author jujinxin 
 * @version 创建时间：2017年1月6日 上午11:54:17 
 * 类说明 
 */
public class Configuration {
	
	private String id;
	private String ip;
	private String api_no;
	private String case_list_no;
	private String env;
	private String appkey;
	private String devprivatekey;
	private String version;
	private String is_delete;
	private String date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getApi_no() {
		return api_no;
	}
	public void setApi_no(String api_no) {
		this.api_no = api_no;
	}
	public String getCase_list_no() {
		return case_list_no;
	}
	public void setCase_list_no(String case_list_no) {
		this.case_list_no = case_list_no;
	}
	public String getEnv() {
		return env;
	}
	public void setEnv(String env) {
		this.env = env;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getDevprivatekey() {
		return devprivatekey;
	}
	public void setDevprivatekey(String devprivatekey) {
		this.devprivatekey = devprivatekey;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "configuration [id=" + id + ", ip=" + ip + ", api_no=" + api_no
				+ ", case_list_no=" + case_list_no + ", env=" + env
				+ ", appkey=" + appkey + ", devprivatekey=" + devprivatekey
				+ ", version=" + version + ", is_delete=" + is_delete
				+ ", date=" + date + "]";
	}


}
 