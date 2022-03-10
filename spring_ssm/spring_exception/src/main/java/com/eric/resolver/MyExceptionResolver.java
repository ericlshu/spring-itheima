package com.eric.resolver;

import com.eric.exception.MyException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-10 22:14
 */
public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    {
        ModelAndView modelAndView = new ModelAndView();

        System.out.println("ex = " + ex);

        if (ex instanceof MyException)
        {
            modelAndView.addObject("info", "自定义异常");
        }
        else if (ex instanceof ClassCastException)
        {
            modelAndView.addObject("info", "类转换异常");
        }
        modelAndView.setViewName("error");

        return modelAndView;
    }
}
