package com.zyouke.dao;

import com.zyouke.bean.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * UserDao.java
 * user Dao
 * @author zyouke
 * @create 2017/11/14 13:48
 */
@Repository
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;

}
