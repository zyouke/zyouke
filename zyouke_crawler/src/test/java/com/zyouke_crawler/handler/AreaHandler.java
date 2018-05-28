package com.zyouke_crawler.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.zyouke_crawler.bean.Area;
import com.zyouke_crawler.storage.Storage;
import com.zyouke_crawler.utils.CrawlerUtils;

/**
 * @ClassName: DataHandler
 * @Description: 处理数据
 * @author 周俊
 * @date 2017年9月15日 上午9:35:51
 */
public class AreaHandler extends AbstractDataHandler<Area> {

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
	Map<String, Area> resultMap = new HashMap<String, Area>();
	List<Area> list = new ArrayList<Area>();
	if (StringUtils.equals(depth, "1")) {
	    for (Entry<String, Elements> entry : proToEls.entrySet()) {
		String keyEntry = entry.getKey();
		Elements elements = entry.getValue();
		for (Element element : elements) {
		    Area area = new Area();
		    area.setParent("0");
		    String href = null;
		    try {
			href = new URLCodec().decode(element.attr("abs:href"));
		    } catch (DecoderException e) {
			e.printStackTrace();
		    }
		    super.assignment("url", href, area);
		    if (!proToEls.keySet().contains("code")) {
			String attrHref = element.attr("href");
			area.setCode(attrHref.replace(".html", "").trim() + "0000000000");
			area.setParent("0");
		    }
		    String text = element.text();
		    if (StringUtils.isNotBlank(text)) {
			assignment(keyEntry, text, area);
			if (StringUtils.equals(keyEntry, "value")) {
			    super.assignment("fullName",text, area);
			}
		    }
		    list.add(area);
		}
	    }
	} else {
	    String objJson = null;
	    if (StringUtils.isNotBlank(storageKey)) {
		objJson = super.storage.getValue(storageKey);
	    }
	    Area area = new Area();
	    if (StringUtils.isNotBlank(objJson)) {
		Area jsonArea = JSON.parseObject(objJson, Area.class);
		area.setParent(jsonArea.getCode());
		area.setFullName(jsonArea.getFullName());
	    }
	    for (Entry<String, Elements> entry : proToEls.entrySet()) {
		String keyEntry = entry.getKey();
		Elements elements = entry.getValue();
		for (Element element : elements) {
		    String isRequest = xmlHandler.getAttributeValue(depth, "name", keyEntry, "isRequest");
		    if (CrawlerUtils.toBoolean(isRequest, true)) {
			String href = null;
			try {
			    href = new URLCodec().decode(element.attr("abs:href"));
			} catch (DecoderException e) {
			    e.printStackTrace();
			}
			super.assignment("url", href, area);
			if (!proToEls.keySet().contains("code")) {
			    String attrHref = element.attr("href");
			    area.setCode(attrHref.replace(".html", "").trim() + "0000000000");
			    area.setParent("0");
			}
		    }
		    String text = element.text();
		    if (StringUtils.isNotBlank(text)) {
			super.assignment(keyEntry, text, area);
			if (StringUtils.equals(keyEntry, "value")) {
			    super.assignment("fullName",area.getFullName() + text, area);
			}
		    }
		}
	    }
	    list.add(area);
	}
	for (Area area :list) {
	    resultMap.put(UUID.randomUUID().toString().replace("-", ""), area);
	}
	List<Map<String, String>> resultList = super.storage.storage(resultMap,false, depth);
	return resultList;
    }

}
