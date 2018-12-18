package com.okay.usersvr;

import com.noriental.usersvr.bean.request.RequestLong;
import com.noriental.usersvr.bean.user.domain.Student;
import com.noriental.usersvr.service.okuser.student.StudentService;
import com.noriental.validate.bean.CommonResponse;
import com.taobao.stresstester.StressTestUtils;
import com.taobao.stresstester.core.StressResult;
import com.taobao.stresstester.core.StressTask;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhoujun
 */
public class FindStudentBySystemIdTest {
    public static void main(String[] args) {
        //加载dubbo配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext-consumer-user.xml"});
        //获取server
        final StudentService studentService = (StudentService) context.getBean("studentService");

        /**
         * StressTestUtils.test(int concurrencyLevel, int totalRequests, StressTask stressTask, int warmUpTime);
         * concurrencyLevel  总并发次数
         * totalRequests 总请求次数， 单个线程请求次数= totalRequests/concurrencyLevel
         * stressTask  并发主体
         * warmUpTime  初始绿灯测试stressTask次数
         */
        StressResult stressResult = StressTestUtils.test(50, 100, new StressTask() {
            public Object doTask() throws Exception {
                CommonResponse<Student> commonResponse = studentService.findStudentBySystemId(new RequestLong(81951061835L));
                return null;
            }
        }, 0);
        //数据格式化
        String str = StressTestUtils.format(stressResult);
        System.out.println(str);
    }
}
