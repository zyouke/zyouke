package com.zyouke.service;


import com.zyouke.bean.Goods;

/**
 * IGoodsService.java
 * 商品接口
 * @author zyouke
 * @create 2018/1/19 11:54
 */
public interface IGoodsService {
    public void updateGoodsNum(long id);
    public void addGoods(Goods goods);
}
