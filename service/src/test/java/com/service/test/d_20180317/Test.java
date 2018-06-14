package com.service.test.d_20180317;


import com.alibaba.fastjson.JSON;
import com.zyouke.bean.Goods;
import com.zyouke.bean.User;
import com.zyouke.dao.AreaDao;
import com.zyouke.service.IGoodsService;
import com.zyouke.service.IUserService;
import com.zyouke.utils.RandomUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    @org.junit.Test
    public void test1() {
        String str = RandomStringUtils.random(10);
        System.out.println("------------" + str);
        System.out.println("------------" + new String(str.getBytes()));
        System.out.println("------------" + RandomStringUtils.randomAlphabetic(10));
        System.out.println("------------" + RandomStringUtils.randomAscii(10));
        System.out.println("------------" + RandomStringUtils.randomAlphanumeric(10));
        System.out.println("------------" + RandomStringUtils.randomNumeric(10));
    }

    @org.junit.Test
    public void test2() {
        final float[] loadFactorArr = new float[]{0.15f, 0.18f, 1.53f, 3.26f, 0.78f, 0.56f, 1.36f, 0.96f, 1};
        try {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
            final IGoodsService goodsService = (IGoodsService) applicationContext.getBean("goodsService");
            BufferedReader reader = new BufferedReader(new FileReader("E:/temp/goods.txt"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                final String finalLine = line;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String[] lineArray = finalLine.split("#");
                        for (String l : lineArray) {
                            Goods goods = new Goods();
                            if (StringUtils.isNotBlank(l.trim())) {
                                goods.setGoodsName(l.trim());
                                goods.setGoodsNum(RandomUtil.getRandomNumber(1000, 100000));
                                goods.setGoodsPrice(RandomUtil.getRandomNumber(10, 1000) * (loadFactorArr[RandomUtil.getRandomNumber(0, loadFactorArr.length)]));
                                goodsService.addGoods(goods);
                            }
                        }
                    }
                }).start();
            }
            Thread.sleep(1000 * 20);
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    @org.junit.Test
    public void test4() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("aaaaa");
        buffer.append("aaaaa");
        buffer.append("aaaaa");
        System.out.println(buffer.toString());
        buffer.delete(0,buffer.length());
        System.out.println(buffer.toString());
    }

    @org.junit.Test
    public void test3() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AreaDao areaDao = (AreaDao) applicationContext.getBean("areaDao");
        List<String> codes = areaDao.queryAreaCodes();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("E:/work_doc/user.txt"));
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < 3000000; i++) {
                if (i % 25000 == 0){
                    if (buffer.length() > 0){
                        buffer.append("('" + RandomStringUtils.randomAlphanumeric(10) + "'," + RandomUtil.getRandomNumber(10, 100) + ",'" + codes.get(RandomUtil.getRandomNumber(0, codes.size())) + "');");
                        writeSql(buffer.toString(),null,bufferedWriter);
                        buffer.delete(0,buffer.length());
                    }
                    buffer.append("INSERT INTO T_USER (USER_NAME,USER_AGR,AREA_CODE) VALUES ");
                }else {
                    buffer.append("('" + RandomStringUtils.randomAlphanumeric(10) + "'," + RandomUtil.getRandomNumber(10, 100) + ",'" + codes.get(RandomUtil.getRandomNumber(0, codes.size())) + "'),");
                }
                if (i % 1024 == 0){
                    System.out.println("==================================");
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写sql文件到文本
     * @param sql
     * @param filePath
     * @param bufferedWriter
     */
    private void writeSql(String sql,String filePath,BufferedWriter bufferedWriter){
        try {
            bufferedWriter.write(sql+"\r\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void test5() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("aaa","");
        System.out.println(JSON.toJSONString(map));
    }
}