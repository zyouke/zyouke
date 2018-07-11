package com.zyouke.nio;



public class TimeClient {

    public static void main(String[] args) throws Exception {
	new Thread(new TimeClientHandler()).start();
    }
}
