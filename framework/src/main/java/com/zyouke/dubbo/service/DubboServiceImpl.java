package com.zyouke.dubbo.service;

import com.zyouke.bean.Area;
import com.zyouke.service.IDubboService;
import com.zyouke.utils.RandomUtil;
import com.zyouke.utils.ThreadUtil;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zhoujun
 */
@Service("dubboService")
public class DubboServiceImpl implements IDubboService{

    AtomicInteger atomicInteger = new AtomicInteger();
    /**
     * 获取当前执行的线程
     * @return
     */
    @Override
    public String getExecuteThread() {
        String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss:ssss");
        int randomNumber = RandomUtil.getRandomNumber(1, 100);
        ThreadUtil.sleep(randomNumber);
        return ThreadUtil.getThreadName() +"_requestTime"+ date + "_execute_time :" + randomNumber;
    }
}
