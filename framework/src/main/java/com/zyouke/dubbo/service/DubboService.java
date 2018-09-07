package com.zyouke.dubbo.service;

import com.zyouke.bean.Area;
import com.zyouke.service.IDubboService;
import com.zyouke.utils.RandomUtil;
import com.zyouke.utils.ThreadUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zhoujun
 */
@Service("dubboService")
public class DubboService implements IDubboService{

    AtomicInteger atomicInteger = new AtomicInteger();
    /**
     * 获取当前执行的线程
     * @return
     */
    @Override
    public String getExecuteThread() {
        int randomNumber = RandomUtil.getRandomNumber(1, 30);
        ThreadUtil.sleep(randomNumber);
        return ThreadUtil.getThreadName();
    }
}
