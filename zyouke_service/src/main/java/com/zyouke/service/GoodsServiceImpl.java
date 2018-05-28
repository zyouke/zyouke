package com.zyouke.service;

import com.zyouke.bean.Goods;
import com.zyouke.dao.BaseDao;
import com.zyouke.dao.GoodsDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * GoodsServiceImpl.java
 *
 * @author zyouke
 * @create 2018/1/19 11:57
 */
@Service("goodsService")
public class GoodsServiceImpl implements IGoodsService{

    @Resource
    private BaseDao<Goods> baseDao;
    @Resource
    private GoodsDao goodsDao;
    @Override
    public void updateGoodsNum(long id) {
        goodsDao.updateGoodsNum(id);
    }

    @Override
    public void addGoods(Goods goods) {
        baseDao.add(goods);
    }
}
