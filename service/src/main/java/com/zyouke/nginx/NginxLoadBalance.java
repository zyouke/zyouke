package com.zyouke.nginx;

import java.util.ArrayList;
import java.util.List;

/**
 * NginxLoadBalance.java
 * 模拟nginx负载均衡算法
 * @author zyouke
 * @create 2018/01/07 16:12
 */
public class NginxLoadBalance {

    // 本次选择服务器的索引
    private int currentIndex;
    // 有效权重
    private int effectiveWeight;
    // 当前权重
    private int currentWeight;
    // 最大权重
    private int maxWeight;
    // 最大公约数
    private int maxCommonDivisor;
    // 服务器集群
    private List<Server> servers = new ArrayList<>();

    // 获取最大权重
    private int getMaxWeight(){
        maxWeight = 0;
        for (Server server : servers) {
            if (server.getWeight() > maxWeight){
                maxWeight = server.getWeight();
            }
        }
        return maxWeight;
    }
    // 获取这组服务器权重公约数
    private int getMaxCommonDivisor(){
        int maxCommonDivisor = 0;
        for (int i = 0; i < servers.size(); i++) {
            if (i == 0){
              maxCommonDivisor = getMaxCommonDivisor(servers.get(i).getWeight(), servers.get(i + 1).getWeight());
            }else {
                maxCommonDivisor = getMaxCommonDivisor(maxCommonDivisor,servers.get(i).getWeight());
            }
        }
        return maxCommonDivisor;
    }
    // 获取两个数的最大公约数
    private int getMaxCommonDivisor(int a,int b) {
        if (a % b == 0) {
            return b;
        }
        return getMaxCommonDivisor(b,a%b);
    }
    // 获取服务
    public Server getServer(){
        while (true){
            currentIndex = (currentIndex + 1)%servers.size();
            if (currentIndex == 0){
                currentWeight = currentWeight - maxCommonDivisor;
                if (currentWeight < 0){
                    currentWeight = maxWeight;
                }
                if (currentWeight == 0){
                    return  null;
                }
            }
            if (servers.get(currentIndex).getWeight() >= currentWeight){
                return servers.get(currentIndex);
            }
        }
    }

    // 初始化
    private void init(){
        servers.add(new Server("192.168.191.1", 1));
        servers.add(new Server("192.168.191.2", 2));
        servers.add(new Server("192.168.191.4", 1));
        servers.add(new Server("192.168.191.8", 1));
        servers.add(new Server("192.168.191.9", 1));
        maxWeight = getMaxWeight();
        currentIndex = -1;
        maxCommonDivisor = getMaxCommonDivisor();
    }

    public static void main(String[] args) {
        NginxLoadBalance nlb = new NginxLoadBalance();
        nlb.init();
        for (int i = 0; i < 15; i++) {
            System.out.println(nlb.getServer().toString());
        }
    }




    // 服务实体
    private class Server{
        private String ip;
        private int weight;
        public Server(String ip, int weight) {
            this.ip = ip;
            this.weight = weight;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Server{" +
                    "ip='" + ip + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }

}
