package com.za.qa.domain.dto; 

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.za.qa.domain.analyze.DataAnalyze;
import com.za.qa.domain.core.RunClient;
import com.za.qa.domain.enums.CaseStatus;
import com.za.qa.domain.enums.CaseType;
import com.za.qa.domain.enums.ErrorCodenum;
import com.za.qa.domain.processor.Postprocessor;
import com.za.qa.domain.processor.Proprocessor;
import com.za.qa.domain.verify.DataVerify;
import com.za.qa.domain.verify.ResponseVerify;
import com.za.qa.domain.verify.SqlVerify;
import com.za.qa.hessianbean.CaseDataDTO;
import com.za.qa.hessianbean.CaseReportDTO;

/** 
 * @author jujinxin 
 * @version 创建时间：2016年12月6日 下午3:52:08 
 * 类说明 
 */
public class CaseDTO {
	/**
	 * @author jujinxin
	 *
	 */	
    private static Logger logger = LoggerFactory.getLogger(CaseDTO.class);  
    //解析后DTO
    public static CaseBeforeRunDTO getCaseBeforeRunDTO(CaseDataDTO casedataDTO) throws Exception{
    	CaseBeforeRunDTO casebeforeDTO = new CaseBeforeRunDTO();
    	if(casedataDTO.getProProcessor()!=null){
        	Proprocessor.doProprocessor(casedataDTO);
    	}
    	String request =DataAnalyze.getAnalyzeData(casedataDTO);
    	String checkpoint= casedataDTO.getCheckPoint();
    	//String ListPath=casedataDTO.getProjectId()+"_"+casedataDTO.getTaskId();
    	casebeforeDTO.setRequest(request);
    	casebeforeDTO.setCheckpoint(checkpoint);
    	return casebeforeDTO;
    }
    //run client
    public static CaseAfterRunDTO getCaseAfterRunDTO(CaseDataDTO casedataDTO,CaseBeforeRunDTO casebeforeDTO) throws Exception{
    	CaseAfterRunDTO caseafterrunDTO = new CaseAfterRunDTO();
    	 Date start_time = new Date();
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String s_start_time = df.format(start_time);
        logger.info(s_start_time);
    	caseafterrunDTO.setReponse(RunClient.runClient(casedataDTO, casebeforeDTO));
    	Date end_time = new Date();
        String s_end_time = df.format(end_time);
        logger.info(s_end_time);
        float time_diff = (end_time.getTime() - start_time.getTime())/1000;//时间差单位秒
        caseafterrunDTO.setDurationTime(Float.toString(time_diff));
    	if(casedataDTO.getPostProcessor()!=null){
        	Postprocessor.doPostprocessor(casedataDTO);
    	}
    	return caseafterrunDTO;
    }
    
