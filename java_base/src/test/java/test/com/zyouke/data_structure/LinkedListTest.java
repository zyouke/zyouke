package test.com.zyouke.data_structure;

import com.zyouke.data_structure.LinkedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: zhoujun
 */
public class LinkedListTest {

    private LinkedList linkedList;
    @Before
    public void before(){
        System.out.println("----------------------before开始-----------------");
        linkedList = new LinkedList();
        for (int i = 0; i < 10; i++) {
            linkedList.offer(i);
        }
        System.out.println(linkedList.print());
        System.out.println("----------------------before结束-----------------");
    }
    @Test
    public void offerTest(){
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < 10; i++) {
            linkedList.offer(i);
        }
        System.out.println(linkedList.print());
    }

    @Test
    public void pollTest(){
        for (int i = 0; i < 5; i++) {
            linkedList.poll();
        }
    }

    @After
    public void after(){
        System.out.println("after-----------------------" + linkedList.print());
    }
}
