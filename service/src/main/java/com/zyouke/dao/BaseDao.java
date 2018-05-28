package com.zyouke.dao;

import com.zyouke.bean.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BaseDao.java
 * 基础的dao
 * @author zyouke
 * @create 2018/1/19 11:26
 */
@Repository
public class BaseDao<T> {
    @Autowired
    private SessionFactory sessionFactory;
    public void add(T t){
        Session session = sessionFactory.getCurrentSession();
        session.save(t);
        session.close();
    }

    public void add(List<T> ts){
        Session session = sessionFactory.getCurrentSession();
        for (T t : ts) {
            session.save(t);
        }
        session.flush();
        session.clear();
        session.close();
    }

    public void update(T t){
        Session session = sessionFactory.getCurrentSession();
        session.update(t);
    }
}
