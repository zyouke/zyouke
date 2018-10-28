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
    private AtomicInteger counter = new AtomicInteger(1);
    /**
     * 获取当前执行的线程
     * @return
     */
    @Override
    public String getExecuteThread() {
        counter.getAndIncrement();
        String threadName = ThreadUtil.getThreadName();
        String requestTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss:ssss");
        int randomNumber = RandomUtil.getRandomNumber(1, 100);
        ThreadUtil.sleep(randomNumber);
        System.out.println("---------------------------");
        return new DubboBean(requestTime,randomNumber,threadName).toStringNotRequestCount();
    }

    /**
     * 获取请求总数
     *
     * @return
     */
    @Override
    public String getRequestCount() {
        return new DubboBean(ThreadUtil.getThreadName(),counter.get()).toStringNotRequestTimeExecuteTime();
    }

    /**
     * 重置計數器
     */
    @Override
    public void resetCounter() {
        this.counter = new AtomicInteger(1);
    }
}
