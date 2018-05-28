package com.zyouke.proxy.dynamic;

public class GamePlayerDynamic implements IGamePlayerDynamic {
    @Override
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