    public static CaseReportDTO getCaseReportDTO(CaseDataDTO casedataDTO,CaseAfterRunDTO caseafterrunDTO,CaseBeforeRunDTO casebeforerunDTO) throws Exception{
    	String Response=caseafterrunDTO.getResponse();
    	CaseReportDTO caseReportDTO = new CaseReportDTO();
    	    caseReportDTO.setProjectId(casedataDTO.getProjectId());
    	    caseReportDTO.setTaskId(casedataDTO.getTaskId());
    	    caseReportDTO.setCaseNo(casedataDTO.getCaseNo());
    		caseReportDTO.setCaseDataDTO(casedataDTO);
        	caseReportDTO.setCheckpoint(casebeforerunDTO.getCheckpoint());
        	caseReportDTO.setDurationTime(caseafterrunDTO.getDurationTime());
    		String Payloaddata=casebeforerunDTO.getRequest();
    		//入参解析失败set report
    		if(DataVerify.verifyPayload(Payloaddata)){			
    			caseReportDTO.setRequest(casebeforerunDTO.getRequest()); //重点关注入参中哪个字段解析失败
    			caseReportDTO.setReponse(Response);     //response应为空
    			caseReportDTO.setErrorInfo(ErrorCodenum.analyze_fail.getName());
            	caseReportDTO.setTest_result(CaseStatus.SKIP.getCode());     //只要参数未解析成功，接口结果为SKIP 
    		}if((!DataVerify.verifyPayload(Payloaddata)&&Response.length()<3)||Response.equals("getResponseError")||Response.equals("请求异常")){
    			caseReportDTO.setRequest(casebeforerunDTO.getRequest()); 
    			caseReportDTO.setReponse(Response);     //response应为空
    			caseReportDTO.setErrorInfo(ErrorCodenum.respone_is_empty.getName());
            	caseReportDTO.setTest_result(CaseStatus.FAIL.getCode());     //接口数据肯能过期
    		}if(casedataDTO.getEnv()==null||casedataDTO.getEnv().trim().length()<1){
    			caseReportDTO.setRequest(casebeforerunDTO.getRequest()); 
    			caseReportDTO.setReponse(Response);     //response应为空
    			caseReportDTO.setErrorInfo(ErrorCodenum.env_is_empty.getName());
            	caseReportDTO.setTest_result(CaseStatus.SKIP.getCode());     
    		}if(casedataDTO.getServiceName()==null&&casedataDTO.getApi_type().equals(CaseType.OpenAPI.getName())){
    			caseReportDTO.setRequest(casebeforerunDTO.getRequest()); 
    			caseReportDTO.setReponse(Response);     //response应为空
    			caseReportDTO.setErrorInfo(ErrorCodenum.serviceName_is_empty.getName());
            	caseReportDTO.setTest_result(CaseStatus.SKIP.getCode()); 
    		}	
    		else{   			
    			String result="";
				try {
					result = ResponseVerify.check(casedataDTO,caseafterrunDTO);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Response ="接口异常";
					e.printStackTrace();
				}
				//增加sql查询校验
			/*	try {
					String sqlResult = SqlVerify.Sqlcheck(casedataDTO,caseafterrunDTO);
					if (!CaseStatus.PASS.getCode().equals(sqlResult)){
						result = result +";"+"\n"+sqlResult;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					result=result+";Sql校验异常";
					e.printStackTrace();
				}*/
    			//判断是否是xml报文进行请求和返回字符串转义
    			if(Payloaddata!=null&&Payloaddata.length()>1){
    				if(isXmlStr(Payloaddata)){
    					Payloaddata=Payloaddata.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;");
    				}
    			}
    			if(Response!=null&&Payloaddata.length()>1){
    				if(isXmlStr(Response)){
    					Response=Response.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;");
    				}
    			}
    			//测试
    			//Response=Response+ExpressionRegister.getSqlSelectEnv().get(caseConfigurationDTO.getListPath()+"#"+casedataDTO.getCaseNo());
    			caseReportDTO.setRequest(Payloaddata);
    			caseReportDTO.setReponse(Response);
    	    	
    			caseReportDTO.setUrl(casedataDTO.getIp()+casedataDTO.getResourcePath());
    	    	if(CaseStatus.PASS.getCode().equalsIgnoreCase(result)){
    	    		caseReportDTO.setTest_result(result);   //验证成功，接口结果为Pass
    	    		caseReportDTO.setErrorInfo(ErrorCodenum.success.getName());
    	    	}else{
    	    		caseReportDTO.setTest_result(CaseStatus.FAIL.getCode());  //验证失败，接口结果为Fail
    	    		caseReportDTO.setErrorInfo(result);
    	    	}
    		}
    	return caseReportDTO;
    }
    

    
    public static CaseReportRelativeDTO getCaseReportRelativeDTO(String BeginTime,String EndTime,long DuringTime,LinkedList <CaseReportDTO> caseReportDTOList){
    	CaseReportRelativeDTO caseReportRelativeDTO = new CaseReportRelativeDTO();

    	caseReportRelativeDTO.setReportFileName("众安接口测试报告");
        caseReportRelativeDTO.setBeginTime(BeginTime);
        caseReportRelativeDTO.setEndTime(EndTime);
        caseReportRelativeDTO.setDuringTime(DuringTime+"秒");
        caseReportRelativeDTO.setPassedPercent(PassedPercent(caseReportDTOList));
        caseReportRelativeDTO.setSkippedPercent(SkippedPercent(caseReportDTOList));
        caseReportRelativeDTO.setFailedPercent(FailedPercent(caseReportDTOList));
        caseReportRelativeDTO.setCaseNum(CaseNum(caseReportDTOList)+"");
        caseReportRelativeDTO.setFailedNum(FailedNum(caseReportDTOList)+"");
        caseReportRelativeDTO.setSkippedNum(SkippedNum(caseReportDTOList)+"");
        caseReportRelativeDTO.setPassedNum(PassedNum(caseReportDTOList)+"");
    	return caseReportRelativeDTO;
    }
    
 
    
