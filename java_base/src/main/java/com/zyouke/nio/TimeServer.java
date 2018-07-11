package com.zyouke.nio;


public class TimeServer {
    public static void main(String[] args) {
	new Thread(new TimeServerHandler(8080)).start();
    }
}
