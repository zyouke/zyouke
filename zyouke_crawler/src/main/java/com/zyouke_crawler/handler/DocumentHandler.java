package com.zyouke_crawler.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zyouke_crawler.http.HttpUtils;
import com.zyouke_crawler.queue.CrawlerQueue;

/**
 * @ClassName: DocumentHandler
 * @Description: 处理文档
 * @author 周俊
 * @date 2017年8月21日 上午11:20:26
 */
public class DocumentHandler<T> {

    private static final Logger logger = Logger.getLogger(DocumentHandler.class.getName());

    private XmlHandler xmlHandler;

    private CrawlerQueue crawlerQueue;

    private String charsetName;

    private AbstractDataHandler dataHandler;
    
    public DocumentHandler(String xmlPath, CrawlerQueue crawlerQueue, AbstractDataHandler dataHandler) {
	xmlHandler = new XmlHandler(xmlPath);
	this.crawlerQueue = crawlerQueue;
	this.dataHandler = dataHandler;
    }

    /**
     * @Title: execution
     * @Description: 对外提供的执行器方法
     * @author 周俊
     * @date 2017年9月3日 下午3:56:49
     * @throws
     */
    public void execution(Map<String, String> map, String charsetName) {
	this.charsetName = charsetName;
	String level = map.get("LEVEL");
	String url = map.get("URL");
	if (url.contains("page")) {
	    System.out.println("");
	}
	if (StringUtils.isNotBlank(url)) {
	    String storageKey = map.get("STORAGE_KEY");
	    logger.info("url : " + url);
	    try {
		boolean boo = parse(url, level, storageKey);
		if (!boo) {
		    crawlerQueue.put(map);
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}
    }

    /**
     * @throws Exception
     * @Title: parse
     * @Description: 解析页面中的元素获取相应的数据
     * @author 周俊
     * @param storageKey level
     * @return void
     * @throws
     */
    private boolean parse(String url, String depth, String storageKey) throws Exception {
	Document document = HttpUtils.doGet(url, charsetName);
	if (document == null) {
	    return false;
	}
	List<org.dom4j.Element> xmlElements = xmlHandler.querySimplyDepth(depth);

	String attributeValue = xmlHandler.getAttributeValue(depth, "webpageParentCssSelector");
	if (StringUtils.isNotBlank(attributeValue)) {
	    Elements parentElements = document.select(attributeValue);
	    for (Element parentElement : parentElements) {
		parseByElement(document,parentElement, xmlElements, depth, storageKey);
	    }
	} else {
	    parseByDocument(document, xmlElements, depth, storageKey);
	}
	return true;
    }

    /**
     * 根据文档解析数据
     * 
     * @Title: parseByDocument
     * @author 周俊
     * @param document
     *            页面文档
     * @param xmlElements
     *            xml元素集合
     * @param depth
     *            深度
     * @param storageKey
     *            存储key
     * @throws Exception
     *             void
     */
    private void parseByDocument(Document document, List<org.dom4j.Element> xmlElements, String depth, String storageKey) throws Exception {
	String indexAtt = "index";
	String cssSelectorAtt = "cssSelector";
	String nameAtt = "name";
	Map<String, Elements> proToEls = new HashMap<String, Elements>();
	for (org.dom4j.Element eXml : xmlElements) {
	    String index = xmlHandler.getAttributeValue(eXml, indexAtt);
	    String cssSelector = xmlHandler.getAttributeValue(eXml, cssSelectorAtt);
	    String name = xmlHandler.getAttributeValue(eXml, nameAtt);
	    if (StringUtils.isNotBlank(cssSelector)) {
		Elements elements = document.select(cssSelector);
		if (elements.size() > 0) {
		    if (StringUtils.isNotBlank(index)) {
			Element element = elements.get(Integer.valueOf(index));
			elements.clear();
			elements.add(element);
		    }
		}
		proToEls.put(name, elements);
	    }

	}
	List<Map<String, String>> list = dataHandler.createStorageData(document,proToEls, storageKey,depth,xmlHandler);
	crawlerQueue.put(list);
    }

    /**
     * 根据页面元素解析数据
     * 
     * @Title: parseByElement
     * @author 周俊
     * @param parElement
     *            页面元素
     * @param xmlElements
     *            xml元素集合
     * @param depth
     *            深度
     * @param storageKey
     *            存储key
     * @throws Exception
     *             void
     */
    private void parseByElement(Document document, Element parElement, List<org.dom4j.Element> xmlElements, String depth, String storageKey) throws Exception {
	String indexAtt = "index";
	String cssSelectorAtt = "cssSelector";
	String nameAtt = "name";
	Map<String, Elements> proToEls = new HashMap<String, Elements>();
	for (org.dom4j.Element eXml : xmlElements) {
	    String index = xmlHandler.getAttributeValue(eXml, indexAtt);
	    String cssSelector = xmlHandler.getAttributeValue(eXml, cssSelectorAtt);
	    String name = xmlHandler.getAttributeValue(eXml, nameAtt);
	    if (StringUtils.isNotBlank(cssSelector)) {
		Elements elements = parElement.select(cssSelector);
		if (elements.size() > 0) {
		    if (StringUtils.isNotBlank(index)) {
			Element element = elements.get(Integer.valueOf(index));
			elements.clear();
			elements.add(element);
		    }
		}
		proToEls.put(name, elements);
	    }
	}
	List<Map<String, String>> list = dataHandler.createStorageData(document,proToEls, storageKey,depth,xmlHandler);
	crawlerQueue.put(list);
	crawlerQueue.put(list);
    }
}
