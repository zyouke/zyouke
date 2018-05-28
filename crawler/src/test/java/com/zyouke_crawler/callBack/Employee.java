package com.zyouke_crawler.callBack;

public class Employee {

    CallBack callBack;
    public Employee(CallBack callBack){
        this.callBack=callBack;
    }
    public void doWork(){
        System.out.println("玩命干活中....");
        callBack.doEvent();
    }
}
