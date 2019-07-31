package com.yrj.collection;


import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Ordering;

/**
 * 
 * @ClassName: CollectionUtil
 * @Description: 通用Collection的工具集
 * 				 1. 集合是否为空，取得集合中首个及最后一个元素，判断集合是否完全相等
 * 				 2. 集合的最大最小值，及Top N, Bottom N
 * 				  关于List, Map, Queue, Set的特殊工具集，另见特定的Util.
 * 				  另JDK中缺少ComparableComparator和NullComparator，直到JDK8才补上。
 * 				  因此平时请使用guava的Ordering.natural(),fluentable的API更好用，可以链式设置nullFirst，nullLast,reverse
 * @author: huchenghao
 * @date: 2018年7月31日 下午4:29:23
 */
public class CollectionUtil {

	/**
	 * 
	 * @Title: isEmpty
	 * @Description: 判断集合是否为空.
	 * @param collection
	 * @return boolean
	 * @author huchenghao
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return (collection == null) || collection.isEmpty();
	}
	/**
	 * 
	 * @Title: isNotEmpty
	 * @Description: 判断集合不为空
	 * @param collection
	 * @return boolean
	 * @author huchenghao
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		return (collection != null) && !(collection.isEmpty());
	}

	/**
	 * 
	 * @Title: getFirst
	 * @Description: 取得Collection的第一个元素，如果collection为空返回null
	 * @param collection
	 * @return
	 * @author huchenghao
	 */
	public static <T> T getFirst(Collection<T> collection) {
		if (isEmpty(collection)) {
			return null;
		}
		if (collection instanceof List) {
			return ((List<T>) collection).get(0);
		}
		return collection.iterator().next();
	}

	/**
	 * 
	 * @Title: getLast
	 * @Description: 获取Collection的最后一个元素，如果collection为空返回null
	 * @param collection
	 * @return
	 * @author huchenghao
	 */
	public static <T> T getLast(Collection<T> collection) {
		if (isEmpty(collection)) {
			return null;
		}
		// 当类型List时，直接取得最后一个元素.
		if (collection instanceof List) {
			List<T> list = (List<T>) collection;
			return list.get(list.size() - 1);
		}

		return Iterators.getLast(collection.iterator());
	}

	/**
	 * 
	 * @Title: elementsEqual
	 * @Description: 两个集合中的所有元素按顺序相等.
	 * @param iterable1
	 * @param iterable2
	 * @return boolean
	 * @author huchenghao
	 */
	public static boolean elementsEqual(Iterable<?> iterable1, Iterable<?> iterable2) {
		return Iterables.elementsEqual(iterable1, iterable2);
	}
	/**
	 * 
	 * @Title: min
	 * @Description: 返回无序集合中的最小值，使用元素默认排序
	 * @param coll
	 * @return 
	 * @author huchenghao
	 */
	public static <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll) {
		return Collections.min(coll);
	}

	
	/**
	 * @Title: min
	 * @Description: 返回无序集合中的最小值(自定义排序规则)
	 * <p> class MyComparator implements Comparator<User>{</p>
	   <p>       public int compare(User o1, User o2) {</p>
	   <p>			Integer age1 = o1.getAge();</p>
	   <p>			Integer age2 = o2.getAge();</p>
	   <p>			return age1.compareTo(age2);</p>
	   <p>		}</p>
	   <p>	}</p>
	 * 
	 * @param coll
	 * @param comp 为 new MyComparator()
	 * @return
	 * @author huchenghao
	 */
	public static <T> T min(Collection<? extends T> coll, Comparator<? super T> comp) {
		return Collections.min(coll, comp);
	}

	/**
	 * 
	 * @Title: max
	 * @Description: 返回无序集合中的最大值，使用元素默认排序
	 * @param coll
	 * @return
	 * @author huchenghao
	 */
	public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) {
		return Collections.max(coll);
	}

	/**
	 * 
	 * @Title: max
	 * @Description: 返回无序集合中的最大值(自定义排序规则)
	 * @param coll
	 * @param comp
	 * @return
	 * @author huchenghao
	 */
	public static <T> T max(Collection<? extends T> coll, Comparator<? super T> comp) {
		return Collections.max(coll, comp);
	}

	/**
	 * 
	 * @Title: topN
	 * @Description: 返回Iterable中最大的N个对象, back by guava.
	 * @param coll
	 * @param n
	 * @return
	 * @author huchenghao
	 */
	public static <T extends Comparable<?>> List<T> topN(Iterable<T> coll, int n) {
		return Ordering.natural().greatestOf(coll, n);
	}

	/**
	 * 返回Iterable中最大的N个对象, back by guava.
	 */
	public static <T> List<T> topN(Iterable<T> coll, int n, Comparator<? super T> comp) {
		return Ordering.from(comp).greatestOf(coll, n);
	}

	/**
	 * 返回Iterable中最小的N个对象, back by guava.
	 */
	public static <T extends Comparable<?>> List<T> bottomN(Iterable<T> coll, int n) {
		return Ordering.natural().leastOf(coll, n);
	}

	/**
	 * 返回Iterable中最小的N个对象, back by guava.
	 */
	public static <T> List<T> bottomN(Iterable<T> coll, int n, Comparator<? super T> comp) {
		return Ordering.from(comp).leastOf(coll, n);
	}

}
