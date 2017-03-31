package hessian.test;

import java.util.LinkedList;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.caucho.hessian.client.HessianProxyFactory;
import com.za.qa.hessianbean.CaseDataDTO;
import com.za.qa.hessianbean.CaseReportDTO;
import com.za.qa.hessianservice.CoreService;
import com.za.qa.hessianservice.ExecuteService;
import com.za.qa.hessianbean.CombineData;
import com.za.qa.hessianbean.CombineReport;

public class HessianTest {
	

	
    @Test
    public void CoreServiceTest() {
    	CaseDataDTO casedata =new CaseDataDTO();
    	CaseDataDTO casedata2 =new CaseDataDTO();
    	CaseDataDTO casedata3 =new CaseDataDTO();
    	CombineData combindata =new CombineData();
    	CombineData combindata2 =new CombineData();
    	CombineData combindata3 =new CombineData();

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
    	//casedata2.setProProcessor("");
    	//casedata2.setPostProcessor("");
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
    	//OpenAPI接口
    	casedata3.setProjectId("P003");
    	casedata3.setCaseNo("TC003");
    	casedata3.setEnv("uat");
    	casedata3.setProProcessor("");
    	casedata3.setPostProcessor("");
    	casedata3.setCheckPoint("");
    	casedata3.setAppkey("");
    	casedata3.setCharset("");
    	casedata3.setHttp_type("");
    	casedata3.setAppkey("bb3c2fd325588b235fe7fdfdb34edf8f");
    	casedata3.setDevprivatekey("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMDHR+G/GCTRNzutr85kS8aoanduasE6qVqyJXCGKSVWQODjdqyolUEswKFSpW9vYsFXv2WI2ZZRz1ZyXujI2R7PylDr0WfhA4bVJoLo3pxGv103hnLvfa+UjcLf45OSUtWs8XDd+bJhke6M65KtIlr1eOLO1wX/uztGg2iNs+ypAgMBAAECgYBC5KOfxOSG2gY1ciaAErNsosIW4TywAkAD1a9CJXuflhd7MdLbRMBN1k6dbAU17suw1Vb3z43ZKFgu4g7m7dLQ0RL3cVqOHsdAX5Yy8HkZA6KViXKdOUec7f9rSSR6DrcjjSSYPdU4IPe1gMxrclXqcRTOcgrF5sU6SLE4MYYPFQJBAN8K4TfVd9OORZmlavEP2PFQBd2/AdhFJJ12Xvwcj531F1Gwffv0Y3coc6qbBfkX+xPKdXE7mOtUBPJYe6ubtRMCQQDdQ5cN/EgGGlcHOCne4kFIRL31sIl8ezHBmr9Nm+jMzmFQAZD/KRKke+/AJmCckK/L5VhchiOtN90jjay+F1rTAkEAkQ8jYMu9zeJm08GoprU20+LGnew/B+gYIyy8I91zbjjbfK57kWTlV7cAWwHNxa5aBgoTjZxEHTEcsnznmbl/7wJBAJ2LqtrCb2YpCx6Q1v9kiqcyiy+Na9e+wEbiMKTKBpENwix9cQi97hBATw26RcA1JXdA7hGzUWPdCxBtqDoR1xECQE5MrNuumbfl9RK89j4eYzrrdFGXbsBxIEwQnWkBnEWhHvTtpI768jsTekere1i3oEeo78QptHaLb2JkDk3jnrw=");
    	casedata3.setServiceName("zhongan.tiancheng.traffic.accident.check");
    	casedata3.setVersion("1.0.0");
    	casedata3.setTaskId("T001");
    	casedata3.setRequestDto("");
    	casedata3.setResourcePath("");
    	casedata3.setIp("opengw.uat.zhongan.com");
    	casedata3.setData("channelOrderNo=cgirvtkzpn"+"\n"
+"campaignId=10002156284"+"\n"
+"productPackageId=51264608"+"\n"
+"policyHolderUserName=投保测"+"\n"
+"policyHolderCertiType=I"+"\n"
+"policyHolderCertiNo=440607199103138425"+"\n"
+"policyHolderPhone=13581781486"+"\n"
+"policyHolderBirthDay="+"\n"
+"policyHolderGender="+"\n"
+"relationToPolicyHolder=5"+"\n"
+"insuredUserName=北测试"+"\n"
+"insuredCertiType=I"+"\n"
+"insuredCertiNo=410411199203135369"+"\n"
+"insuredPhone=13581781486"+"\n"
+"insuredBirthDay="+"\n"
+"insuredGender=");
    	casedata3.setApi_type("OpenAPI");
    	
    	LinkedList<CaseDataDTO> listcase = new LinkedList<CaseDataDTO>();
    	LinkedList<CaseDataDTO> listcase2 = new LinkedList<CaseDataDTO>();
    	LinkedList<CaseDataDTO> listcase3 = new LinkedList<CaseDataDTO>();

    	LinkedList<CombineData> listcombine = new LinkedList<CombineData>();
    	listcase.add(casedata2);
    	for(int i=0;i<3;i++){
    		listcase2.add(casedata2);
    	}
    	listcase2.add(casedata2);
    	listcase3.add(casedata3);
    	combindata.setData(listcase); 
    	combindata2.setData(listcase2);
    	combindata3.setData(listcase3);
    	listcombine.add(combindata);
    	listcombine.add(combindata2);
    	listcombine.add(combindata3);
    	//System.out.println(listcombine.toArray());
        HessianProxyFactory factory = new HessianProxyFactory();
        //factory.setServiceUrl("http://localhost:8080/HelloService");
        //factory.setServiceInterface(HelloService.class);
        try {
        	
        	//debug
        	CoreService coreService = (CoreService) factory.create(CoreService.class,
                    "http://10.139.162.247:9090/debugcoreAPIservice");
        	LinkedList<CombineReport> listreport= coreService.execute(listcombine);
        	for(int i=0;i<listreport.size();i++){
        		System.out.println(listreport.get(i).getReport().get(0));
        	}
        	
        	//actual execute
        	/*ExecuteService executeService = (ExecuteService) factory.create(ExecuteService.class,
                    "http://10.139.162.247:9090/executeAPIservice");
        	boolean message= executeService.remoteExecute("8888", listcombine);
        	System.out.println(message);*/
        	//System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
