package com.zyouke_crawler.storage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * @ClassName: Storage
 * @Description: 使用Hashtable存储数据,不可以持久化,只可用于打印
 * @author 周俊
 * @date 2017年8月22日 下午5:43:07
 */
public class Storage<T> {

    private Hashtable<String, String> table =  new Hashtable<String, String>();;

    private static final Logger logger = Logger.getLogger(Storage.class.getName());
    
    /**
     * @Title: put
     * @Description: 添加值,key为uuid生存的字符串,并返回key
     * @author 周俊
     * @param value
     * @return String
     * @throws
     */
    public  String put(String value) {
	List<String> valueList = new ArrayList<String>();
	valueList.add(value);
	String key = UUID.randomUUID().toString().replace("-", "");
	table.put(key, JSON.toJSONString(valueList));
	return key;
    }

    /**
     * @Title: put
     * @Description: 添加值,key为uuid生存的字符串,并返回key
     * @author 周俊
     * @param value
     * @return String
     * @throws
     */
    public void put(String key,String value) {
	table.put(key,value);
    }
    
    /**
     * @Title: get
     * @Description: 获取值
     * @author 周俊
     * @param key
     * @return String
     * @throws
     */
    public  String getValue(String key) {
	return table.get(key);
    }
    
    /**
     * @Title: get
     * @Description: 获取值
     * @author 周俊
     * @param key
     * @return String
     * @throws
     */
    public  String get(String key) {
	return table.get(key).replace("[", "").replace("]", "").trim();
    }

    /**
     * @Title: remove
     * @Description: 获取值
     * @author 周俊
     * @param key
     * @return String
     * @throws
     */
    public  void remove(String key) {
	if (StringUtils.isNotBlank(key)) {
	    table.remove(key);
	}
    }

    /**
     * 存储
     * @Title: storage
     * @author 周俊 
     * @param map
     * @param depth
     * @return List<Map<String,String>>
     */
    public  List<Map<String, String>> storage(Map<String,T> map,boolean isStorage,String depth){
	int index = Integer.valueOf(depth);
	index = index + 1;
	depth = String.valueOf(index);
	List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
	for (Entry<String, T> entry : map.entrySet()) {
	    String key = entry.getKey();
	    T t = entry.getValue();
	    put(key, JSON.toJSONString(t));
	    Class<? extends Object> clazz = t.getClass();
	    String url = "";
	    try {
		Field field = clazz.getDeclaredField("url");
		field.setAccessible(true);
		url = (String) field.get(t);
	    } catch (NoSuchFieldException e) {
		logger.error(e);
	    } catch (SecurityException e) {
		logger.error(e);
	    } catch (IllegalArgumentException e) {
		logger.error(e);
	    } catch (IllegalAccessException e) {
		logger.error(e);
	    }
	    if (StringUtils.isNotBlank(url) && isStorage) {
		Map<String, String> crawlerQueueMap = new HashMap<String, String>();
		crawlerQueueMap.put("LEVEL", depth);
		crawlerQueueMap.put("URL", url);
		crawlerQueueMap.put("STORAGE_KEY", key);
		resultList.add(crawlerQueueMap);
	    }
	    if (!isStorage) {
		//System.out.println("HashtableStorage数据:" + get(key));
	    }
	    
	}
	return resultList;
    }
}
