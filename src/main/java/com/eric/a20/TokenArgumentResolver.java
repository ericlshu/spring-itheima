package com.eric.a20;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 自定义带token注解的参数解析器
 */
public class TokenArgumentResolver implements HandlerMethodArgumentResolver
{
    /**
     * 是否支持某个参数
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter)
    {
        return parameter.getParameterAnnotation(Token.class) != null;
    }

    /**
     * 解析参数
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception
    {
        return webRequest.getHeader("token");
    }
}
