package com.zyouke.bean;

/**
 * dubbo 测试返回的bean
 */
public class DubboBean {
    private String requestTime;
    private String executeTime;
    private String threadName;

    public DubboBean(String requestTime, String executeTime, String threadName) {
        this.requestTime = requestTime;
        this.executeTime = executeTime;
        this.threadName = threadName;
    }

    @Override
    public String toString() {
        return "DubboBean{" +
                "requestTime='" + requestTime + '\'' +
                ", executeTime='" + executeTime + '\'' +
                ", threadName='" + threadName + '\'' +
                '}';
    }
}
