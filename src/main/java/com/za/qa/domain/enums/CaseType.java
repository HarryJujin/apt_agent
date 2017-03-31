package com.za.qa.domain.enums; 
/** 
 * @author jujinxin 
 * @version 创建时间：2016年11月17日 上午10:00:04 
 * 类说明:接口类型描述
 */
public enum CaseType {
	OpenAPI("OpenAPI","开发平台"),
	HSF("HSF","HSF接口"),
	HTTP("HTTP","Http/Https接口"),
	SOAP("SOAP","Web_Service接口");

	 private String code;
    private String name;

    private CaseType(String code, String name) {
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
 