package com.zyouke.dubbo.service;

import com.zyouke.bean.Area;
import com.zyouke.bean.DubboBean;
import com.zyouke.service.IDubboService;
import com.zyouke.utils.RandomUtil;
import com.zyouke.utils.ThreadUtil;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zhoujun
 */
@Service("dubboService")
public class DubboServiceImpl implements IDubboService{
    private AtomicInteger atomicInteger_20880 = new AtomicInteger(0);
    private AtomicInteger atomicInteger_20881 = new AtomicInteger(0);
    /**
     * 获取当前执行的线程
     * @return
     */
    @Override
    public String getExecuteThread() {
        System.out.println("----------" + this.toString());
        int requestCount = 0;
        String threadName = ThreadUtil.getThreadName();
        if (threadName.startsWith("DubboServerHandler-122.114.90.68:20881")){
            requestCount = atomicInteger_20881.getAndIncrement();
        }else if (threadName.startsWith("DubboServerHandler-122.114.90.68:20880")){
            requestCount = atomicInteger_20880.getAndIncrement();
        }
        String requestTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss:ssss");
        int randomNumber = RandomUtil.getRandomNumber(1, 100);
        ThreadUtil.sleep(randomNumber);
        return new DubboBean(requestTime,randomNumber,threadName,requestCount).toString();
    }
}
