package com.eric.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-10 11:52
 */
public class MyInterceptor1 implements HandlerInterceptor {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * 在目标方法执行之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        LOGGER.debug("preHandle ......");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * 在目标方法执行之后 视图对象返回之前执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        LOGGER.debug("postHandle ......");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 在整个流程执行完毕后执行
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
        LOGGER.debug("afterCompletion ......");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
