package com.gjc.util;

import org.apache.commons.lang.StringUtils;

public class StringUtil {
	/**
	 * 数据库字段转java对象 
	 * contract_bill ==> ContractBill
	 * @param str
	 * @return
	 */
	public static String replaceUnderLineAndUpperCase(String str){
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        int count = sb.indexOf("_");
        while(count!=0){
            int num = sb.indexOf("_",count);
            count = num + 1;
            if(num != -1){
                char ss = sb.charAt(count);
                char ia;
                if(ss>=97&&ss<=122) {
                	ia = (char) (ss - 32);
                }else {
                	ia = ss;
                }
                sb.replace(count , count + 1,ia + "");
            }
        }
        String result = sb.toString().replaceAll("_","");
        return StringUtils.capitalize(result);
    }
	
	/**
	 * 包名转换成文件格式
	 * @param str
	 * @return
	 */
	public static String getPackage(String str){
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        int count = sb.indexOf(".");
        while(count!=0){
            int num = sb.indexOf(".",count);
            count = num + 1;
            if(num != -1){
                sb.replace(num,count,"/");
            }
        }
        return sb.toString();
    }
	
	/**
	 * 首字母小写
	 * @param str
	 * @return
	 * @throws Exception
	 */
	 public static String firstToLower(String str) throws Exception{
		 return str.substring(0, 1).toLowerCase()+ str.substring(1, str.length());
	 }
	 
	 /**
	  * 首字母大写
	  * @param str
	  * @return
	  * @throws Exception
	  */
	 public static String firstToUpper(String str) throws Exception{
		 return str.substring(0, 1).toUpperCase()+ str.substring(1, str.length());
	 }
	 
}
