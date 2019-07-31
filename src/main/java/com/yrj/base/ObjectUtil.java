package com.yrj.base;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Objects;
import com.yrj.annotation.Nullable;
/**
 * @ClassName: ObjectUtil
 * @Description:
 * @author: huchenghao
 * @date: 2018年7月11日 下午3:03:22
 */
public class ObjectUtil {
	
	private static final String NULL = "null";
	/**
	 * @Title: equals
	 * @Description: 判断两个对象是否相等
	 * @param a
	 * @param b
	 * @return
	 * @author huchenghao
	 */
	public static boolean equals(@Nullable Object a, @Nullable Object b) {
		return Objects.equal(a, b);
	}
	/**
	 * 
	 * @Title: beanToString
	 * @Description: 对象转字符串
	 * @param value
	 * @return
	 * @author huchenghao
	 * @date: 2019年2月20日 上午9:41:11
	 */
	public static <T> String beanToString(T value) {
		if(value == null){
			return null;
		}
		Class<?> clazz = value.getClass();
		if(clazz == int.class || clazz == Integer.class){
			return ""+value;
		}else if(clazz == String.class){
			return (String)value;
		}else if(clazz == long.class || clazz == Long.class){
			return ""+value;
		}else{
			return JSON.toJSONString(value);
		}
	}
	/**
	 * 
	 * @Title: strToBean
	 * @Description: 字符串转bean对象
	 * @param str
	 * @param clazz
	 * @return
	 * @author huchenghao
	 * @date: 2019年2月20日 上午9:41:34
	 */
	@SuppressWarnings("unchecked")
	public static <T> T strToBean(String str,Class<T> clazz) {
		if(str == null || str.length() <=0 || clazz == null){
			return null;
		}
		if(clazz == int.class || clazz == Integer.class){
			return (T) Integer.valueOf(str);
		}else if(clazz == String.class){
			return (T) str;
		}else if(clazz == long.class || clazz == Long.class){
			return (T) Long.valueOf(str);
		}else{
			JSONObject job = JSON.parseObject(str);
			return JSON.toJavaObject(job, clazz);
		}
	}

}
