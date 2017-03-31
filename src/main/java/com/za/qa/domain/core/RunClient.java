package com.za.qa.domain.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.za.qa.domain.analyze.DataAnalyze;
import com.za.qa.domain.dto.CaseAfterRunDTO;
import com.za.qa.domain.dto.CaseBeforeRunDTO;
import com.za.qa.domain.dto.CaseDTO;
import com.za.qa.domain.enums.CaseType;
import com.za.qa.domain.http.HttpClientA;
import com.za.qa.domain.verify.DataVerify;
import com.za.qa.hessianbean.CaseDataDTO;
import com.za.qa.hessianbean.CaseReportDTO;
import com.za.qa.hessianbean.CombineReport;
import com.za.qa.keywords.ExpressionRegister;
import com.za.qa.utils.AgentException;
import com.za.qa.utils.ClientUtils;
import com.za.qa.utils.JSONUtils;
import com.za.qa.utils.Utilities;
import com.zhongan.qa.utils.HttpClientUtils;
import com.zhongan.qa.utils.HttpResponseUtils;
import com.zhongan.scorpoin.biz.common.CommonRequest;
import com.zhongan.scorpoin.biz.common.CommonResponse;
import com.zhongan.scorpoin.common.ZhongAnApiClient;

/**
 * @author jujinxin
 * @version 创建时间：2017年2月8日 上午10:22:19 类说明
 */
public class RunClient {

    private static Logger logger = LoggerFactory.getLogger(RunClient.class);

    public CombineReport runsuite(List<CaseDataDTO> list) throws Exception {
        LinkedList<CaseReportDTO> Report = new LinkedList<CaseReportDTO>();
        CombineReport combineReport = new CombineReport();
        for (int j = 0; j < list.size(); j++) {
            CaseDataDTO casedataDTO = list.get(j);
            ClientUtils.caseStartLog(casedataDTO);
            CaseBeforeRunDTO casebeforerunDTO = CaseDTO.getCaseBeforeRunDTO(casedataDTO);
            CaseAfterRunDTO caseafterrunDTO = CaseDTO.getCaseAfterRunDTO(casedataDTO, casebeforerunDTO);
            CaseReportDTO caseReportDTO = CaseDTO.getCaseReportDTO(casedataDTO, caseafterrunDTO, casebeforerunDTO);
            Report.add(caseReportDTO);
            ClientUtils.caseEndLog(casedataDTO);
        }
        combineReport.setReport(Report);
        return combineReport;
    }

    public static void saveDataToMap(String key, String bizcontent, String response) {
        ExpressionRegister.setPayloadEnv(key, bizcontent);
        ExpressionRegister.setResponseEnv(key, response);
    }

    public static String runClient(CaseDataDTO casedataDTO, CaseBeforeRunDTO casebeforerunDTO) throws Exception {
        String response = "";

        if (casedataDTO.getApi_type().equalsIgnoreCase(CaseType.OpenAPI.getCode())) {
            response = runSDKclient(casedataDTO, casebeforerunDTO);
        } else if ((casedataDTO.getApi_type().equalsIgnoreCase(CaseType.HSF.getCode()))) {
            response = runHSFclient(casedataDTO, casebeforerunDTO);
        } else if ((casedataDTO.getApi_type().equalsIgnoreCase(CaseType.HTTP.getCode()))) {
            response = runHttpclient(casedataDTO, casebeforerunDTO);
        } else if((casedataDTO.getApi_type().equalsIgnoreCase(CaseType.SOAP.getCode()))){
        	response = runSOAPclient(casedataDTO, casebeforerunDTO);
        }
        	else {
            logger.info(String.format("当前用例:%s接口类型异常,忽略,开始执行下一条", casedataDTO.getApi_type()));
        }
        return response;
    }

