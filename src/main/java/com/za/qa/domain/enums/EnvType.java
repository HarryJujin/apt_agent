package com.za.qa.domain.enums; 
/** 
 * @author jujinxin 
 * @version 创建时间：2016年11月29日 下午5:48:02 
 * 类说明 
 */
public enum EnvType {
	uat("uat","预发环境"),
	iTest("iTest","测试环境"),
	none("none","无");

	 private String code;
   private String name;

   private EnvType(String code, String name) {
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
 