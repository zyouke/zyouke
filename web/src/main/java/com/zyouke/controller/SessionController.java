package com.zyouke.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/session")
public class SessionController {
    private static final Logger LOGGER = Logger.getLogger(SessionController.class);

    @RequestMapping(value = "/getSessionId.do")
    @ResponseBody
    public String getSessionId(HttpServletRequest request, HttpServletResponse response) {
        return request.getSession().getId();
    }
    @RequestMapping(value = "/setSession.do")
    public void setSession(HttpServletRequest request, HttpServletResponse response) {
        String value = request.getParameter("value");
        request.getSession().setAttribute("test", value);
        LOGGER.info("sessionid=======" + request.getSession().getId());
    }

    @RequestMapping(value = "/getSession.do")
    public void getInterestPro(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        LOGGER.info("------" + request.getSession().getAttribute(name));
    }

    @RequestMapping(value = "/removeSession.do")
    public void removeSession(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        request.getSession().removeAttribute(name);
    }
}
