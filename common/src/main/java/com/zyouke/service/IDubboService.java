package com.zyouke.service;

/**
 * @Author: zhoujun
 * dubbo 测试的接口
 */
public interface IDubboService {

    /**
     * 获取当前执行的线程
     * @return
     */
    String getExecuteThread();

    /**
     * 获取请求总数
     * @return
     */
    String getRequestCount();

    /**
     * 重置計數器
     */
    void resetCounter();

}
