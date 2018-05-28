package com.zyouke_crawler.queue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: CrawlerQueue
 * @Description: 自定义队列
 * @author 周俊
 * @date 2017年8月21日 下午2:35:33
 *
 */
public class CrawlerQueue {

    private static final Logger logger = LoggerFactory.getLogger(CrawlerQueue.class);
    private static final LinkedBlockingQueue<Map<String, String>> queue = new LinkedBlockingQueue<Map<String, String>>();

    /**
     * @Title: put
     * @Description: 添加到队列尾部
     * @author 周俊
     * @param map
     *            void
     * @throws
     */
    public void put(Map<String, String> map) {
	try {
	    queue.put(map);
	} catch (InterruptedException e) {
	    logger.error(e.getMessage(), e);
	    e.printStackTrace();
	}
    }

    /**
     * @Title: put
     * @Description: 添加到队列尾部
     * @author 周俊
     * @param map
     *            void
     * @throws
     */
    public void put(List<Map<String, String>> maps) {
	try {
	    for (Map<String, String> map : maps) {
		queue.put(map);
	    }
	} catch (InterruptedException e) {
	    logger.error(e.getMessage(), e);
	    e.printStackTrace();
	}
    }

    /**
     * @Title: put
     * @Description: 添加到队列尾部 分开插入
     * @author 周俊
     * @param level
     * @param url
     *            void
     * @throws
     */
    public void put(String level, String url) {
	Map<String, String> map = new HashMap<String, String>();
	map.put("LEVEL", level);
	map.put("URL", url);
	put(map);
    }

    /**
     * @Title: pull
     * @Description: 获取第一个元素
     * @author 周俊
     * @return Map<String,String>
     * @throws
     */
    public Map<String, String> pull(long timeout) {
	try {
	    if (timeout == 0) {
		Map<String, String> t = queue.poll();
		return t;
	    }
	    Map<String, String> t = queue.poll(timeout, TimeUnit.SECONDS);
	    return t;
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    e.printStackTrace();
	}
	return null;
    }

    /**
     * @Title: hasNext
     * @Description: 判断是否有下一个
     * @author 周俊
     * @return boolean
     * @throws
     */
    public boolean hasNext() {
	Iterator<Map<String, String>> iterator = queue.iterator();
	return iterator.hasNext();
    }

}
