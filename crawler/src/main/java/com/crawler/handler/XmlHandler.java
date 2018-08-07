package com.crawler.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ObjectUtils;

/**
 * @ClassName: XmlHandler
 * @Description: 处理xml
 * @author 周俊
 * @date 2017年8月22日 下午2:26:39
 *
 */
public class XmlHandler {
    private static final Logger logger = Logger.getLogger(XmlHandler.class.getName());
    private static List<Element> list = new ArrayList<org.dom4j.Element>();
    private static Element rootElement;

    public XmlHandler(String xmlPath) {
	init(xmlPath);
    }

    /**
     * @Title: init
     * @Description: 初始化
     * @author 周俊 void
     * @throws
     */
    private void init(String xmlPath) {
	try {
	    xmlPath = this.getClass().getResource("/").getPath() + xmlPath;
	    logger.info("加载css选择器的配置文件:" + xmlPath);
	    SAXReader saxReader = new SAXReader();
	    org.dom4j.Document document = saxReader.read(new File(xmlPath));
	    rootElement = document.getRootElement();
	    list = rootElement.elements();
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
    }

    /**
     * 根据深度获取元素
     * 
     * @Title: queryDepth
     * @author 周俊
     * @param depth
     * @return Iterator<Element>
     */
    public List<Element> querySimplyDepth(String depth) {
	Element element = list.get(Integer.valueOf(depth) - 1);
	List<Element> elements = element.elements();
	return elements;
    }


    /**
     * @Title: getAttributeValue
     * @Description: 根据深度和属性名获取深度上的属性值
     * @author 周俊
     * @param attributeName
     * @return String
     * @throws
     */
    public String getAttributeValue(String depth, String attributeName) {
	org.dom4j.Element element = list.get(Integer.valueOf(depth) - 1);
	Attribute attribute = element.attribute(attributeName);
	if (attribute != null) {
	    String attrValue = attribute.getValue();
	    return attrValue;
	}
	return null;
    }
    
    /**
     * 获取属性值, 获取深度下属性名为attributeName,值为attributeValue元素的另一个属性名为resultAttributeName的值
     * @Title: getAttributeValue
     * @author 周俊 
     * @param depth
     * @param attributeName
     * @param attributeValue
     * @param resultAttributeName
     * @return String
     */
    public String getAttributeValue(String depth, String attributeName,String attributeValue,String resultAttributeName) {
	org.dom4j.Element element = list.get(Integer.valueOf(depth) - 1);
	List<Element> elements = element.elements();
	String resultAttributeValue = "";
	for (Element e : elements) {
	    String value = getAttributeValue(e, attributeName);
	    if (StringUtils.equals(attributeValue, value)) {
		resultAttributeValue = getAttributeValue(e, resultAttributeName);
		break;
	    }
	}
	return resultAttributeValue;
    }
    /**
     * 根据属性名,属性值,当前元素获取同一个父元素下的元素
     * 
     * @Title: getElement
     * @author 周俊
     * @param attributeValue
     *            属性值
     * @param attributeName
     *            属性名
     * @param element
     *            当前元素
     * @return Element 元素
     * @throws Exception
     */
    public Element getElement(String attributeValue, String attributeName, Element element) throws Exception {
	Element parentE = element.getParent();
	Iterator<Element> elementIterator = parentE.elementIterator(element.getName());
	Element resultElement = null;
	while (elementIterator.hasNext()) {
	    Element e = elementIterator.next();
	    if (StringUtils.equals(attributeValue, getAttributeValue(e, attributeName))) {
		resultElement = e;
		break;
	    }
	}
	return resultElement;
    }

    /**
     * @Title: getAttributeValue
     * @Description: 根据元素和属性名获取属性的值
     * @author 周俊
     * @param element
     * @param attributeName
     * @return String
     * @throws
     */
    public String getAttributeValue(Element element, String attributeName) {
	Attribute attribute = element.attribute(attributeName);
	if (attribute != null) {
	    String attrValue = attribute.getValue();
	    return attrValue;
	}
	return null;
    }

    /**
     * @Title: hasNextNode
     * @Description: 判断当前深度下是否还存在下一个深度
     * @author 周俊
     * @return 存在返回true 不存在返回false
     * @throws
     */
    public boolean hasNextNode(String depth) {
	int index = Integer.valueOf(depth);
	if (list.size() - 1 < index) {
	    return false;
	}
	return true;
    }

}
