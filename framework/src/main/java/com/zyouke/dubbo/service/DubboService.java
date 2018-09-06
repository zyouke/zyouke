package com.zyouke.dubbo.service;

import com.zyouke.bean.Area;
import com.zyouke.service.IDubboService;
import com.zyouke.utils.ThreadUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhoujun
 */
@Service("dubboService")
public class DubboService implements IDubboService{

    /**
     * 获取当前执行的线程
     * @return
     */
    @Override
    public String getExecuteThread() {
        return ThreadUtil.getThreadName();
    }
}
