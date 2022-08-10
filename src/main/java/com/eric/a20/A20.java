package com.eric.a20;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.Objects;

/**
 * a. DispatcherServlet 是在第一次被访问时执行初始化, 也可以通过配置修改为 Tomcat 启动后就初始化
 * b. 在初始化时会从 Spring 容器中找一些 Web 需要的组件, 如 HandlerMapping、HandlerAdapter 等，并逐一调用它们的初始化
 * c. RequestMappingHandlerMapping 初始化时，会收集所有 @RequestMapping 映射信息，封装为 Map，其中
 * -> key 是 RequestMappingInfo 类型，包括请求路径、请求方法等信息
 * -> value 是 HandlerMethod 类型，包括控制器方法对象、控制器对象
 * -> 有了这个 Map，就可以在请求到达时，快速完成映射，找到 HandlerMethod 并与匹配的拦截器一起返回给 DispatcherServlet
 * d. RequestMappingHandlerAdapter 初始化时，会准备 HandlerMethod 调用时需要的各个组件，如：
 * -> HandlerMethodArgumentResolver 解析控制器方法参数
 * -> HandlerMethodReturnValueHandler 处理控制器方法返回值
 */
@Slf4j
public class A20
{
    public static void main(String[] args) throws Exception
    {
        // 支持Java配置类方式并内嵌web容器的ApplicationContext
        AnnotationConfigServletWebServerApplicationContext context =
                new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);

        // 作用 : 解析 @RequestMapping 以及派生注解，生成路径与控制器方法的映射关系, 在初始化时就生成
        RequestMappingHandlerMapping handlerMapping = context.getBean(RequestMappingHandlerMapping.class);

        // 获取映射结果
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        handlerMethods.forEach((key, value) -> log.info(key + " = " + value));

        // 请求来了，获取控制器方法  返回处理器执行链对象
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/test2");
        request.setParameter("name", "张三");
        HandlerExecutionChain chain = handlerMapping.getHandler(request);
        log.info("chain : {}", chain);

        log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        // HandlerAdapter 作用: 调用控制器方法
        MockHttpServletResponse response = new MockHttpServletResponse();
        MyRequestMappingHandlerAdapter handlerAdapter = context.getBean(MyRequestMappingHandlerAdapter.class);
        assert chain != null;
        handlerAdapter.invokeHandlerMethod(request, response, (HandlerMethod) chain.getHandler());

        log.warn(">>>>>>>>>>>>>>>>>>>> 所有参数解析器 <<<<<<<<<<<<<<<<<<<<");
        for (HandlerMethodArgumentResolver resolver : Objects.requireNonNull(handlerAdapter.getArgumentResolvers()))
        {
            log.debug("resolver {} ", resolver);
        }

        log.warn(">>>>>>>>>>>>>>>>>>>> 所有返回值解析器 <<<<<<<<<<<<<<<<<<<<");
        for (HandlerMethodReturnValueHandler handler : Objects.requireNonNull(handlerAdapter.getReturnValueHandlers()))
        {
            log.debug("handler {} ", handler);
        }
    }
}
