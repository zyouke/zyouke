package com.crawler.callBack;

public class TestMain {

    public static void main(String[] args) {
	 //创建控制器对象，将提供给他的回调对象传入
        Employee employee=new Employee(new Boss());
        //启动控制器对象运行
        employee.doWork();
    }

}
