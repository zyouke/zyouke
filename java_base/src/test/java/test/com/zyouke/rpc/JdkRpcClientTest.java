package test.com.zyouke.rpc;

import com.zyouke.bean.Order;
import org.junit.Test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;

/**
 * @Author: zhoujun
 */
public class JdkRpcClientTest {

    @Test
    public void jdkRpcClientTest(){
        Order order = new Order();
        order.setId(123L);
        order.setMoney(3.2D);
        order.setUserName("aaaaa");
        order.setCreateTime(new Date());
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1",8080));
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeUTF("com.zyouke.service.IOrderService");
            out.writeUTF("addOrder");
            out.writeObject(IOrderService.class.getMethod("addOrder",Order.class).getParameterTypes());
            out.writeObject(order);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            System.out.println("------------" + in.readObject());
            socket.close();
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
