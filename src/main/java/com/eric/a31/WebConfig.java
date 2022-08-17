package com.eric.a31;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.List;
import java.util.Map;

/**
 * 1. ExceptionHandlerExceptionResolver 初始化时会解析 @ControllerAdvice 中的 @ExceptionHandler 方法
 * 2. ExceptionHandlerExceptionResolver 会以类为单位，在该类首次处理异常时，解析此类的 @ExceptionHandler 方法
 * 3. 以上两种 @ExceptionHandler 的解析结果都会缓存来避免重复解析
 */
@Configuration
public class WebConfig
{
    @ControllerAdvice
    static class MyControllerAdvice
    {
        @ExceptionHandler
        @ResponseBody
        public Map<String, Object> handle(Exception e)
        {
            return Map.of("error", e.getMessage());
        }
    }

    @Bean
    public ExceptionHandlerExceptionResolver resolver()
    {
        ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
        resolver.setMessageConverters(List.of(new MappingJackson2HttpMessageConverter()));
        // resolver.afterPropertiesSet();
        return resolver;
    }
}
