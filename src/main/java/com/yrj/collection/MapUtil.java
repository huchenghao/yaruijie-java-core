package com.yrj.collection;

import java.util.Map;

/**
 * 
 * @ClassName: MapUtil
 * @Description: 关于Map的工具集合，
 * @author: huchenghao
 * @date: 2018年7月31日 下午5:42:40
 */
public class MapUtil {

	public static final float DEFAULT_LOAD_FACTOR = 0.75f;

	/**
	 * 
	 * @Title: isEmpty
	 * @Description: 判断是否为空.
	 * @param map
	 * @return
	 * @author huchenghao
	 */
	public static boolean isEmpty(final Map<?, ?> map) {
		return (map == null) || map.isEmpty();
	}

	/**
	 * 
	 * @Title: isNotEmpty
	 * @Description: 判断是否不为空.
	 * @param map
	 * @return
	 * @author huchenghao
	 */
	public static boolean isNotEmpty(final Map<?, ?> map) {
		return (map != null) && !map.isEmpty();
	}
	
}
