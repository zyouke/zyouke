package com.zyouke.bean;


/**
 * Goods.java
 * 商品实例
 * @author zyouke
 * @create 2018/1/19 9:46
 */
public class Goods {
    private long id;
    private String goodsName;
    private double goodsPrice;
    private long goodsNum;

    public long getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(long goodsNum) {
        this.goodsNum = goodsNum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}
