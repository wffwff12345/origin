package com.example.demo.interceptor;

import java.io.Console;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.cloud.context.utils.StringUtils;
import com.example.demo.commontool.People;
import com.example.demo.commontool.WmThreadLocal;

public class MyInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        WmThreadLocal.remove();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String data = request.getHeader("token");
        System.out.println("token");
        System.out.println(data);
        System.out.println("token");
        if (data != null) {
            People pelple = new People(Integer.parseInt(data));
            WmThreadLocal.setUser(pelple);
        }
        return true;

    }
}
