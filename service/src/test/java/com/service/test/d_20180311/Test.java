package com.service.test.d_20180311;


import com.zyouke.jdk.thread.VolatileTestThread;

public class Test {

    @org.junit.Test
    public void test1(){
        VolatileTestThread td = new VolatileTestThread();
        new Thread(td).start();
        while (true){
            int num = td.getNum();
            System.out.println("---------------" + num +"当前毫秒数:" + System.currentTimeMillis());
            if(num == 1){
                break;
            }
        }
    }
}