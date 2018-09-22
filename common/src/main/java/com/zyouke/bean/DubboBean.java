package com.zyouke.bean;

/**
 * dubbo 测试返回的bean
 */
public class DubboBean {
    private String threadName;
    private String requestTime;
    private int executeTime;

    public DubboBean(String requestTime, int executeTime, String threadName) {
        this.requestTime = requestTime;
        this.executeTime = executeTime;
        this.threadName = threadName;
    }

    @Override
    public String toString() {
        return "DubboBean{" +
                "threadName='" + threadName + '\'' +
                ", requestTime='" + requestTime + '\'' +
                ", executeTime='" + executeTime + '\'' +
                '}';
    }
}
