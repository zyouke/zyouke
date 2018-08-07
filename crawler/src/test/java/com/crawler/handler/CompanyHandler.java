package com.crawler.handler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.crawler.bean.Company;

/**
 * @ClassName: DataHandler
 * @Description: 处理数据
 * @author 周俊
 * @date 2017年9月15日 上午9:35:51
 */
public class CompanyHandler extends AbstractDataHandler<Company> {

    /**
     * 生成Hashtable存储方式的数据
     * 
     * @Title: createStorageData
     * @author 周俊
     * @param proToEls
     *            对象属性与页面元素产生的map关系
     * @param storageKey
     *            数据存储key
     */
    public List<Map<String, String>> createStorageData(Document document, Map<String, Elements> proToEls, String storageKey, String depth, XmlHandler xmlHandler) {
	Map<String, Company> resultMap = new HashMap<String, Company>();
	List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
	String objJson = null;
	if (StringUtils.isNotBlank(storageKey)) {
	    objJson = super.storage.getValue(storageKey);
	}
	// 判断是否存在分页如果存在分页将分页连接放到队列中
	String lastAttValue = xmlHandler.getAttributeValue(depth, "lastPageCssSelector");
	String nextAttValue = xmlHandler.getAttributeValue(depth, "nextPageCssSelector");
	if (StringUtils.isNotBlank(lastAttValue) && StringUtils.isNotBlank(nextAttValue)) {
	    Elements lastElements = document.select(lastAttValue);
	    Elements nextElements = document.select(nextAttValue);
	    if (lastElements.size() > 0 && nextElements.size() > 0) {
		Element lastE = lastElements.get(0);
		Element nextE = nextElements.get(0);
		String lastHref = lastE.attr("href");
		String nextHref = nextE.attr("href");
		if (!StringUtils.equals(lastHref, nextHref)) {
		    Company company = new Company();
		    if (StringUtils.isNotBlank(objJson)) {
			Company sourceCompany = JSON.parseObject(objJson, Company.class);
			BeanUtils.copyProperties(sourceCompany, company);
		    }
		    String href = null;
		    try {
			href = new String(new URLCodec().decode(nextE.attr("abs:href").getBytes()));
		    } catch (DecoderException e) {
			e.printStackTrace();
		    }
		    super.assignment("url", href, company);
		    resultMap.put(UUID.randomUUID().toString().replace("-", ""), company);
		    List<Map<String, String>> list = super.storage.storage(resultMap, xmlHandler.hasNextNode(String.valueOf(Integer.valueOf(depth) - 1)), String.valueOf(Integer.valueOf(depth) - 1));
		    resultList.addAll(list);
		}
	    }
	}
	List<Company> companys = new ArrayList<Company>();
	int maxSize = 0;
	String key = "";
	Elements values = null;
	for (Entry<String, Elements> entry : proToEls.entrySet()) {
	    Elements valueTemp = entry.getValue();
	    if (valueTemp.size() > maxSize) {
		maxSize = valueTemp.size();
		key = entry.getKey();
		values = valueTemp;
	    }
	}
	if (values != null && values.size() > 0) {
	    for (Element element : values) {
		String href = null;
		try {
		    href = new URLCodec().decode(element.attr("abs:href"));
		} catch (DecoderException e) {
		    e.printStackTrace();
		}
		Company company = new Company();
		if (StringUtils.isNotBlank(objJson)) {
		    Company sourceCompany = JSON.parseObject(objJson, Company.class);
		    BeanUtils.copyProperties(sourceCompany, company);
		}
		if (StringUtils.isNotBlank(href)) {
		    company = super.assignment("url", href, company);
		}
		String text = element.text();
		if (StringUtils.isNotBlank(text)) {
		    company = super.assignment(key, text, company);
		}
		companys.add(company);
	    }
	}
	for (Entry<String, Elements> entry : proToEls.entrySet()) {
	    String keyEntry = entry.getKey();
	    if (!StringUtils.equals(key, keyEntry)) {
		Elements elements = entry.getValue();
		for (Company c : companys) {
		    for (Element element : elements) {
			String text = element.text();
			if (StringUtils.isNotBlank(text)) {
			    super.assignment(keyEntry, text, c);
			}
		    }
		}
	    }
	}
	super.storage.remove(storageKey);
	for (Company company : companys) {
	    resultMap.put(UUID.randomUUID().toString().replace("-", ""), company);
	}
	List<Map<String, String>> list = super.storage.storage(resultMap, xmlHandler.hasNextNode(depth), depth);
	resultList.addAll(list);
	final Set<String> keySet = resultMap.keySet();
	if (!xmlHandler.hasNextNode(depth)) {
	    new Thread(new Runnable() {
		@Override
		public void run() {
		    storageFile(keySet);
		}
	    }).start();
	}
	return resultList;
    }

    /**
     * 存储到文件
     * 
     * @Title: storageFile
     * @author 周俊
     *            void
     */
    synchronized private void storageFile(Set<String> keySet) {
	try {
	    FileWriter fileWritter = new FileWriter("E:/temp/t_company.txt", true);
	    BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	    for (String storageKey : keySet) {
		String jsonObj = super.storage.get(storageKey);
		bufferWritter.write(jsonObj + "\r\n");
		super.storage.remove(storageKey);
	    }
	    bufferWritter.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}
