package com.zyouke.designpattern.decorate;

/**
 * @Author: zhoujun
 */
public class UserNameAndPasswordMysqlClient extends MysqlClient{
    private void userNameAndPassword(){
        System.out.println("你输入的    用户名为:张三    密码为:123456");
    }

    @Override
    public void connect() {
        userNameAndPassword();
        super.connect();
    }
}
