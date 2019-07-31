package com.yrj.res;

/**
 * 
 * @ClassName: MessageCode
 * @Description: 返回code 表述
 * @author: huchenghao
 * @date: 2018年7月31日 下午3:21:55
 */
public class MessageCode {
	/**
	 * 成功 code
	 * */
	public static  final String SUCCESS ="0";
	
	/**
	 * 失败(主要针对代码异常) code
	 * */
	public static  final String ERROR = "1";
	/**
	 * 提示(主要针对客户端提示)
	 */
	public static  final String TIPS = "2";
	/**
	 * 签名认证失败
	 */
	public static final String UNAUTHORIZED="3";
	
}
