package com.crawler.http;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: HttpUtils 
 * @Description: http请求的工具类
 * @author 周俊
 * @date 2017年8月21日 上午10:54:38 
 *
 */
public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    
    /**
     * 
     * @Title: doGet 
     * @Description: get请求返回请求获得页面,会存在乱码
     * @author 周俊 
     * @param url
     * @return Document 
     * @throws
     */
    public static Document doGet(String url) {
	try {
	    Connection connection = HttpConnection.connect(url);
	    Response response = connection.execute();
	    Document document = response.parse();
	    return document;
	} catch (IOException e) {
	    logger.error(e.getMessage(),e);
	}
	return null;
    }
    
    /**
     * 
     * @Title: doGet 
     * @Description: get请求返回请求获得页面,根据页面编码指定编码
     * @author 周俊 
     * @param url 地址
     * @param charsetName 编码
     * @return Document 
     * @throws
     */
    public static Document doGet(String url, String charsetName) {
	if (StringUtils.isBlank(charsetName)) {
	    charsetName = "UTF-8";
	}
	try {
	    Document document = Jsoup.parse(new URL(url).openStream(),charsetName, url);
	    return document;
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	return null;
    }
}
