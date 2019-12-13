package com.zyouke.map;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HashMapTest {
    public static void main(String[] args){
        //putTest();
        hashTest();
    }

    public static void putTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Map<String, String> map = new HashMap<>(2);
        for(int i = 0; i < 100000; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run(){
                    map.put(UUID.randomUUID().toString(),"");
                }
            });
        }
        executorService.shutdown();
        System.out.println("结束。。。。。。。。。。。");
    }

    public static void hashTest(){
        Map<String, String> map = new HashMap<>(2);
        map.put("aaaaa","aaaaa");
        map.put("bbbbb","aaaaa");
    }
}
