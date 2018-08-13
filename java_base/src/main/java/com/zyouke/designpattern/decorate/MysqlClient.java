package com.zyouke.designpattern.decorate;

/**
 * @Author: zhoujun
 */
public class MysqlClient extends Client{
    @Override
    public void connect() {
        System.out.println("开始连接mysql数据库......");
    }

    @Override
    public void disConnect() {
        System.out.println("开始销毁mysql数据库的连接......");
    }
}
