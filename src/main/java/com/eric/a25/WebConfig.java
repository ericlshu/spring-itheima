package com.eric.a25;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Configuration
public class WebConfig
{
    @Controller
    static class Controller1
    {
        @ResponseStatus(HttpStatus.OK)
        public ModelAndView foo(User user)
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
