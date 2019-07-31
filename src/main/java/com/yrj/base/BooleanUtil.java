package com.yrj.base;
import org.apache.commons.lang3.BooleanUtils;
/**
 * 
 * @ClassName: BooleanUtil
 * @Description: 
 * 				1. 从String(true/false, yes/no)，转换为Boolean或boolean
 * 				2. 逻辑运算：取反，多个boolean的and,or 计算
 * @author: huchenghao
 * @date: 2018年7月10日 下午6:39:27
 */
public class BooleanUtil {
	/**
	 * @Title: toBoolean
	 * @Description: str="true"-->true;<br/>
	 * 				 str="TRUE"-->true;<br/>
	 * 				 str=""-->false;<br/>
	 * 				 str="其它字符串"-->false<br/>
	 * @param str
	 * @return boolean
	 * @author huchenghao
	 */
	public static boolean toBoolean(String str) {
		return Boolean.parseBoolean(str);
	}
	/**
	 * @Title: toBooleanObject
	 * @Description: str="true"-->true;<br/>
	 * 				 str="TRUE"-->true;<br/>
	 * 				 str=""-->false;<br/>
	 * 				 str="其它字符串"-->false<br/>
	 * 				 str=null-->null<br/>
	 * @param str
	 * @return Boolean
	 * @author huchenghao
	 */
	public static Boolean toBooleanObject(String str) {
		return str != null ? Boolean.valueOf(str) : null;
	}

	/**
	 * 
	 * @Title: toBooleanObject
	 * @Description: 只分析是否忽略大小写的"true"<br/>
	 * 				 str为空时返回defaultValue
	 * @param str
	 * @param defaultValue
	 * @return Boolean
	 * @author huchenghao
	 */
	public static Boolean toBooleanObject(String str, Boolean defaultValue) {
		return str != null ? Boolean.valueOf(str) : defaultValue;
	}

	/**
	 * 
	 * @Title: parseGeneralString
	 * @Description: 支持true/false, on/off, y/n, yes/no的转换<br/>
	 * 				 <b>str为空或无法分析时返回null</b>
	 * @param str
	 * @return
	 * @author huchenghao
	 */
	public static Boolean parseGeneralString(String str) {
		return BooleanUtils.toBooleanObject(str);
	}

	/**
	 * 
	 * @Title: parseGeneralString
	 * @Description: 支持true/false,on/off, y/n, yes/no的转换<br/>
	 * 				 <b>str为空或无法分析时返回defaultValue</b>
	 * @param str
	 * @param defaultValue
	 * @return
	 * @author huchenghao
	 */
	public static Boolean parseGeneralString(String str, Boolean defaultValue) {
		return BooleanUtils.toBooleanDefaultIfNull(BooleanUtils.toBooleanObject(str), defaultValue);
	}

	
	/**
	 * 
	 * @Title: negate
	 * @Description: 取反
	 * @param bool
	 * @return
	 * @author huchenghao
	 */
	public static boolean negate(final boolean bool) {
		return !bool;
	}
	/**
	 * 
	 * @Title: negate
	 * @Description: 取反
	 * @param bool
	 * @return
	 * @author huchenghao
	 */
	public static Boolean negate(final Boolean bool) {
		return BooleanUtils.negate(bool);
	}

	/**
	 * 
	 * @Title: and
	 * @Description: 多个值的and<br/>
	 * 				 true,true-->true<br/>
	 *  			 false,true-->false<br/>
	 *  			 false,false-->false<br/>
	 * @param array
	 * @return
	 * @author huchenghao
	 */
	public static boolean and(final boolean... array) {
		return BooleanUtils.and(array);
	}

	/**
	 * 
	 * @Title: or
	 * @Description: 多个值的or<br/>
	 * 				 true,true-->true<br/>
	 *  			 false,true-->true<br/>
	 *  			 false,false-->false<br/>
	 * @param array
	 * @return
	 * @author huchenghao
	 */
	public static boolean or(final boolean... array) {
		return BooleanUtils.or(array);
	}
	
	
}
