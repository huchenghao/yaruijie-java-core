package com.yrj.collection;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.collect.Lists;

/**
 * 关于List的工具集合.
 * 
 * 1. 常用函数(如是否为空，sort/binarySearch/shuffle/reverse(via JDK Collection)
 * 
 * 2. 各种构造函数(from guava and JDK Collection)
 * 
 * 3. 各种扩展List类型的创建函数
 * 
 * 5. 集合运算：交集，并集, 差集, 补集，from Commons Collection，但对其不合理的地方做了修正
 */
@SuppressWarnings("unchecked")
public class ListUtil {

	/**
	 * 
	 * @Title: isEmpty
	 * @Description: 判断是否为空.
	 * @param list
	 * @return
	 * @author huchenghao
	 */
	public static boolean isEmpty(List<?> list) {
		return (list == null) || list.isEmpty();
	}

	/**
	 * 
	 * @Title: isNotEmpty
	 * @Description: 判断List不为空
	 * @param list
	 * @return
	 * @author huchenghao
	 */
	public static boolean isNotEmpty(List<?> list) {
		return (list != null) && !(list.isEmpty());
	}

	/**
	 * 
	 * @Title: getFirst
	 * @Description: 获取第一个元素, 如果List为空返回 null.
	 * @param list
	 * @return
	 * @author huchenghao
	 */
	public static <T> T getFirst(List<T> list) {
		if (isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * 
	 * @Title: getLast
	 * @Description: 获取最后一个元素，如果List为空返回null.
	 * @param list
	 * @return
	 * @author huchenghao
	 */
	public static <T> T getLast(List<T> list) {
		if (isEmpty(list)) {
			return null;
		}

		return list.get(list.size() - 1);
	}

	///////////////// from Guava的构造函数///////////////////
	/**
	 * 
	 * @Title: newArrayList
	 * @Description: 根据等号左边的类型，构造类型正确的ArrayList.
	 * @see com.google.common.collect.Lists#newArrayList()
	 * @return
	 * @author huchenghao
	 */
	public static <T> ArrayList<T> newArrayList() {
		return new ArrayList<T>();
	}
	/**
	 * 
	 * @Title: newArrayList
	 * @Description: 根据等号左边的类型，构造类型正确的ArrayList, 并初始化元素.
	 * @see com.google.common.collect.Lists#newArrayList(Object...)
	 * @param elements
	 * @return
	 * @author huchenghao
	 */
	public static <T> ArrayList<T> newArrayList(T... elements) {
		return Lists.newArrayList(elements);
	}

	/**
	 * 
	 * @Title: newArrayList
	 * @Description: 根据等号左边的类型，构造类型正确的ArrayList, 并初始化元素.
	 * @see com.google.common.collect.Lists#newArrayList(Iterable)
	 * @param elements
	 * @return
	 * @author huchenghao
	 */
	public static <T> ArrayList<T> newArrayList(Iterable<T> elements) {
		return Lists.newArrayList(elements);
	}

	
	/**
	 * 
	 * @Title: newArrayListWithCapacity
	 * @Description: 根据等号左边的类型，构造类型正确的ArrayList, 并初始化数组大小.
	 * @see com.google.common.collect.Lists#newArrayListWithCapacity(int)
	 * @param initSize
	 * @return
	 * @author huchenghao
	 */
	public static <T> ArrayList<T> newArrayListWithCapacity(int initSize) {
		return new ArrayList<T>(initSize);
	}

	
	/**
	 * 
	 * @Title: newLinkedList
	 * @Description: 根据等号左边的类型，构造类型正确的LinkedList.
	 * @see com.google.common.collect.Lists#newLinkedList()
	 * @return
	 * @author huchenghao
	 */
	public static <T> LinkedList<T> newLinkedList() {
		return new LinkedList<T>();
	}

	
	/**
	 * 
	 * @Title: newLinkedList
	 * @Description: 根据等号左边的类型，构造类型正确的LinkedList.
	 * @see com.google.common.collect.Lists#newLinkedList()
	 * @param elements
	 * @return
	 * @author huchenghao
	 */
	public static <T> LinkedList<T> newLinkedList(Iterable<? extends T> elements) {
		return Lists.newLinkedList(elements);
	}

	/**
	 * 
	 * @Title: newCopyOnWriteArrayList
	 * @Description: 根据等号左边的类型，构造类型正确的CopyOnWriteArrayList.
	 * @see com.google.common.collect.Lists#newCopyOnWriteArrayList()
	 * @return
	 * @author huchenghao
	 */
	public static <T> CopyOnWriteArrayList<T> newCopyOnWriteArrayList() {
		return new CopyOnWriteArrayList<T>();
	}

	
	/**
	 * 
	 * @Title: newCopyOnWriteArrayList
	 * @Description: 根据等号左边的类型，构造类型转换的CopyOnWriteArrayList, 并初始化元素.
	 * @param elements
	 * @return
	 * @author huchenghao
	 */
	public static <T> CopyOnWriteArrayList<T> newCopyOnWriteArrayList(T... elements) {
		return new CopyOnWriteArrayList<T>(elements);
	}

	///////////////// from JDK Collections的常用构造函数 ///////////////////


	/**
	 * 
	 * @Title: emptyList
	 * @Description: 返回一个空的结构特殊的List，节约空间.
	 * 				   注意返回的List不可写, 写入会抛出UnsupportedOperationException.
	 * @see java.util.Collections#emptyList()
	 * @return
	 * @author huchenghao
	 */
	public static final <T> List<T> emptyList() {
		return Collections.emptyList();
	}

	
	/**
	 * 
	 * @Title: emptyListIfNull
	 * @Description: 如果list为null，转化为一个安全的空List.
	 * 				 注意返回的List不可写, 写入会抛出UnsupportedOperationException.
	 * @see java.util.Collections#emptyList()
	 * @param list
	 * @return
	 * @author huchenghao
	 */
	public static <T> List<T> emptyListIfNull(final List<T> list) {
		return list == null ? (List<T>) Collections.EMPTY_LIST : list;
	}

	
	/**
	 * 
	 * @Title: singletonList
	 * @Description: 返回只含一个元素但结构特殊的List，节约空间.
	 * 				 注意返回的List不可写, 写入会抛出UnsupportedOperationException.
	 * @see java.util.Collections#singletonList(Object)
	 * @param o
	 * @return
	 * @author huchenghao
	 */
	public static <T> List<T> singletonList(T o) {
		return Collections.singletonList(o);
	}

	/**
	 * 
	 * @Title: unmodifiableList
	 * @Description: 返回包装后不可修改的List.
	 * 				 如果尝试写入会抛出UnsupportedOperationException.
	 * @see java.util.Collections#unmodifiableList(List)
	 * @param list
	 * @return
	 * @author huchenghao
	 */
	public static <T> List<T> unmodifiableList(List<? extends T> list) {
		return Collections.unmodifiableList(list);
	}

	
	/**
	 * 
	 * @Title: synchronizedList
	 * @Description: 返回包装后同步的List，所有方法都会被synchronized原语同步.
	 * 				 用于CopyOnWriteArrayList与 ArrayDequeue均不符合的场景
	 * @param list
	 * @return
	 * @author huchenghao
	 */
	public static <T> List<T> synchronizedList(List<T> list) {
		return Collections.synchronizedList(list);
	}

	///////////////// from JDK Collections的常用函数 ///////////////////

	
	/**
	 * 
	 * @Title: sort
	 * @Description: 升序排序, 采用JDK认为最优的排序算法, 使用元素自身的compareTo()方法
	 * @see java.util.Collections#sort(List)
	 * @param list
	 * @author huchenghao
	 */
	public static <T extends Comparable<? super T>> void sort(List<T> list) {
		Collections.sort(list);
	}

	
	/**
	 * 
	 * @Title: sortReverse
	 * @Description: 倒序排序, 采用JDK认为最优的排序算法,使用元素自身的compareTo()方法
	 * @see java.util.Collections#sort(List)
	 * @param list
	 * @author huchenghao
	 */
	public static <T extends Comparable<? super T>> void sortReverse(List<T> list) {
		Collections.sort(list, Collections.reverseOrder());
	}

	
	/**
	 * 
	 * @Title: sort
	 * @Description: 升序排序, 采用JDK认为最优的排序算法, 使用Comparetor.
	 * @see java.util.Collections#sort(List, Comparator)
	 * @param list
	 * @param c
	 * @author huchenghao
	 */
	public static <T> void sort(List<T> list, Comparator<? super T> c) {
		Collections.sort(list, c);
	}

	
	/**
	 * 
	 * @Title: sortReverse
	 * @Description: 倒序排序, 采用JDK认为最优的排序算法, 使用Comparator
	 * @see java.util.Collections#sort(List, Comparator)
	 * @param list
	 * @param c
	 * @author huchenghao
	 */
	public static <T> void sortReverse(List<T> list, Comparator<? super T> c) {
		Collections.sort(list, Collections.reverseOrder(c));
	}
	
	/**
	 * 
	 * @Title: shuffle
	 * @Description: 随机乱序，使用默认的Random.
	 * @see java.util.Collections#shuffle(List)
	 * @param list
	 * @author huchenghao
	 */
	public static void shuffle(List<?> list) {
		Collections.shuffle(list);
	}

	
	/**
	 * 
	 * @Title: reverse
	 * @Description: 返回一个倒转顺序访问的List，仅仅是一个倒序的View，不会实际多生成一个List
	 * @see com.google.common.collect.Lists#reverse(List)
	 * @param list
	 * @return
	 * @author huchenghao
	 */
	public static <T> List<T> reverse(final List<T> list) {
		return Lists.reverse(list);
	}

	
	/**
	 * 
	 * @Title: shuffle
	 * @Description: 随机乱序，使用传入的Random.
	 * @see java.util.Collections#shuffle(List, Random)
	 * @param list
	 * @param rnd
	 * @author huchenghao
	 */
	public static void shuffle(List<?> list, Random rnd) {
		Collections.shuffle(list, rnd);
	}

	///////////////// 集合运算 ///////////////////

	/**
	 * 
	 * @Title: union
	 * @Description: list1,list2的并集（在list1或list2中的对象），产生新List
	 * 				 对比Apache Common Collection4 ListUtils, 优化了初始大小
	 * @param list1
	 * @param list2
	 * @return
	 * @author huchenghao
	 */
	public static <E> List<E> union(final List<? extends E> list1, final List<? extends E> list2) {
		final List<E> result = new ArrayList<E>(list1.size() + list2.size());
		result.addAll(list1);
		result.addAll(list2);
		return result;
	}

	
	/**
	 * 
	 * @Title: intersection
	 * @Description: list1, list2的交集（同时在list1和list2的对象），产生新List
	 * 				 copy from Apache Common Collection4 ListUtils，但其做了不合理的去重，因此重新改为性能较低但不去重的版本
	 * 				 与List.retainAll()相比，考虑了的List中相同元素出现的次数, 如"a"在list1出现两次，而在list2中只出现一次，则交集里会保留一个"a".
	 * @param list1
	 * @param list2
	 * @return
	 * @author huchenghao
	 */
	public static <T> List<T> intersection(final List<? extends T> list1, final List<? extends T> list2) {
		List<? extends T> smaller = list1;
		List<? extends T> larger = list2;
		if (list1.size() > list2.size()) {
			smaller = list2;
			larger = list1;
		}

		// 克隆一个可修改的副本
		List<T> newSmaller = new ArrayList<T>(smaller);
		List<T> result = new ArrayList<T>(smaller.size());
		for (final T e : larger) {
			if (newSmaller.contains(e)) {
				result.add(e);
				newSmaller.remove(e);
			}
		}
		return result;
	}

	
	/**
	 * 
	 * @Title: difference
	 * @Description: list1, list2的差集（在list1，不在list2中的对象），产生新List.
	 * 				 与List.removeAll()相比，会计算元素出现的次数，如"a"在list1出现两次，而在list2中只出现一次，则差集里会保留一个"a".
	 * @param list1
	 * @param list2
	 * @return
	 * @author huchenghao
	 */
	public static <T> List<T> difference(final List<? extends T> list1, final List<? extends T> list2) {
		final List<T> result = new ArrayList<T>(list1);
		final Iterator<? extends T> iterator = list2.iterator();

		while (iterator.hasNext()) {
			result.remove(iterator.next());
		}

		return result;
	}

	
	/**
	 * 
	 * @Title: disjoint
	 * @Description: list1, list2的补集（在list1或list2中，但不在交集中的对象，又叫反交集）产生新List.
	 * 				 copy from Apache Common Collection4 ListUtils，但其并集－交集时，初始大小没有对交集*2，所以做了修改
	 * @param list1
	 * @param list2
	 * @return
	 * @author huchenghao
	 */
	public static <T> List<T> disjoint(final List<? extends T> list1, final List<? extends T> list2) {
		List<T> intersection = intersection(list1, list2);
		List<T> towIntersection = union(intersection, intersection);
		return difference(union(list1, list2), towIntersection);
	}
}
