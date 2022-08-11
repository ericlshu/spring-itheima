package com.eric.a24;

import com.eric.a23.MyDateFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@Configuration
public class WebConfig
{
    @ControllerAdvice
    static class MyControllerAdvice
    {
        // 加在@ControllerAdvice中的@InitBinder对所有控制器都生效的自定义类型转换器
        @InitBinder
        public void binder3(WebDataBinder webDataBinder)
        {
            webDataBinder.addCustomFormatter(new MyDateFormatter("binder3 转换器"));
        }
    }

    @Controller
    static class Controller1
    {
        // 加在@Controller中的@InitBinder仅对当前控制器生效
        @InitBinder
        public void binder1(WebDataBinder webDataBinder)
        {
            webDataBinder.addCustomFormatter(new MyDateFormatter("binder-1 转换器"));
        }

        public void foo()
        {
        }
    }

    @Controller
    static class Controller2
    {
        @InitBinder
        public void binder21(WebDataBinder webDataBinder)
        {
            webDataBinder.addCustomFormatter(new MyDateFormatter("binder-2-1 转换器"));
        }

        @InitBinder
        public void binder22(WebDataBinder webDataBinder)
        {
            webDataBinder.addCustomFormatter(new MyDateFormatter("binder-2-2 转换器"));
        }

        public void bar()
        {
        }
    }
}
