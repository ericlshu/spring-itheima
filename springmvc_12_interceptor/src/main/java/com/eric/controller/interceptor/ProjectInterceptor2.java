package com.eric.controller.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description :
 *
 * @author Eric SHU
 */
@Component
public class ProjectInterceptor2 implements HandlerInterceptor
{
    /**
     * 原始方法调用前执行的内容
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        System.out.println("com.eric.controller.interceptor.ProjectInterceptor2.preHandle...");
        return true;
    }

    /**
     * 原始方法调用后执行的内容
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        System.out.println("com.eric.controller.interceptor.ProjectInterceptor2.postHandle...");
    }

    /**
     * 原始方法调用完成后执行的内容
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
        System.out.println("com.eric.controller.interceptor.ProjectInterceptor2.afterCompletion...");
    }
}
