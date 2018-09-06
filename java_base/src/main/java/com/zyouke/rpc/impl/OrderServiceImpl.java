package com.zyouke.rpc.impl;

import com.zyouke.bean.Order;

/**
 * @Author: zhoujun
 * 订单的实现类
 */
public class OrderServiceImpl implements IOrderService{
    @Override
    public void addOrder(Order order) {
        System.out.println("---------- : " + order.toString());
    }
}
