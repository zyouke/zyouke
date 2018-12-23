package com.zyouke.nio;

import com.taobao.stresstester.StressTestUtils;
import com.taobao.stresstester.core.StressResult;
import com.taobao.stresstester.core.StressTask;

/**
 * nio 并发测试
 */
public class ConcurrentNioTest {

    public static void main(String[] args){
        StressResult stressResult = StressTestUtils.test(10, 50, new StressTask() {
            @Override
            public Object doTask() {
                return null;
            }
        });
        //数据格式化
        String str = StressTestUtils.format(stressResult);
        System.out.println(str);
    }
}
