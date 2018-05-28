package com.zyouke.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * NginxController.java
 * 测试nginx
 * @author zyouke
 * @create 2018/01/06 21:17
 */
@Controller
@RequestMapping("/nginx")
public class NginxController {
    @RequestMapping("/nginxWeight.do")
    @ResponseBody
    public String nginxWeight(HttpServletRequest request){
        System.out.println("-------------------" + request.getLocalPort()+"sessionId : "+request.getSession().getId());
        return JSON.toJSONString(request.getLocalPort());
    }

    @RequestMapping(value = "/nginxCache.do",method = RequestMethod.GET)
    @ResponseBody
    public String nginxCache(HttpServletRequest request){
        System.out.println("接受的参数:" + request.getParameter("uuid"));
        return JSON.toJSONString(request.getLocalPort());
    }
}
