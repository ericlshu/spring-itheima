package com.eric.a26;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * RequestMappingHandlerAdapter 初始化时会解析 @ControllerAdvice 中的 @ModelAttribute 方法
 * --> 加在@ControllerAdvice中的方法上时针对所有控制器方法均会生效
 * --> 加在@Controller中的方法上时尽对当前控制器方法生效
 */
@Slf4j
@Configuration
public class WebConfig
{
    @ControllerAdvice
    static class MyControllerAdvice
    {
        @ModelAttribute("a")
        public String aa()
        {
            log.debug("@ModelAttribute in @ControllerAdvice");
            return "aa";
        }
    }

    @Controller
    static class Controller1
    {
        @ModelAttribute("b")
        public String bb()
        {
            log.debug("@ModelAttribute in @Controller");
            return "bb";
        }

        @ResponseStatus(HttpStatus.OK)
        public ModelAndView foo(@ModelAttribute("u") User user)
        {
            log.debug("user in foo() : {}", user);
            return null;
        }
    }

    @Data
    static class User
    {
        private String name;
    }
}
