package com.zyouke.dao;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * GoodsDao.java
 *
 * @author zyouke
 * @create 2018/1/19 14:23
 */
@Repository
public class GoodsDao {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 修改库存
     * @param id
     */
    public void updateGoodsNum(long id){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("UPDATE T_GOODS SET GOODS_NUM = GOODS_NUM -1 WHERE ID = " + id);
        int updateCount = query.executeUpdate();
    }

}
