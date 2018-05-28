package com.zyouke.es;

import org.elasticsearch.client.transport.TransportClient;

/**
 * 自定义连接实体
 * @ClassName: EsConnection 
 * @author 周俊
 * @date 2017年10月9日 上午10:26:11
 */
public class EsConnection {

    // es连接
    private TransportClient client;
    // 是否被占用
    private boolean isBusy;
    
    public TransportClient getClient() {
        return client;
    }
    public void setClient(TransportClient client) {
        this.client = client;
    }
    public boolean isBusy() {
        return isBusy;
    }
    public void setBusy(boolean isBusy) {
        this.isBusy = isBusy;
    }
    // 关闭连接
    public void close() {
        this.isBusy = false;
    }
}
