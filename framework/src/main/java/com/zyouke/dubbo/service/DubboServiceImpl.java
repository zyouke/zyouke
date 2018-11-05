package com.zyouke.dubbo.service;

import com.zyouke.bean.DubboBean;
import com.zyouke.dubbo.main.DubboMain;
import com.zyouke.service.IDubboService;
import com.zyouke.utils.RandomUtil;
import com.zyouke.utils.ThreadUtil;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

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
        long start = System.currentTimeMillis();
        counter.getAndIncrement();
        String threadName = ThreadUtil.getThreadName();
        String requestTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss:ssss");
        int randomNumber = RandomUtil.getRandomNumber(1, 100);
        ThreadUtil.sleep(randomNumber);
        if (randomNumber % 3 == 0){
            ClassPathXmlApplicationContext contextOne = DubboMain.contextOne;
            if (contextOne != null) {
                contextOne.stop();
                contextOne.close();
                contextOne = null;
            }
        }
        System.out.println("请求执行时间：" + (System.currentTimeMillis() - start));
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
