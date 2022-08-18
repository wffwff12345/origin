package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.MyInterceptor;
@Configuration
public class MyWebConfig implements WebMvcConfigurer{
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login").excludePathPatterns("/app/user/login").excludePathPatterns("/app/user/register");
    }
    
}
