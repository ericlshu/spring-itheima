package com.eric.a30;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * a. ExceptionHandlerExceptionResolver 能够重用参数解析器、返回值处理器，实现组件重用
 * b. 能够支持嵌套异常
 */
@Slf4j
public class A30
{
    public static void main(String[] args) throws NoSuchMethodException
    {
        ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
        resolver.setMessageConverters(List.of(new MappingJackson2HttpMessageConverter()));
        resolver.afterPropertiesSet();

        // 1.测试 json
        HandlerMethod handlerMethod1 = new HandlerMethod(new Controller1(), Controller1.class.getMethod("foo"));
        Exception exception1 = new ArithmeticException("除零异常");
        MockHttpServletResponse response1 = new MockHttpServletResponse();
        resolver.resolveException(new MockHttpServletRequest(), response1, handlerMethod1, exception1);
        log.info(new String(response1.getContentAsByteArray(), StandardCharsets.UTF_8));

        log.debug("<============================================================>");

        // 2.测试 mav
        HandlerMethod handlerMethod2 = new HandlerMethod(new Controller2(), Controller2.class.getMethod("foo"));
        Exception exception2 = new ArithmeticException("除零异常");
        MockHttpServletResponse response2 = new MockHttpServletResponse();
        ModelAndView mav = resolver.resolveException(new MockHttpServletRequest(), response2, handlerMethod2, exception2);
        assert mav != null;
        log.info("Model Name : {}", mav.getModel());
        log.info("View  Name : {}", mav.getViewName());

        log.debug("<============================================================>");

        // 3.测试嵌套异常
        HandlerMethod handlerMethod3 = new HandlerMethod(new Controller3(), Controller3.class.getMethod("foo"));
        Exception exception3 = new Exception("Exception", new RuntimeException("Runtime", new IOException("IO")));
        MockHttpServletResponse response3 = new MockHttpServletResponse();
        resolver.resolveException(new MockHttpServletRequest(), response3, handlerMethod3, exception3);
        log.info(new String(response3.getContentAsByteArray(), StandardCharsets.UTF_8));

        log.debug("<============================================================>");

        // 4.测试异常处理方法参数解析
        HandlerMethod handlerMethod4 = new HandlerMethod(new Controller4(), Controller4.class.getMethod("foo"));
        Exception exception4 = new Exception("e1");
        MockHttpServletResponse response4 = new MockHttpServletResponse();
        resolver.resolveException(new MockHttpServletRequest(), response4, handlerMethod4, exception4);
        log.info(new String(response4.getContentAsByteArray(), StandardCharsets.UTF_8));
    }

    static class Controller1
    {
        public void foo()
        {
        }

        @ExceptionHandler
        @ResponseBody
        public Map<String, Object> handle(ArithmeticException e)
        {
            return Map.of("error", e.getMessage());
        }
    }

    static class Controller2
    {
        public void foo()
        {
        }

        @ExceptionHandler
        public ModelAndView handle(ArithmeticException e)
        {
            return new ModelAndView("test2", Map.of("error", e.getMessage()));
        }
    }

    static class Controller3
    {
        public void foo()
        {
        }

        @ExceptionHandler
        @ResponseBody
        public Map<String, Object> handle(IOException e3)
        {
            return Map.of("error", e3.getMessage());
        }
    }

    static class Controller4
    {
        public void foo()
        {
        }

        @ExceptionHandler
        @ResponseBody
        public Map<String, Object> handler(Exception e, HttpServletRequest request)
        {
            log.info(request.toString());
            return Map.of("error", e.getMessage());
        }
    }
}
