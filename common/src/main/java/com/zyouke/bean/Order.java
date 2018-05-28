package com.zyouke.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Order.java
 * 订单实体
 * @author zyouke
 * @create 2017/12/17 20:49
 */
public class Order implements Serializable{
    private Long id; // 主键,也是订单编号
    private String userName; // 用户姓名
    private Double money; // 订单金额
    private Date createTime; // 创建时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
