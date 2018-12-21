package com.okay.usersvr;

import com.noriental.usersvr.bean.request.RequestLong;
import com.noriental.usersvr.bean.request.UserBaseMapRequest;
import com.noriental.usersvr.bean.user.domain.Student;
import com.noriental.usersvr.service.okuser.UserService;
import com.noriental.usersvr.service.okuser.student.StudentService;
import com.noriental.validate.bean.CommonResponse;
import com.taobao.stresstester.StressTestUtils;
import com.taobao.stresstester.core.StressResult;
import com.taobao.stresstester.core.StressTask;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhoujun
 */
public class FindStudentsTest {
    public static void main(String[] args) {
        //加载dubbo配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext-consumer-user.xml"});
        //获取server
        final UserService userService = (UserService) context.getBean("userService");

        /**
         * StressTestUtils.test(int concurrencyLevel, int totalRequests, StressTask stressTask, int warmUpTime);
         * concurrencyLevel  总并发次数
         * totalRequests 总请求次数， 单个线程请求次数= totalRequests/concurrencyLevel
         * stressTask  并发主体
         * warmUpTime  初始绿灯测试stressTask次数
         */
        StressResult stressResult = StressTestUtils.test(500, 500, new StressTask() {
            public Object doTask() throws Exception {
                UserBaseMapRequest baseMapRequest = new UserBaseMapRequest();
                Map params = new HashMap();
                List<Long> list = new ArrayList<Long>();
                list.add(12721L);
                params.put("klassIds",list);
                baseMapRequest.setParams(params);
                baseMapRequest.setReqId("abc123456789");
                userService.findStudent(baseMapRequest);
                return null;
            }
        }, 0);
        //数据格式化
        String str = StressTestUtils.format(stressResult);
        System.out.println(str);

    }
}
