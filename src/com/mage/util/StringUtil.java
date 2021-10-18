package com.mage.util;

/**
 * 字符串非空判断工具类
 * @author Cushier
 *
 */
public class StringUtil {

	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return false;
		}
		return true;
	}
	
}