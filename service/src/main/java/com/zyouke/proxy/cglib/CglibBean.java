package com.zyouke.proxy.cglib;

public class CglibBean {
    public void killBoss() {
        long start = System.currentTimeMillis();
        while (true){
            if (System.currentTimeMillis() - start == 1000){
                break;
            }
            System.out.println("开始打怪......");
        }
    }
}