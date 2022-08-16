package com.eric.a29;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Configuration
public class WebConfig
{
    @ControllerAdvice
    static class MyControllerAdvice implements ResponseBodyAdvice<Object>
    {
        // 满足条件才转换
        public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType)
        {
            return returnType.getMethodAnnotation(ResponseBody.class) != null ||
                    AnnotationUtils.findAnnotation(returnType.getContainingClass(), ResponseBody.class) != null;
        }

        // 将 User 或其它类型统一为 Result 类型
        public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                      Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response)
        {
            if (body instanceof Result)
            {
                return body;
            }
            return Result.ok(body);
        }
    }

    // @Controller
    // @ResponseBody // returnType.getContainingClass().isAnnotationPresent(ResponseBody.class)
    @RestController // AnnotationUtils.findAnnotation(returnType.getContainingClass(), ResponseBody.class) != null
    public static class MyController
    {
        // @ResponseBody // returnType.getMethodAnnotation(ResponseBody.class) != null
        public User user()
        {
            return new User("王五", 18);
        }
    }

    @Data
    @AllArgsConstructor
    public static class User
    {
        private String name;
        private int age;
    }
}
