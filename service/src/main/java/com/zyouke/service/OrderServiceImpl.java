package com.zyouke.service;

import com.zyouke.bean.Goods;
import com.zyouke.bean.Order;
import com.zyouke.dao.BaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * OrderServiceImpl.java
 * 订单接口实现类
 * @author zyouke
 * @create 2018/1/19 9:58
 */
@Service("orderService")
public class OrderServiceImpl implements IOrderService{
    @Resource
    private BaseDao<Order> baseDao;
    @Resource
    private IGoodsService goodsService;
    @Override
    public void addOrder(Order order){
        baseDao.add(order);
        //goodsService.updateGoodsNum(1);
    }
}
