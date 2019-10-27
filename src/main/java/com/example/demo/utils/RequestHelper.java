package com.example.demo.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author WeLong
 * @create 2019/10/23 10:33
 */
public class RequestHelper {

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
    public static String getRequestUrl() {
        String methods = getRequest().getMethod();
        return methods+" "+getRequest().getServletPath();
    }
}
