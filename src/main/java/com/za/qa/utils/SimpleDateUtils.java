package com.za.qa.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SimpleDateUtils {

    public static String getCurrentDateTime(String dateFormat) {
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        return sf.format(new Date());
    }
    
    /**  
     * 将String型格式化,比如想要将2011-11-11格式化成2011年11月11日,就StringPattern("2011-11-11","yyyy-MM-dd","yyyy年MM月dd日"). 
     * @param date String 想要格式化的日期 
     * @param oldPattern String 想要格式化的日期的现有格式 
     * @param newPattern String 想要格式化成什么格式 
     * @return String  
     */   
    public static String StringPattern(String date,int age) {   
    	GregorianCalendar gc =new GregorianCalendar();
    	SimpleDateFormat sdf1=null;
        if (date == null)   
            return ""; 
        if(date.matches("[0-9]*")&&date.length()==8){
        	sdf1 = new SimpleDateFormat("yyyyMMdd");
        }else if(date.matches("[0-9\\s]*")&&date.length()==10){
        	sdf1 = new SimpleDateFormat("yyyy MM dd");
        }else if(date.matches("[0-9-]*")&&date.length()==10){
        	sdf1 = new SimpleDateFormat("yyyy-MM-dd") ;
        }else if(date.matches("[0-9.]*")&&date.length()==10){
        	sdf1 = new SimpleDateFormat("yyyy.MM.dd") ;
        }else if(date.matches("[0-9//]*")&&date.length()==10){
        	sdf1 = new SimpleDateFormat("yyyy/MM/dd") ;
        }else if (date.matches("[0-9\\\\]*")&&date.length()==10){
        	sdf1 = new SimpleDateFormat("yyyy\\MM\\dd") ;
        }else if(date.matches("[0-9\u4e00-\u9fa5]*")){
        	sdf1 = new SimpleDateFormat("yyyy年MM月dd日") ;
        }else if(date.matches("[0-9_]*")&&date.length()==10){
        	sdf1 = new SimpleDateFormat("yyyy_MM_dd") ;
        }else{
        	return ""; 
        }
        //SimpleDateFormat sdf1 = new SimpleDateFormat(oldPattern) ;        // 实例化模板对象    
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd") ;        // 实例化模板对象    
        Date d = null ;    
        try{    
            d = sdf1.parse(date) ;   // 将给定的字符串中的日期提取出来    
        }catch(Exception e){            // 如果提供的字符串格式有错误，则进行异常处理    
            e.printStackTrace() ;       // 打印异常信息    
        }    
        gc.setTime(d);
        gc.add(1,age);
        gc.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DATE));
        return sdf2.format(gc.getTime());  
    } 
}