    public static String runSDKclient(CaseDataDTO casedataDTO, CaseBeforeRunDTO casebeforerunDTO) throws Exception {
        String response_bizcontent = "";
        String bizcontent = casebeforerunDTO.getRequest();
        if (DataVerify.verifyPayload(bizcontent)) {
            logger.info(String.format("当前用例:%s入参解析失败,忽略,开始执行下一条", bizcontent));

        } else {
            JSONObject reqJson = ClientUtils.Str2JsonObject(bizcontent);
            ZhongAnApiClient client = new ZhongAnApiClient(casedataDTO.getEnv(), casedataDTO.getAppkey(),
                    casedataDTO.getDevprivatekey(), casedataDTO.getVersion());
            CommonRequest request = new CommonRequest(casedataDTO.getServiceName());
            request.setParams(reqJson);
            try {
                CommonResponse response = new CommonResponse();
                try {
                    response = (CommonResponse) client.call(request, casedataDTO.getIp());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if (response.getBizContent() == null) {
                    JSONObject responseJson = new JSONObject();
                    responseJson.put("errorCode", response.getErrorCode());
                    responseJson.put("errorMsg", response.getErrorMsg());
                    responseJson.put("charset", response.getCharset());
                    responseJson.put("format", response.getFormat());
                    responseJson.put("signType", response.getSignType());
                    responseJson.put("timestamp", response.getTimestamp());
                    response_bizcontent = responseJson.toJSONString();
                } else {
                    response_bizcontent = response.getBizContent();
                }
            } catch (Exception e) {
            	response_bizcontent="请求异常";
				throw new AgentException(String.format("无效接口请求:%s", casedataDTO.getServiceName()));
            }
            saveDataToMap(casedataDTO.getProjectId() + "_" + casedataDTO.getStep_num(), reqJson.toJSONString(),
                    response_bizcontent);
            logger.info("bizcontent: " + response_bizcontent);
        }
        return response_bizcontent;
    }

    public static String runHSFclient(CaseDataDTO casedataDTO, CaseBeforeRunDTO casebeforerunDTO) throws Exception {
        String response = "";
        String url = "";
        String ArgsObjects = casebeforerunDTO.getRequest();
        if (DataVerify.verifyPayload(ArgsObjects)) {
            logger.info(String.format("当前用例:%s入参解析失败,忽略,开始执行下一条", ArgsObjects));
        } else {
            String ArgsTypes = casedataDTO.getRequestDto();
            if (casedataDTO.getIp().contains("http://")) {
                url = casedataDTO.getIp() + casedataDTO.getResourcePath();
            } else {
                url = "http://" + casedataDTO.getIp() + casedataDTO.getResourcePath();
            }
            logger.info("接口访问地址: " + url);
            try {
                //response = HttpClientA.hsfHttpPost(url, ArgsTypes, ArgsObjects);
                CloseableHttpResponse closehttp = HttpClientUtils.testHSF(null, null, url, "POST", ArgsTypes, ArgsObjects);
				 response = HttpResponseUtils.getResponseContent(closehttp);
            } catch (Exception e) {
            	response="请求异常";
				throw new AgentException(String.format("无效接口请求:%s", url));
            }
            saveDataToMap(casedataDTO.getProjectId() + "_" + casedataDTO.getStep_num(), ArgsObjects, response);
            logger.info("bizcontent: " + response);
        }
        return response;
    }

    public static String runHttpclient(CaseDataDTO casedataDTO, CaseBeforeRunDTO casebeforerunDTO) throws Exception {
    	Map<String, String> headerMap = new HashMap<String, String> ();
        headerMap = casedataDTO.getHeader();
        if(null!=headerMap){
        	String headers=JSONUtils.Map2JsonString(headerMap);
        	headers = DataAnalyze.analyzeStr(headers);
        	logger.info("headers:"+headers);
        	headerMap = JSONUtils.JsonString2Map(headers);
        }
    	String response = "";
        String url = "";
        String bizcontent = casebeforerunDTO.getRequest();
        if (DataVerify.verifyPayload(bizcontent)) {
            logger.info(String.format("当前用例:%s入参解析失败,忽略,开始执行下一条", bizcontent));
        } else {
            url = casedataDTO.getIp() + casedataDTO.getResourcePath();
            url = DataAnalyze.analyzeStr(url);
            if (!casedataDTO.getIp().contains("http://")) {
                url = "http://" + casedataDTO.getIp() + casedataDTO.getResourcePath();
            }
            logger.info("接口访问地址: " + url);
            try {
				if(casedataDTO.getHttp_type().equalsIgnoreCase("POST")){
					if(JSONUtils.isJson(bizcontent)){
						//headerMap.put("Content-Type", "application/json; charset=utf-8");
						CloseableHttpResponse httpresponse =HttpClientUtils.testWithJson(headerMap, null, url, "POST", bizcontent);
						response = HttpResponseUtils.getResponseContent(httpresponse);
					}else{
						CloseableHttpResponse httpresponse =HttpClientUtils.testWithKeyValue(headerMap, null, url, "POST", Utilities.serializeFormat(bizcontent));
						response = HttpResponseUtils.getResponseContent(httpresponse);
					}
				}
				 else if (casedataDTO.getHttp_type().equalsIgnoreCase("GET")) {
					 CloseableHttpResponse httpresponse = HttpClientUtils.testWithKeyValue(null, null, url, "GET", null);
					 response = HttpResponseUtils.getResponseContent(httpresponse);                
				}
			} catch (Exception e) {
				response="请求异常";
				throw new AgentException(String.format("无效接口请求:%s", url));				
			}             
            saveDataToMap(casedataDTO.getProjectId() + "_" + casedataDTO.getStep_num(), bizcontent, response);
            logger.info("bizcontent: " + response);
        }
        return response;
    }
    
    public static String runSOAPclient(CaseDataDTO casedataDTO, CaseBeforeRunDTO casebeforerunDTO) throws Exception{
    	String response = "";
        String url = "";
        String bizcontent = casebeforerunDTO.getRequest();
        if (DataVerify.verifyPayload(bizcontent)) {
            logger.info(String.format("当前用例:%s入参解析失败,忽略,开始执行下一条", bizcontent));
        } else {
        	 url = casedataDTO.getIp() + casedataDTO.getResourcePath();
             url = DataAnalyze.analyzeStr(url);
             if (!casedataDTO.getIp().contains("http://")) {
                 url = "http://" + casedataDTO.getIp() + casedataDTO.getResourcePath();
             }
             logger.info("接口访问地址: " + url);
             Map<String, String> headerMap = new HashMap<String, String> ();
             headerMap = casedataDTO.getHeader();
			 //headerMap.put("Content-Type","text/xml; charset=utf-8");
			 try {
				CloseableHttpResponse closehttp = HttpClientUtils.testWithXml(headerMap, null, url, "POST", bizcontent);
				 response = HttpResponseUtils.getResponseContent(closehttp);
			
			 if (response != null) {
                 if (response.indexOf("&lt;") > -1 & response.indexOf("&gt;") > -1) {
                     response = response.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"");
                 }
             }
			 } catch (Exception e) {
					response="请求异常";
					throw new AgentException(String.format("无效接口请求:%s", url));	
				}
        }
    	return response;
    }

  

}
