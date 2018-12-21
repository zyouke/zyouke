package com.okay.usercenter;

import com.noriental.usersvr.bean.request.RequestLong;
import com.noriental.usersvr.bean.user.domain.Student;
import com.noriental.usersvr.service.okuser.student.StudentService;
import com.noriental.utils.json.JsonUtil;
import com.noriental.utils.servlet.HttpUtils;
import com.noriental.validate.bean.CommonResponse;
import com.taobao.stresstester.StressTestUtils;
import com.taobao.stresstester.core.StressResult;
import com.taobao.stresstester.core.StressTask;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhoujun
 */
public class FindSubjectIdByStageTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext-consumer-user.xml"});
        StudentService studentService = (StudentService) context.getBean("studentService");
        StressResult stressResult = StressTestUtils.test(10, 500, new StressTask() {
            public Object doTask() throws Exception {
                Map<String,String> headerMap = new HashMap<String,String>();
                headerMap.put("requestid","abc123");
                headerMap.put("Content-Type","application/json;charset=utf-8");
                Map<String, Object> entityMap = new HashMap<String, Object>(1);
                entityMap.put("entity",2);
                HttpUtils.doPost("http://127.0.0.1:28081/usercenter/user/findSubjectIdByStage", JsonUtil.obj2Json(entityMap),5000,5000,headerMap);
                return null;
            }
        }, 0);
        //数据格式化
        String str = StressTestUtils.format(stressResult);
        System.out.println(str);
    }
}
