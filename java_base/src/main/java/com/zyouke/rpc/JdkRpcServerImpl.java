package com.zyouke.rpc;

import com.zyouke.bean.Order;
import com.zyouke.rpc.impl.OrderServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: zhoujun
 */
public class JdkRpcServerImpl implements JdkRpcServer{
    /**
     * 启动
     */
    @Override
    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(8080));
            System.out.println("start server ...........");
            while (true){
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream in = new ObjectInputStream(inputStream);
                String serviceName = in.readUTF();
                String methodName = in.readUTF();
                Class<?>[] parameterTypes = (Class<?>[]) in.readObject();
                Object arg = in.readObject();
                System.out.println("获取客户端请求的接口:" + serviceName + "获取客户端请求的方法:" + methodName);
                Class serviceClass = null;
                if ("com.zyouke.service.IOrderService".equals(serviceName)){
                    serviceClass = OrderServiceImpl.class;
                }
                Method method = serviceClass.getMethod(methodName, parameterTypes);
                Object result  = method.invoke(serviceClass.newInstance(), arg);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(result);
                socket.close();
                in.close();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        JdkRpcServerImpl jdkRpcServer = new JdkRpcServerImpl();
        jdkRpcServer.start();
    }
}
