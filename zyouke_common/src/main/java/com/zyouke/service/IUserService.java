package com.zyouke.service;

import com.zyouke.bean.User;

import java.util.List;

/**
 * IUserService.java
 * 用户管理的接口
 * @author zyouke
 * @create 2017/11/14 11:14
 */
public interface IUserService {

    /**
     * @Instructions: 添加用户
     * @Author: zyouke
     * @Param: user 用户实体
     * @Return: void
     * @Date: 2017/11/14 13:42
     */
    public void addUser(User user);

    public void addUser(List<User> users);
}
