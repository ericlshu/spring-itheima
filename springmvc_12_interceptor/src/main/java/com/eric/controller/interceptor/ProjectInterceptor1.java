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
public class ProjectInterceptor1 implements HandlerInterceptor
{
    /**
     * 原始方法调用前执行的内容
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        System.out.println("com.eric.controller.interceptor.ProjectInterceptor1.preHandle...");

        // 使用request对象可以获取请求数据中的内容
        // String userAgent = request.getHeader("User-Agent");
        // System.out.println("userAgent = " + userAgent);

        // 使用handler参数，可以获取方法的相关信息
        // HandlerMethod handlerMethod = (HandlerMethod) handler;
        // String methodName = handlerMethod.getMethod().getName();
        // System.out.println("methodName = " + methodName);

        // 如果返回true,则代表放行，会执行原始Controller类中要请求的方法，
        // 如果返回false，则代表拦截，后面的就不会再执行了。
        return true;
    }

    /**
     * 原始方法调用后执行的内容
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        System.out.println("com.eric.controller.interceptor.ProjectInterceptor1.postHandle...");
    }

    /**
     * 原始方法调用完成后执行的内容
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
        System.out.println("com.eric.controller.interceptor.ProjectInterceptor1.afterCompletion...");
    }
}
