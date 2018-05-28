package com.zyouke.service;

import com.zyouke.bean.User;
import com.zyouke.dao.AreaDao;
import com.zyouke.dao.BaseDao;
import com.zyouke.dao.UserDao;
import com.zyouke.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserServiceImpl.java
 *
 * @author zyouke
 * @create 2017/11/14 13:44
 */
@Service("userService")
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserDao userDao;
    @Autowired
    private BaseDao<User> baseDao;
    @Autowired
    private AreaDao areaDao;

    /**
     * @param user
     * @Instructions: 添加用户
     * @Author: zyouke
     * @Param: user 用户实体
     * @Return: int
     * @Date: 2017/11/14 13:42
     */
    @Override
    public void addUser(User user) {
        // 插入区域
        baseDao.add(user);
    }

    @Override
    public void addUser(List<User> users) {
        // 插入区域
        baseDao.add(users);
    }
}
