package com.zyouke.map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
    public static void main(String[] args){
        Map<String,String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("bb","bbb");
        linkedHashMap.put("aa","aaa");
        linkedHashMap.put("cc","ccc");
        for(Map.Entry<String,String> entry : linkedHashMap.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
