package test.com.zyouke.designpattern.decorate;

import com.zyouke.designpattern.decorate.Client;
import com.zyouke.designpattern.decorate.ConnectDecorate;
import com.zyouke.designpattern.decorate.MysqlClient;
import com.zyouke.designpattern.decorate.UserNameAndPasswordMysqlClient;
import org.junit.Test;

/**
 * @Author: zhoujun
 * 装饰模式的测试
 */
public class DecorateTest {

    @Test
    public void  mysqlClientTest(){
        Client mysqlClient = new MysqlClient();
        mysqlClient.connect();
        mysqlClient.disConnect();
    }

    @Test
    public void  userNameAndPasswordMysqlClientTest(){
        Client mysqlClient = new UserNameAndPasswordMysqlClient();
        mysqlClient.connect();
        mysqlClient.disConnect();
    }

    @Test
    public void  connectDecorateTest(){
        Client mysqlClient = new MysqlClient();
        mysqlClient = new ConnectDecorate(mysqlClient);
        mysqlClient.connect();
        mysqlClient.disConnect();
    }


}
