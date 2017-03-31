package com.za.qa.hessianbean; 

import java.io.Serializable;
import java.util.Map;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年1月20日 上午10:43:57 
 * 类说明 
 */
public class CaseDataDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2683664773946636349L;
	
    private String            projectId;
    private String            taskId;                //任务编号暂时用上
    private String            caseNo;
    private String            caseName;
    private String            env;
	private String            ip;
    private String            version;
    private String            appkey;
    private String            devprivatekey;
    private String            api_type;
    private String            data;
    private String            serviceName;
    private String            resourcePath;
    private String            requestDto;    
    private String            caseDesc;              //case描述
    private String            checkPoint;
    private String            proProcessor;
    private String            postProcessor;
	private String            charset;
	private String            http_type;
	private String            analyze_data;
	private Map<String,String>               header;      //http请求头
	private String            step_num;                   //依赖case编号
	private String            step_name;                  //依赖case名称
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getEnv() {
		return env;
	}
	public void setEnv(String env) {
		this.env = env;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
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
	public String getApi_type() {
		return api_type;
	}
	public void setApi_type(String api_type) {
		this.api_type = api_type;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getResourcePath() {
		return resourcePath;
	}
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	public String getRequestDto() {
		return requestDto;
	}
	public void setRequestDto(String requestDto) {
		this.requestDto = requestDto;
	}
	public String getCaseDesc() {
		return caseDesc;
	}
	public void setCaseDesc(String caseDesc) {
		this.caseDesc = caseDesc;
	}
	public String getCheckPoint() {
		return checkPoint;
	}
	public void setCheckPoint(String checkPoint) {
		this.checkPoint = checkPoint;
	}
	public String getProProcessor() {
		return proProcessor;
	}
	public void setProProcessor(String proProcessor) {
		this.proProcessor = proProcessor;
	}
	public String getPostProcessor() {
		return postProcessor;
	}
	public void setPostProcessor(String postProcessor) {
		this.postProcessor = postProcessor;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getHttp_type() {
		return http_type;
	}
	public void setHttp_type(String http_type) {
		this.http_type = http_type;
	}

	public String getAnalyze_data() {
		return analyze_data;
	}
	public void setAnalyze_data(String analyze_data) {
		this.analyze_data = analyze_data;
	}
	public Map<String, String> getHeader() {
		return header;
	}
	public void setHeader(Map<String, String> header) {
		this.header = header;
	}
	public String getStep_num() {
		return step_num;
	}
	public void setStep_num(String step_num) {
		this.step_num = step_num;
	}
	public String getStep_name() {
		return step_name;
	}
	public void setStep_name(String step_name) {
		this.step_name = step_name;
	}
	@Override
	public String toString() {
		return "CaseDataDTO [projectId=" + projectId + ", taskId=" + taskId
				+ ", caseNo=" + caseNo + ", caseName=" + caseName + ", env="
				+ env + ", ip=" + ip + ", version=" + version + ", appkey="
				+ appkey + ", devprivatekey=" + devprivatekey + ", api_type="
				+ api_type + ", data=" + data + ", serviceName=" + serviceName
				+ ", resourcePath=" + resourcePath + ", requestDto="
				+ requestDto + ", caseDesc=" + caseDesc + ", checkPoint="
				+ checkPoint + ", proProcessor=" + proProcessor
				+ ", postProcessor=" + postProcessor + ", charset=" + charset
				+ ", http_type=" + http_type + ", analyze_data=" + analyze_data
				+ ", header=" + header + ", step_num=" + step_num
				+ ", step_name=" + step_name + "]";
	}
	


}
 