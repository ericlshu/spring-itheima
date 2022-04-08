package com.eric.interceptor;

import com.eric.service.IpCountService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-08 23:09
 * @since jdk-11.0.14
 */
public class IpCountInterceptor implements HandlerInterceptor
{
    @Resource
    private IpCountService ipCountService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        ipCountService.count();
        return true;
    }
}
