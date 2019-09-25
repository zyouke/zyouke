package com.zyouke.designpattern.decorate;

/**
 * @Author: zhoujun
 */
public abstract class ClientDecorate extends Client{
    private Client client;

    public ClientDecorate(Client client) {
        this.client = client;
    }

    @Override
    public void connect() {
        client.connect();
    }

    @Override
    public void disConnect() {
        client.disConnect();
    }
}
