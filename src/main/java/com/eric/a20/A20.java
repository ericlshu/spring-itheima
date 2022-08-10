package com.eric.a20;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

/**
 * a. DispatcherServlet 是在第一次被访问时执行初始化, 也可以通过配置修改为 Tomcat 启动后就初始化
 * b. 在初始化时会从 Spring 容器中找一些 Web 需要的组件, 如 HandlerMapping、HandlerAdapter 等，并逐一调用它们的初始化
 * c. RequestMappingHandlerMapping 初始化时，会收集所有 @RequestMapping 映射信息，封装为 Map，其中
 * -> key 是 RequestMappingInfo 类型，包括请求路径、请求方法等信息
 * -> value 是 HandlerMethod 类型，包括控制器方法对象、控制器对象
 * -> 有了这个 Map，就可以在请求到达时，快速完成映射，找到 HandlerMethod 并与匹配的拦截器一起返回给 DispatcherServlet
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
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/test1");
        HandlerExecutionChain chain = handlerMapping.getHandler(request);
        log.info("chain : {}", chain);
    }
}
