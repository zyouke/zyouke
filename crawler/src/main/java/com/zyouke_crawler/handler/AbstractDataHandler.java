package com.zyouke_crawler.handler;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.zyouke_crawler.storage.Storage;

/**
 * 数据处理
 * @ClassName: IDataHandler 
 * @author 周俊
 * @date 2017年9月27日 下午5:52:54
 */
public abstract class AbstractDataHandler<T> {

    protected static final Logger logger = Logger.getLogger(AbstractDataHandler.class.getName());
    protected Storage<T> storage = new Storage<T>();
    
    /**
     * 数据处理
     * @Title: createStorageData
     * @author 周俊
     * @param proToEls 对象属性与页面元素产生的map关系
     * @param storageKey 数据存储key
     * @param depth 深度
     * @param xmlHandler 处理xml文件对象
     */
    public abstract  List<Map<String, String>> createStorageData(Document document, Map<String,Elements> proToEls,String storageKey,String depth,XmlHandler xmlHandler);
    
    /**
     * 为对象赋值
     * @Title: assignment
     * @author 周俊 
     * @param pro
     * @param value
     * @return Area
     */
    public T assignment(String pro, String value, T t) {
	if (StringUtils.isNotBlank(pro) && StringUtils.isNotBlank(value)) {
	    try {
		Class<? extends Object> clazz = t.getClass();
		Field field = clazz.getDeclaredField(pro);
		field.setAccessible(true);
		field.set(t, value);
	    } catch (NoSuchFieldException e) {
		logger.error(e);
	    } catch (SecurityException e) {
		logger.error(e);
	    } catch (IllegalArgumentException e) {
		logger.error(e);
	    } catch (IllegalAccessException e) {
		logger.error(e);
	    }
	}
	return t;
    }
    
}
