package test.com.zyouke.data_structure;

import com.zyouke.data_structure.LinkedOneWay;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: zhoujun
 */
public class LinkedOneWayTest {

    private LinkedOneWay linkedOneWay;
    @Before
    public void before(){
        System.out.println("----------------------before开始-----------------");
        linkedOneWay = new LinkedOneWay();
        for (int i = 0; i < 10; i++) {
            linkedOneWay.offer(i);
        }
        System.out.println(linkedOneWay.print());
        System.out.println("----------------------before结束-----------------");
    }
    @Test
    public void offerTest(){

    }

    @Test
    public void pollTest(){
        for (int i = 0; i < 5; i++) {
            linkedOneWay.poll();
        }
    }

    @After
    public void after(){
        System.out.println("after-----------------------" + linkedOneWay.print());
    }
}
