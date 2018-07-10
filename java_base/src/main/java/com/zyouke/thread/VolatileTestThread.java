package com.zyouke.thread;

/**
 * VolatileTestThread.java
 *
 * @author zyouke
 * @create 2018/3/9 17:02
 */
public class VolatileTestThread implements Runnable {

    private int num = 0;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("准备修改数据................");
        try {
            Thread.sleep(500);
            num = 1;
            System.out.println("修改数据完成的毫秒数:" + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
