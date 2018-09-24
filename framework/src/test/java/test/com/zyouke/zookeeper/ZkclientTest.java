package test.com.zyouke.zookeeper;

import com.zyouke.utils.RandomUtil;
import com.zyouke.utils.ThreadUtil;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @Author: zhoujun
 * 使用Zkclient连接zk
 */
public class ZkclientTest {

    //private ZkClient zkClient = new ZkClient("122.114.90.68:2181");
    @Test
    public void zkclientSimpleTest(){
        long s = System.currentTimeMillis();
        ZkClient zkClient = new ZkClient("122.114.90.68:2181");
        System.out.println(System.currentTimeMillis() - s);
        /*zkClient.createPersistent("/zyouke/test",true);
        ThreadUtil.sleep(1000);
        zkClient.writeData("/zyouke/test",RandomUtil.getRandomString());
        Object readData = zkClient.readData("/zyouke/test");
        System.out.println(readData);
        zkClient.close();*/
    }

    @Test
    public void subscribeChildChangesTest(){/*
        zkClient.subscribeChildChanges("/zyouke", new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println("当前监听的path :" +parentPath);
                System.out.println("当前监听到变化的childs :" +currentChilds);
            }
        });
        for (int i = 0; i < 5; i++) {
            zkClient.createPersistent("/zyouke/test"+i,true);
            ThreadUtil.sleep(500);
        }
        ThreadUtil.sleep(5000);
    */}

    //@Before
    public void before(){/*
        zkClient.deleteRecursive("/zyouke");
    */}
}
