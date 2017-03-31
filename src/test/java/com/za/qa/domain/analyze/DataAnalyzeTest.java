package com.za.qa.domain.analyze; 

import static org.junit.Assert.*;

import org.junit.Test;

import com.za.qa.hessianbean.CaseDataDTO;


/** 
 * @author jujinxin 
 * @version 创建时间：2017年2月22日 下午3:52:43 
 * 类说明 
 */
public class DataAnalyzeTest {

	@Test
	public void testReplaceParameter() {
		String str1 = "$ParaReplace(\"15900986780\",\"phone\")";
		String str2 = "ParaReplace";
		CaseDataDTO casedataDTO =new CaseDataDTO();
		casedataDTO.setAnalyze_data("phone=@Num()"+"\n"
+"CertiNo=@RIdWithBirthday(RDate(\"yyyyMMdd\",\"-20/0/0\"),\"2\")"+"\n"
    			+"effectiveDate=");
		String final_name=DataAnalyze.replaceParameter(str1, str2, casedataDTO);
		System.out.println(final_name);
	}

}
 