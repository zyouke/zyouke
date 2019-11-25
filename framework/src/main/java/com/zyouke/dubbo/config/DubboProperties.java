package com.zyouke.dubbo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboProperties {

    @Value("${dubbo.provider.name}")
    private String dubboProviderName;
    @Value("${dubbo.registry.addr}")
    private String zookeeperAddr;
    @Value("${dubbo.protocol.name}")
    private String dubboProtocolName;
    @Value("${dubbo.protocol.port}")
    private Integer dubboProtocolPort;

    public String getDubboProviderName(){
        return dubboProviderName;
    }

    public void setDubboProviderName(String dubboProviderName){
        this.dubboProviderName = dubboProviderName;
    }

    public String getZookeeperAddr(){
        return zookeeperAddr;
    }

    public void setZookeeperAddr(String zookeeperAddr){
        this.zookeeperAddr = zookeeperAddr;
    }

    public String getDubboProtocolName(){
        return dubboProtocolName;
    }

    public void setDubboProtocolName(String dubboProtocolName){
        this.dubboProtocolName = dubboProtocolName;
    }

    public Integer getDubboProtocolPort(){
        return dubboProtocolPort;
    }

    public void setDubboProtocolPort(Integer dubboProtocolPort){
        this.dubboProtocolPort = dubboProtocolPort;
    }
}
