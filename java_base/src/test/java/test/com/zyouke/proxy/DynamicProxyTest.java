package test.com.zyouke.proxy;

import com.zyouke.proxy.dynamic.GamePlayerDynamic;
import com.zyouke.proxy.dynamic.GamePlayerProxyDynamic;
import com.zyouke.proxy.dynamic.IGamePlayerDynamic;
import org.junit.Test;

/**
 * @Author: zhoujun
 * 动态代理测试类
 */
public class DynamicProxyTest {

    @Test
    public void gamePlayerDynamicTest(){
        IGamePlayerDynamic gamePlayerDynamic = new GamePlayerDynamic();
        GamePlayerProxyDynamic gamePlayerProxyDynamic = new GamePlayerProxyDynamic(gamePlayerDynamic);
        IGamePlayerDynamic proxy = (IGamePlayerDynamic) gamePlayerProxyDynamic.getProxy();
        proxy.killBoss();
    }


}
