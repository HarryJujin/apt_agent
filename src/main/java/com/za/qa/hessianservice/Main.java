package com.za.qa.hessianservice; 

import java.util.LinkedList;

import com.za.qa.hessianbean.CombineData;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年3月2日 上午10:28:35 
 * 类说明 
 */
public class Main {/*

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CaseDataDTO casedata =new CaseDataDTO();
    	CaseDataDTO casedata2 =new CaseDataDTO();
    	CombineData combindata =new CombineData();
    	//HSF接口
    	casedata.setProjectId("P001");
    	casedata.setCaseNo("TC001");
    	casedata.setEnv("uat");
    	casedata.setProProcessor("");
    	casedata.setPostProcessor("");
    	casedata.setCheckPoint("");
    	casedata.setAppkey("");
    	casedata.setCharset("");
    	casedata.setHttp_type("");
    	casedata.setServiceName("");
    	casedata.setVersion("");
    	casedata.setTaskId("T001");
    	casedata.setRequestDto("com.zhongan.core.user.dto.CusUserCompanyDTO");
    	casedata.setResourcePath("/com.zhongan.core.user.service.CusCompanyService:1.0.0/findListByCondition");
    	casedata.setIp("10.139.34.119:8086");
    	casedata.setData("[{\"userId\":4165025}]");
    	casedata.setApi_type("HSF");
    	//OpenAPI接口
    	casedata2.setApi_type("OpenAPI");
    	casedata2.setProjectId("P002");
    	casedata2.setCaseNo("TC002");
    	casedata2.setStep_num("TC002");
    	casedata2.setEnv("iTest");
    	casedata2.setProProcessor("");
    	casedata2.setPostProcessor("");
    	casedata2.setCheckPoint("msgCode==1"+"\n"
        +"msgInfo>>保单信息验证成功"+"\n"
    	+"@ResponseDepend(\"TC002\",\"policyId\")==@ResponseCurrent(\"policyId\")");
    	casedata2.setAppkey("10001");
    	casedata2.setCharset("");
    	casedata2.setDevprivatekey("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAO8h8JCJAMb1nd0uBkzZuWyNnL+atBzJKvIG7escD45ODf0AWKr8vSqLZ01HD86+a496CGjsae6GybK8C1MqiMSwaAsIv31nKD6U8xF607MPrD3r2lyjwUnmqBZY++R6yFNYz9ZDXcdiwCudESRsXunPJq7zfnnglCtEH+qqW8/VAgMBAAECgYEAnVc2gtMyKLbZTPuId65WG7+9oDB5S+ttD1xR1P1cmuRuvcYpkS/Eg6a/rJASLZULDpdbyzWqqaAUPD8QMINvAr3ZtkbwH5R0F/4aqOlx/5B0Okjsp3eSK2bQ8J2m/MmFKZxr6Aily7YUDdxcGcjLizsGi1KDkWS22JRufEeUNA0CQQD+g1XJ7ELqmUtrS4m4XnadB25f0g5QC0tMjoa3d9soMzK3q+Drkv8EZVpGSmSHEo1VlE7HUcnKNvK1BO5Nm4iXAkEA8IeZxaWmEcsRqiuFz8xmYGtKcYTmHgheGF4D+fnnFozSNP+3sS1lfgFQrjUkqUyZOoG1hPc6SDhGS4nbXwiscwJASO+gPR58yrgledkK3ZAMk9GWWtVajqu953GMv7UUU//gD+yspzXX6Q2WgkA9cMvrPtQig1I37sAya5e/JvRkfwJARzzCDEmdP9PW7YFqZjrxb0kXiTuFNAviYnEl2FltWb5nW48JBo6dao5VKONQclvfXfagnjriphUUrLatpB3bhQJAKRfJS6jDAIVKt7So5HOdzk4ipxgrMjG/QtZ1grO+VQectk4+tCwdJhOrr5blvdPQvFVqXBQfXuE7cibZrGs4sQ==");
    	casedata2.setHttp_type("");
    	casedata2.setServiceName("zhongan.group.Policy.personalPolicyUnderwrite");
    	casedata2.setVersion("1.0.0-zxy");
    	casedata2.setTaskId("T001");
    	casedata2.setRequestDto("");
    	casedata2.setResourcePath("");
    	casedata2.setIp("10.139.115.27:8080");
    	casedata2.setData("infoJson={"
  +"\"insuredId\": \"@RNum(\"10\")\","
  +"\"campaignId\": \"10002156338\","
  +"\"packageId\": \"51252513\","
  +"\"effectiveDate\": \"$ParaReplace(\"@RDate(\"yyyyMMdd\",\"0/0/1\")000000\",\"effectiveDate\")\","
  +"\"insurantList\": ["
   + "{"
    + " \"amount\": \"1000000\","
    + " \"insurantGender\": \"\","
    + " \"insurantBirthday\": \"\","
    +  "\"insurantCertiNo\": \"@RIdWithBirthday(RDate(\"yyyyMMdd\",\"-25/0/0\"),\"2\")\","
    +  "\"insurantCertiType\": \"I\","
    + " \"insurantName\": \"投保人@RName()\","
    +  "\"insurantPhone\": \"18317183693\","
    +  "\"insuredRelaToHolder\": \"3\","
    +  "\"premium\": \"219.00\","
    +  "\"isMedicare\": \"Y\","
    + " \"insurantUnit\": \"1\""
    +"}"
  +"],"
  +"\"policyHolder\": {"
   +" \"holderGender\": \"\","
   + "\"holderBirthday\": \"\","
   + "\"holderCertiNo\": \"$ParaReplace(@RIdWithBirthday(RDate(\"yyyyMMdd\",\"-20/0/0\"),\"2\"),\"CertiNo\")\","
   + "\"holderCertiType\": \"I\","
   + "\"holderName\": \"投保人王涵蓄\","
   + "\"holderEmail\": \"yangxiaolinxx@zhongan.com\","
   + "\"holderPhone\": \"$ParaReplace(\"15900986780\",\"phone\")\""
  +"}"
+"}");
    	casedata2.setAnalyze_data("phone=@RPhone()"+"\n"
+"CertiNo=@RIdWithBirthday(RDate(\"yyyyMMdd\",\"-20/0/0\"),\"2\")"+"\n"
    			+"effectiveDate="
                                   );
    	
    	LinkedList<CaseDataDTO> listcase = new LinkedList<CaseDataDTO>();
    	LinkedList<CombineData> listcombine = new LinkedList<CombineData>();
    	//listcase.add(casedata);
    	for(int i=0;i<2;i++){
    		listcase.add(casedata2);
    	}
    	combindata.setData(listcase);    	
    	listcombine.add(combindata);
    	
    	executeMessage(listcombine);

	}
	
	public static String  executeMessage(LinkedList<CombineData> listcom)  {
		System.out.println("开始执行测试用例");
		Host host = new Host();
		ReportService reportservice = host.request(listcom);
		// TODO Auto-generated method stub
		//FutureReport futurereport = new FutureReport();
		reportservice.getReport();
		return reportservice+"用例执行中。。。。";
	}

*/}
