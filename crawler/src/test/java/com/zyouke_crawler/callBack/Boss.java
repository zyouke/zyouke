package com.zyouke_crawler.callBack;

public class Boss implements CallBack {

    @Override
    public void doEvent() {
	System.out.println("打电话给老板，告知已经完成工作了1");
    }

}
