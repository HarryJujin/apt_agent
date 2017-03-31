package com.za.qa.hessianbean;

import java.io.Serializable;

/**
 * @author jujinxin
 * @version 创建时间：2016年11月23日 下午4:47:35 类说明
 */
public class CaseReportDTO implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 607044678313659605L;
    
    private String            projectId;
    private String            taskId;
    private String            caseNo;
    private String            url;
    private String            request;                           //解析后的入参
    private String            reponse;
    private String            checkpoint;
    private String            test_result;
    private String            errorInfo;                             //错误信息
    private String            durationTime;
    private CaseDataDTO       caseDataDTO;                           //完整的测试数据
    
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
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	public String getCheckpoint() {
		return checkpoint;
	}
	public void setCheckpoint(String checkpoint) {
		this.checkpoint = checkpoint;
	}
	public String getTest_result() {
		return test_result;
	}
	public void setTest_result(String test_result) {
		this.test_result = test_result;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public String getDurationTime() {
		return durationTime;
	}
	public void setDurationTime(String durationTime) {
		this.durationTime = durationTime;
	}
	public CaseDataDTO getCaseDataDTO() {
		return caseDataDTO;
	}
	
	public void setCaseDataDTO(CaseDataDTO caseDataDTO) {
		this.caseDataDTO = caseDataDTO;
	}
	@Override
	public String toString() {
		return "CaseReportDTO [projectId=" + projectId + ", taskId=" + taskId
				+ ", caseNo=" + caseNo + ", url=" + url + ", request="
				+ request + ", reponse=" + reponse + ", checkpoint="
				+ checkpoint + ", test_result=" + test_result + ", errorInfo="
				+ errorInfo + ", durationTime=" + durationTime
				+ ", caseDataDTO=" + caseDataDTO + "]";
	}
	



}
