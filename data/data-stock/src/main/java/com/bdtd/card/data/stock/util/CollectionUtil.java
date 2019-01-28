package com.bdtd.card.data.stock.util;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
/***
 * 创建所有Java中用到的集合的工具类，再封一层的意义在于对集合的管理，方便查找有可能出现的内在泄漏等问题。
 * @author  Terry
 * @time    2016年5月11日
 */
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class CollectionUtil {

	private CollectionUtil() {

	}

	/**
	 * 
	 * @Description 创建HashMap
	 * @return
	 * @author Terry
	 * @time 2016年5月11日
	 */
	public static <K, V> HashMap<K, V> createHashMap() {
		return new HashMap<K, V>();
	}
	public static <K, V> HashMap<K, V> createHashMap(int initCapacity) {
		return new HashMap<K, V>(initCapacity);
	}
	
	public static <K, V> TreeMap<K, V> createTreeMap() {
		return new TreeMap<K, V>();
	}

	public static <E> TreeSet<E> createTreeSet() {
		return new TreeSet<E>();
	}

	public static <E> HashSet<E> createHashSet() {
		return new HashSet<E>();
	}
	public static <E> HashSet<E> createHashSet(int initCapacity) {
		return new HashSet<E>(initCapacity);
	}

	public static <E> ArrayList<E> createArrayList() {
		return new ArrayList<E>();
	}
	public static <E> ArrayList<E> createArrayList(int initCapacity) {
		return new ArrayList<E>(initCapacity);
	}
	
	public static <E> CopyOnWriteArrayList<E> createCopyOnWriteArrayList(){
		return new CopyOnWriteArrayList<E>();
	}

	public static <K, V> ConcurrentHashMap<K, V> createConcurrentHashMap() {
		return new ConcurrentHashMap<K, V>();
	}
	public static <K, V> ConcurrentHashMap<K, V> createConcurrentHashMap(int initCapacity) {
		return new ConcurrentHashMap<K, V>(initCapacity);
	}
	
	public static <E> ArrayDeque<E> createArrayDeque(){
		return new  ArrayDeque<E>();
	}
	
	public static <E> LinkedBlockingQueue<E> createLinkedBlockingQueue(){
		return new LinkedBlockingQueue<E>();
	}
	public static <E> LinkedBlockingQueue<E> createLinkedBlockingQueue(int initCapacity){
		return new LinkedBlockingQueue<E>(initCapacity);
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] listToArray(List<T> list, Class<T> t) {
		T[] array = (T[]) Array.newInstance(t, list.size());
		list.toArray(array);
		return array;
	}
	
	
	/**
	 * 
	 * 描述:这个方法是将Stringr的list类型转化为Long的list
	 *
	 * @param list
	 * @return
	 * @author			Terry
	 * @time			2016年7月30日-下午2:39:49
	 */
	public static List<Long> listStringToLong(List<String> list){
		if(list != null){
			List<Long> resultList = CollectionUtil.createArrayList(list.size());
			for(String value : list){
				resultList.add(StringUtil.valueOfLong(value));
			}
			return resultList;
		}
		return null;
	}
	/**
	 * 
	 * 描述:这个方法是Stringr的list类型转化为Integer的list
	 *
	 * @param list
	 * @return
	 * @author			Terry
	 * @time			2016年7月30日-下午3:59:27
	 */
	public static List<Integer> listStringToInteger(List<String> list) {
		if(list == null){
			return Collections.emptyList();
		}
		List<Integer> resultList = CollectionUtil.createArrayList(list.size());
		if (list != null) {
			for (String value : list) {
				resultList.add(StringUtil.valueOfInt(value));
			}
		}
		return resultList;
	}
	public static Set<Long> setStringToSetLong(Set<String> sets){
		if(sets == null){
			return Collections.emptySet();
		}
		Set<Long> resultSet = createHashSet(sets.size());
		for(String value : sets){
			resultSet.add(StringUtil.valueOfLong(value));
		}
		return resultSet;
	}
	/**
	 * 
	 * 描述:这个方法是将set的String集合转化为long的list
	 *
	 * @param sets
	 * @return
	 * @author			Terry
	 * @time			2016年8月22日-下午5:46:35
	 */
	public static List<Long> setStringToListLong(Set<String> sets){
		
		if(sets == null){
			return Collections.emptyList();
		}
		List<Long> list = createArrayList(sets.size());
		for(String str : sets){
			list.add(StringUtil.valueOfLong(str));
		}
		return list;
		
	}
	
	
}
