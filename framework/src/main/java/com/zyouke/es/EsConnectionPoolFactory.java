package com.zyouke.es;

/**
 * 用于单例生产线程池
 * @ClassName: EsConnectionPoolFactory 
 * @author 周俊
 * @date 2017年10月9日 上午11:40:20
 */
public class EsConnectionPoolFactory {
    
    public static int initSize;
    public static class ProduceEsConnectionPool{
	public static EsConnectionPool esConnectionPool = new EsConnectionPool(initSize);
    }
    
    public static EsConnectionPool getInstance(int parSize){
	initSize = parSize;
	return ProduceEsConnectionPool.esConnectionPool;
    }
}
