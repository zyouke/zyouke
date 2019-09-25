package com.zyouke.proxy.dynamic;

public class GamePlayerDynamic implements IGamePlayerDynamic {
    @Override
    public void killBoss() {
        for (int i = 0; i < 10; i++) {
            System.out.println("开始打怪......");
        }
    }
}
