package com.zyouke.proxy;

public class GamePlayerProxy implements  IGamePlayer{

    private IGamePlayer gamePlayer;
    public GamePlayerProxy(IGamePlayer gamePlayer) {
            this.gamePlayer = gamePlayer;
    }

    @Override
    public void killBoss() {
        gamePlayer.killBoss();
    }
}
