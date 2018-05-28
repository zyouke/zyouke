package com.zyouke.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zyouke.service.IAreaService;

public class EsMain {
    
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		IAreaService areaService = (IAreaService) applicationContext.getBean("areaService");
		areaService.addAreaToEs();
		//areaService.queryCount();
		//areaService.queryListByLimit();
	}
}
