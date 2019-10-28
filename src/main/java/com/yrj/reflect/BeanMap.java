package com.yrj.reflect;

import java.lang.reflect.Field;
import java.util.Map;

import com.google.common.collect.Maps;

public class BeanMap {

	/**
	 * 
	 * @Title: bean2Map
	 * @Description: bean to map
	 * @param beanObj
	 * @return
	 * @throws Exception
	 * @author huchenghao
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> bean2Map(Object beanObj) throws Exception {
        if(beanObj == null){
            return null;
        }
        Map<K,V> map = Maps.newLinkedHashMap();
        Field[] declaredFields = beanObj.getClass().getDeclaredFields(); //获取所有的属性
        for (Field field : declaredFields) {
            field.setAccessible(true);//打破封装
            map.put((K)field.getName(), (V)field.get(beanObj));
        }
        return map;
    }
}
