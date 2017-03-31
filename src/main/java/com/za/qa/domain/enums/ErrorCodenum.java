package com.za.qa.domain.enums; 
/** 
 * @author jujinxin 
 * @version 创建时间：2017年2月8日 下午1:46:33 
 * 类说明 
 */
public enum ErrorCodenum {

	

	    success("000","成功"), 
	    respone_is_empty("001", "返回报文为空,可能原因1.接口被释放2.ip,url,service错误"),	    
	    analyze_fail("002", "关键字解析失败"),
	    env_is_empty("003","测试环境为空"),
	    ip_is_empty("004","ip为空"),
	    serviceName_is_empty("005","接口服务为空"),
	    data_is_empty("006","请求报文为空");
	  

	    private String code;
	    private String name;

	    private ErrorCodenum(String code, String name) {
	        this.setCode(code);
	        this.setName(name);
	    }

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }



}
 