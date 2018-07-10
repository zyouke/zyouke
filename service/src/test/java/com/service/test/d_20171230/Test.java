package com.service.test.d_20171230;

import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.fastjson.JSON;
import com.zyouke.bean.Area;
import com.zyouke.bean.Goods;
import com.zyouke.bean.Order;
import com.zyouke.bean.User;
import com.zyouke.dubbo.spi.DubboSPIDemo;
import com.zyouke.es.Es;
import com.zyouke.es.EsConnectionPool;
import com.zyouke.es.EsConnectionPoolFactory;
import com.zyouke.jdk.memory.MemoryDemo;
import com.zyouke.jdk.spi.IHelloSpi;
import com.zyouke.proxy.GamePlayer;
import com.zyouke.proxy.GamePlayerProxy;
import com.zyouke.proxy.cglib.CGLibProxy;
import com.zyouke.proxy.cglib.CglibBean;
import com.zyouke.proxy.dynamic.GamePlayerDynamic;
import com.zyouke.proxy.dynamic.GamePlayerProxyDynamic;
import com.zyouke.proxy.dynamic.IGamePlayerDynamic;
import com.zyouke.service.IAreaService;
import com.zyouke.service.IGoodsService;
import com.zyouke.service.IOrderService;
import com.zyouke.service.IUserService;
import com.zyouke.utils.HttpClientUtil;
import com.zyouke.utils.RandomUtil;
import com.zyouke.utils.ThreadUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Test {

    @org.junit.Test
    public void test1(){
        DubboSPIDemo.getDubboSpiClass();
        //System.out.println(getClass());
    }
    @org.junit.Test
    public void  test2(){
        ServiceLoader<IHelloSpi> serviceLoader = ServiceLoader.load(IHelloSpi.class);
        for (IHelloSpi  helloSpi : serviceLoader){
            helloSpi.print();
        }
    }

    @org.junit.Test
    public void  test3(){
        GamePlayer gamePlayer = new GamePlayer();
        GamePlayerProxy proxy = new GamePlayerProxy(gamePlayer);
        proxy.killBoss();
    }

    @org.junit.Test
    public void  test4(){
        GamePlayerDynamic gamePlayer = new GamePlayerDynamic();
        GamePlayerProxyDynamic handler = new GamePlayerProxyDynamic(gamePlayer);
        IGamePlayerDynamic proxy = (IGamePlayerDynamic) Proxy.newProxyInstance(gamePlayer.getClass().getClassLoader(),
                gamePlayer.getClass().getInterfaces(), handler);
        proxy.killBoss();
    }

    @org.junit.Test
    public void  test5(){
        CGLibProxy cgLibProxy = new CGLibProxy();
        CglibBean object = (CglibBean) cgLibProxy.createProxyObject(new CglibBean());
        object.killBoss();
    }

    @org.junit.Test
    public void test26() {
        System.out.println(System.getProperty("line.separator").length());
    }

    @org.junit.Test
    public void test27() {
        EsConnectionPool esConnectionPool = EsConnectionPoolFactory.getInstance(1);
        Es.search(esConnectionPool.getConnection());
    }

    @org.junit.Test
    public void test28() {
        EsConnectionPool esConnectionPool = EsConnectionPoolFactory.getInstance(1);
        Es.search1(esConnectionPool.getConnection(), "长安村");
    }

    @org.junit.Test
    public void test29() {
        EsConnectionPool esConnectionPool = EsConnectionPoolFactory.getInstance(1);
        Es.search2(esConnectionPool.getConnection(), "长安村");
    }

    @org.junit.Test
    public void test6() {
        EsConnectionPool esConnectionPool = EsConnectionPoolFactory.getInstance(1);
        Es.search1(esConnectionPool.getConnection(), "长安村");
    }


    @org.junit.Test
    public void test7() {
        long start = System.currentTimeMillis();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("E:/work_doc/demo_file/area_file/mysqlDataToText.txt"));
            String line = null;
            List<Area> list = new ArrayList<Area>();
            while ((line = reader.readLine()) != null) {
                System.out.println("******" + line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("耗时" + (System.currentTimeMillis() - start) + "ms");
    }

    @org.junit.Test
    public void test8() {
        long[] arr = new long[]{49, 38, 65, 97, 76, 13, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        int length = arr.length;
        long tem = 0;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    tem = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tem;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }

    @org.junit.Test
    public void test9() {
        long s = System.currentTimeMillis();
        List<String> list = new ArrayList<String>(100000);
        for (int i = 0; i < 100000; i++) {
            list.add("value" + i);
        }
        System.out.println(System.currentTimeMillis() - s);
    }

    @org.junit.Test
    public void test10() {
        long s = System.currentTimeMillis();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 100000; i++) {
            list.add("value" + i);
        }
        System.out.println(System.currentTimeMillis() - s);
    }

    @org.junit.Test
    public void  test11(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        IUserService userService = (IUserService) applicationContext.getBean("userService");
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.setUserName(RandomUtil.getRandomString());
            user.setUserAge(RandomUtil.getRandomNumber());
            userService.addUser(user);
        }
    }

    @org.junit.Test
    public void  test12(){
        for (int i = 0; i < 100; i++){
            System.out.println(RandomUtil.getRandomString());
        }
    }

    @org.junit.Test
    public void  test13(){
        try {
            Field[] fields = Class.forName("annotation.Apple").getDeclaredFields();
            for (Field field : fields){
                Annotation[] annotations = field.getAnnotations();
                for (Annotation annotation : annotations){
                    Class<? extends Annotation> classAnnotation = annotation.annotationType();
                    Method[] methods = classAnnotation.getDeclaredMethods();
                    for (Method method : methods){
                        try {
                            System.out.println(method.invoke(annotation));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void  test14(){
        try {
            Method[] methods = Class.forName("com.alibaba.dubbo.registry.RegistryFactory").getDeclaredMethods();
            for (Method method : methods){
                if (method.isAnnotationPresent(Adaptive.class)){
                    System.out.println(method.getReturnType());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @org.junit.Test
    public void test15(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IOrderService orderService = (IOrderService) context.getBean("orderService");
        IGoodsService goodsService = (IGoodsService) context.getBean("goodsService");
        Order order = new Order();
        order.setMoney(1.2d);
        order.setUserName(RandomUtil.getRandomString());
        order.setCreateTime(new Date());
        orderService.addOrder(order);
        goodsService.updateGoodsNum(1);
    }

    @org.junit.Test
    public void test16(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IGoodsService goodsService = (IGoodsService) context.getBean("goodsService");
        Goods goods = new Goods();
        goods.setGoodsName("乔丹篮球鞋");
        goods.setGoodsNum(100);
        goods.setGoodsPrice(395.2);
        goodsService.addGoods(goods);
        goods.setGoodsName("富光水杯");
        goods.setGoodsNum(1000);
        goods.setGoodsPrice(25.9);
        goodsService.addGoods(goods);
    }

    @org.junit.Test
    public void test17(){
        int threadNum = 500;
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        final IAreaService bean = context.getBean(IAreaService.class);
        for (int i = 0 ;i<1000 ;i++){
            final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
            ThreadUtil.concurrentRequest(threadNum, countDownLatch, new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    List<Area> list = bean.findList(100, 100);
                }
            });
        }
    }

    @org.junit.Test
    public void test18(){
        // linux 配置的权重
        int[] weights = new int[]{4,2,1};
        // 当前权重
        int[] currentWeightArr = new int[]{0,0,0};
        // 有效权重
        int[] effectiveWeightArr = new int[]{4,1};
        for (int i = 0; i < 10; i++) {
            int maxCurrentWeight = 0;
            int maxIndex = 0;
            for (int j = 0; j < currentWeightArr.length; j++) {
                if (maxCurrentWeight < currentWeightArr[j] + effectiveWeightArr[j]){
                    maxCurrentWeight = currentWeightArr[j] + effectiveWeightArr[j];
                    maxIndex = j;
                }
                currentWeightArr[j] = currentWeightArr[j] + effectiveWeightArr[j];
            }
            System.out.println("轮询前" + arrToString(currentWeightArr));
            System.out.println("获取当前使用的服务器编号 :" + maxIndex);
            currentWeightArr[maxIndex] = maxCurrentWeight - getTotalByEffectiveWeight(effectiveWeightArr);
            System.out.println("轮询后" + arrToString(currentWeightArr));
        }
    }

    private int getTotalByEffectiveWeight(int[] array){
        int total = 0;
        for (int i = 0; i < array.length; i++) {
            total = total + array[i];
        }
        return total;
    }
    private int getMaxWeightIndex(int[] array){
        int maxWeight = 0;
        int maxIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (maxWeight < array[i]){
                maxWeight = array[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private String arrToString(int[] array){
        StringBuffer buffer = new StringBuffer("{");
        for (int j = 0; j < array.length; j++) {
            if (j == array.length -1){
                buffer.append(array[j]);
                buffer.append("}");
            }else {
                buffer.append(array[j] + ",");
            }
        }
        return buffer.toString();
    }

    @org.junit.Test
    public void test20(){
        List<String> list1 = new  ArrayList<String>();
        List<Integer> list2 = new  ArrayList<Integer>();
        System.out.println(list1.getClass() == list2.getClass());
        System.out.println(list1.getClass().toString());
        System.out.println(list2.getClass().toString());
    }

    @org.junit.Test
    public void test21(){
        List list1 = new  ArrayList();
        list1.add("aaa");
        list1.add(1);
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i));
        }
    }
    @org.junit.Test
    public void test22(){
        String getStr = HttpClientUtil.doGet("http://ip.taobao.com/service/getIpInfo.php?ip=124.193.92.18");
        System.out.println(getStr);
    }
    @org.junit.Test
    public void test23(){
        Map<String, String> map = System.getenv();
        Properties properties = System.getProperties();
        System.out.println(JSON.toJSON(properties));
        for (Map.Entry<String,String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    @org.junit.Test
    public void test24(){
        MemoryDemo memoryDemo1 = new MemoryDemo();
        System.out.println(memoryDemo1.staticMemoryDemoName);
        System.out.println("------------------------------------");
        MemoryDemo memoryDemo2 = new MemoryDemo();
        MemoryDemo.staticMemoryDemoName = "修改";
        System.out.println(memoryDemo2.staticMemoryDemoName);
        System.out.println("------------------------------------");
        MemoryDemo memoryDemo3 = new MemoryDemo();
        System.out.println(memoryDemo3.staticMemoryDemoName);
        System.out.println("------------------------------------");
    }

    @org.junit.Test
    public void test25(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.start();
        MemoryDemo memoryDemo = (MemoryDemo) context.getBean("memoryDemo");
        System.out.println("静态:" + memoryDemo.staticMemoryDemoName);
        System.out.println("非静态:" + memoryDemo.notStaticMemoryDemoName);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.gc();
        System.out.println("gc之后查看内存数据...................");
        System.out.println("静态:" + memoryDemo.staticMemoryDemoName);
        System.out.println("非静态:" + memoryDemo.notStaticMemoryDemoName);
    }
}