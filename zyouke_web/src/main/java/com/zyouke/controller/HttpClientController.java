package com.zyouke.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * HttpClient测试类
 * @ClassName: HttpClientController 
 * @author 周俊
 * @date 2017年10月13日 下午1:49:43
 */
@Controller
@RequestMapping("/httpClient")
public class HttpClientController {


    @RequestMapping(value = "/post.do")
    @ResponseBody
    public void post(HttpServletRequest request){
	    System.out.println("---------------------");
    }
}
