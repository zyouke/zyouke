package com.zyouke.bean;

/**
 * dubbo 测试返回的bean
 */
public class DubboBean {
    private String threadName;
    private String requestTime;
    private int executeTime;
    private int requestCount;

    public DubboBean(String requestTime, int executeTime, String threadName) {
        this.requestTime = requestTime;
        this.executeTime = executeTime;
        this.threadName = threadName;
    }
    public DubboBean(String threadName,int requestCount) {
        this.threadName = threadName;
        this.requestCount = requestCount;
    }

    public String toStringNotRequestCount() {
        return "DubboBean{" +
                "threadName='" + threadName + '\'' +
                ", requestTime='" + requestTime + '\'' +
                ", executeTime=" + executeTime +
                '}';
    }

    public String toStringNotRequestTimeExecuteTime() {
        return "DubboBean{" +
                "threadName='" + threadName + '\'' +
                ", requestCount=" + requestCount +
                '}';
    }
}
