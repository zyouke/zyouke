package com.zyouke_crawler.utils;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: CrawlerUtils 
 * @Description: 爬虫使用的工具类
 * @author 周俊
 * @date 2017年9月13日 下午4:18:04
 */
public class CrawlerUtils {

    /**
     * @Title: toBoolean 
     * @Description: 布尔类型的工具
     * @author 周俊 
     * @param str
     * @param defaultValue
     * @return boolean 
     * @throws
     */
    public static boolean toBoolean(String str,Boolean defaultValue){
	if(StringUtils.isBlank(str)){
	    if(defaultValue == null){
		return true;
	    }
	    return defaultValue;
	}
	return BooleanUtils.toBoolean(str);
    }
    
}
