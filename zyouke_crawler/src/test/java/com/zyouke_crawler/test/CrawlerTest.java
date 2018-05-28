package com.zyouke_crawler.test;

import java.io.File;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.zyouke_crawler.crawlerMain.CrawlerMain;
import com.zyouke_crawler.handler.AreaHandler;
import com.zyouke_crawler.handler.CompanyHandler;
import org.junit.Test;

/**
 * @ClassName: Test
 * @Description: 测试
 * @author 周俊
 * @date 2017年8月23日 下午2:40:35
 *
 */
public class CrawlerTest {

	@Test
	public void test1() {
		new CrawlerMain("1", "http://www.gsdpw.com/", "crawler_css.xml", "UTF-8", new CompanyHandler()).singleStart();
	}

	@Test
	public void test2() throws DocumentException {
		String path = this.getClass().getResource("/").getPath() + "crawler_css.xml";
		SAXReader saxReader = new SAXReader();
		org.dom4j.Document document = saxReader.read(new File(path));
		Element rootElement = document.getRootElement();
		System.out.println(path);
	}

	@Test
	public void test3() {
		new CrawlerMain("1", "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/index.html", "crawler_css_area.xml", "GBK", new AreaHandler()).singleStart();
	}

	@Test
	public void test4() {
		String str = "/%E5%AE%9D%E9%B8%A1公司点评网-公司企业列表/page-125";
		try {
			byte[] decode = new URLCodec().decode(str.getBytes());
			System.out.println(new String(decode));
		} catch (DecoderException e) {
			e.printStackTrace();
		}
		String result = "";
		String[] split = str.split("/");
		for (String s : split) {
			if (s.contains("page-")) {
				result = s;
				break;
			}
		}
		System.out.println(result.replace("page-", "").trim());
	}

	@Test
	public void test5() {
		new CrawlerMain("1", "http://www.gsdpw.com/", "crawler_css.xml", "UTF-8", new CompanyHandler()).start(25, 5);
	}
}
