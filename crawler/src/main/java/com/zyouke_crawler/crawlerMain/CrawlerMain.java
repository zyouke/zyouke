package com.zyouke_crawler.crawlerMain;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.BasicConfigurator;

import com.zyouke_crawler.handler.AbstractDataHandler;
import com.zyouke_crawler.handler.DocumentHandler;
import com.zyouke_crawler.queue.CrawlerQueue;
import com.zyouke_crawler.thread.CrawlerThreadPool;

/**
 * @ClassName: CrawlerMain.java
 * @Description: 爬虫主启动类
 * @author 周俊
 * @date 2017年9月3日 上午11:23:02
 */
public class CrawlerMain<T> {

    private static final CrawlerQueue crawlerQueue = new CrawlerQueue();
    private DocumentHandler<T> documentHandler;
    private String charsetName;
    private AbstractDataHandler<T> dataHandler;

    public CrawlerMain(String level, String baseUrl, String xmlPath,String charsetName,AbstractDataHandler dataHandler) {
	super();
	this.charsetName = charsetName;
	BasicConfigurator.configure();
	Map<String, String> map = new HashMap<String, String>();
	map.put("LEVEL", level);
	map.put("URL", baseUrl);
	crawlerQueue.put(map);
	documentHandler = new DocumentHandler<T>(xmlPath, crawlerQueue,dataHandler);
    }

    /**
     * @Title: singleStart
     * @Description: 单线程启动爬虫
     * @author 周俊
     * @date 2017年9月3日 上午11:32:38
     * @throws
     */
    public void singleStart() {
	while (crawlerQueue.hasNext()) {
	    Map<String, String> map = crawlerQueue.pull(0);
	    documentHandler.execution(map,charsetName);
	}
    }
    
    /**
     * @Title: singleStart
     * @Description: 多线程启动爬虫
     * @author 周俊
     * @date 2017年9月3日 上午11:32:38
     * @throws
     */
	public void start(int threds, long timeout) {
		ExecutorService executorService = CrawlerThreadPool.newFixedThreadPool(threds);
		boolean boo = true;
		while (boo) {
			final Map<String, String> map = crawlerQueue.pull(timeout);
			if (map == null || map.size() == 0) {
				break;
			}
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					documentHandler.execution(map, charsetName);
				}
			});
		}
		executorService.shutdown();
		while (!executorService.isTerminated());
	}
}
