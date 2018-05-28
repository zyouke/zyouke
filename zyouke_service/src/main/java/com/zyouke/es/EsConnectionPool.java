package com.zyouke.es;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.util.ObjectUtils;

/**
 * es 连接池 
 * @ClassName: EsConnectionPool 
 * @author 周俊
 * @date 2017年10月9日 上午9:48:01
 */
public class EsConnectionPool {

    private int initSize = 10;
    private int maxSize = 20;
    private int stepSize = 5;
    private Vector<EsConnection> pool = new Vector<EsConnection>();
    
    public EsConnectionPool(int initSize) {
	if (initSize > 0) {
	    this.initSize = initSize;
	}
	initConnectionPool(this.initSize);
    }
    
    /**
     * 初始化连接池
     * @Title: initConnectionPool
     * @author 周俊  
     * @return void
     */
    private void initConnectionPool(int initSize){
	if (initSize > maxSize || pool.size() + initSize > maxSize) {
	    throw new RuntimeException("连接池已满");
	}
	for (int i = 0; i < initSize; i++) {
	    EsConnection esConnection = new EsConnection();
	    TransportClient client = getClient();
	    esConnection.setClient(client);
	    esConnection.setBusy(false);
	    pool.add(esConnection);
	}
    }
    
    /**
     * 获取连接
     * @Title: getConnection
     * @author 周俊  void
     */
    public synchronized EsConnection getConnection() {
	if (pool.size() < 1) {
	    throw new RuntimeException("连接池初始化失败");
	}
	EsConnection esConnection = getConnectionFromPool();
	while (esConnection == null) {
	    initConnectionPool(stepSize);
	    esConnection = getConnectionFromPool();
	}
	return esConnection;
    }
    
    /**
     * 从pool中获取连接
     * @Title: getConnectionFromPool
     * @author 周俊 
     * @return EsConnection
     */
    public EsConnection getConnectionFromPool(){
	for (EsConnection esConnection : pool) {
	    if (!esConnection.isBusy()) {
		esConnection.setBusy(true);
		return esConnection;
	    }
	}
	return null;
    }
    
    
    /**
     * 获取es连接
     * @Title: getClient
     * @author 周俊 
     * @return TransportClient
     */
    private TransportClient getClient() {
	try {
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("cluster.name", "zyouke_es");
	    Settings.Builder settings = Settings.builder().put(map);
	    TransportClient client = TransportClient.builder().settings(settings).build();
	    client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("103.82.53.221"), Integer.parseInt("9300")));
	    return client;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	 return null;
    }
    
}