    public static int PassedNum(LinkedList <CaseReportDTO> caseReportDTOList){
    	int num=0;
    	for(int i=0;i<caseReportDTOList.size();i++){
    		if(caseReportDTOList.get(i).getTest_result().equalsIgnoreCase(CaseStatus.PASS.getCode())){
    			num++;
    		}
    	}
    	return num;
    }
    public static int FailedNum(LinkedList <CaseReportDTO> caseReportDTOList){
    	int num=0;
    	for(int i=0;i<caseReportDTOList.size();i++){
    		if(caseReportDTOList.get(i).getTest_result().equalsIgnoreCase(CaseStatus.FAIL.getCode())){
    			num++;
    		}
    	}
    	return num;
    }
    public static int SkippedNum(LinkedList <CaseReportDTO> caseReportDTOList){
    	int num=0;
    	for(int i=0;i<caseReportDTOList.size();i++){
    		if(caseReportDTOList.get(i).getTest_result().equalsIgnoreCase(CaseStatus.SKIP.getCode())){
    			num++;
    		}
    	}
    	return num;
    }
    
    public static int CaseNum(LinkedList <CaseReportDTO> caseReportDTOList){
    	int num=0;
    	for(int i=0;i<caseReportDTOList.size();i++){
    		if(!caseReportDTOList.get(i).getTest_result().equalsIgnoreCase(CaseStatus.NOTRUN.getCode())){
    			num++;
    		}
    	}
    	return num;
    }
    
    public static String PassedPercent(LinkedList <CaseReportDTO> caseReportDTOList){
    	NumberFormat numberFormat = NumberFormat.getInstance();
    	numberFormat.setMaximumFractionDigits(2);
    	String result =numberFormat.format((float)PassedNum(caseReportDTOList)/(float)CaseNum(caseReportDTOList)*100);
    	String percent = result+"%";
    	return percent;
    }
    
    public static String FailedPercent(LinkedList <CaseReportDTO> caseReportDTOList){
    	NumberFormat numberFormat = NumberFormat.getInstance();
    	numberFormat.setMaximumFractionDigits(2);
    	String result =numberFormat.format((float)FailedNum(caseReportDTOList)/(float)CaseNum(caseReportDTOList)*100);
    	String percent = result+"%";
    	return percent;
    }
    
    public static String SkippedPercent(LinkedList <CaseReportDTO> caseReportDTOList){
    	NumberFormat numberFormat = NumberFormat.getInstance();
    	numberFormat.setMaximumFractionDigits(1);
    	String result =numberFormat.format((float)SkippedNum(caseReportDTOList)/(float)CaseNum(caseReportDTOList)*100);
    	String percent = result+"%";
    	return percent;
    }
    public static boolean isXmlStr(String dataStr){
    	String Response = dataStr.trim().replace("\\", "").replace("\"", "").replace(" ", "");
    	String firstChar = Response.substring(0, 1);
    	String lastChar = Response.substring(Response.length() - 1,
    			Response.length());
    	// 处理xml字符串
    	if (firstChar.equals("<") && lastChar.equals(">")) {
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public static LinkedList <CaseReportDTO> showfailonly(LinkedList <CaseReportDTO> caseReportDTOList){
    	LinkedList <CaseReportDTO> failreportlist = new LinkedList <CaseReportDTO>();
    	for(CaseReportDTO casereportdto:caseReportDTOList){
    		if(casereportdto.getTest_result()!=CaseStatus.PASS.getCode()&&casereportdto.getTest_result()!=CaseStatus.NOTRUN.getCode())
    			failreportlist.add(casereportdto);
    	}
    	return failreportlist;
    }
	
}
 

