package com.eric.a32;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistrarBeanPostProcessor;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import java.util.List;

/**
 * 1. 因为内嵌了 Tomcat 容器，因此可以配置 Tomcat 的错误页面，Filter 与 错误页面之间是通过请求转发跳转的，可以在这里做手脚
 * 2. 先通过 ErrorPageRegistrarBeanPostProcessor 这个后处理器配置错误页面地址，默认为 `/error` 也可以通过 `${server.error.path}` 进行配置
 * 3. 当 Filter 发生异常时，不会走 Spring 流程，但会走 Tomcat 的错误处理，于是就希望转发至 `/error` 这个地址
 * -> 当然，如果没有 @ExceptionHandler，那么最终也会走到 Tomcat 的错误处理
 * 4. Spring Boot 又提供了一个 BasicErrorController，它就是一个标准 @Controller，@RequestMapping 配置为 `/error`，所以处理异常的职责就又回到了 Spring
 * 5. 异常信息由于会被 Tomcat 放入 request 作用域，因此 BasicErrorController 里也能获取到
 * 6. 具体异常信息会由 DefaultErrorAttributes 封装好
 * 7. BasicErrorController 通过 Accept 头判断需要生成哪种 MediaType 的响应
 * -> 如果要的不是 text/html，走 MessageConverter 流程
 * -> 如果需要 text/html，走 mvc 流程，此时又分两种情况
 * -->> 配置了 ErrorViewResolver，根据状态码去找 View
 * -->> 没配置或没找到，用 BeanNameViewResolver 根据一个固定为 error 的名字找到 View，即所谓的 WhitelabelErrorView
 */
@Slf4j
@Configuration
public class WebConfig
{
    @Bean
    public TomcatServletWebServerFactory servletWebServerFactory()
    {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet()
    {
        return new DispatcherServlet();
    }

    @Bean
    public DispatcherServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet)
    {
        DispatcherServletRegistrationBean registrationBean = new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    @Bean // @RequestMapping
    public RequestMappingHandlerMapping requestMappingHandlerMapping()
    {
        return new RequestMappingHandlerMapping();
    }

    @Bean // 注意默认的 RequestMappingHandlerAdapter 不会带 jackson 转换器
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter()
    {
        RequestMappingHandlerAdapter handlerAdapter = new RequestMappingHandlerAdapter();
        handlerAdapter.setMessageConverters(List.of(new MappingJackson2HttpMessageConverter()));
        return handlerAdapter;
    }

    /**
     * ⬅️修改了 Tomcat 服务器默认错误地址, 出错时使用请求转发方式跳转到error地址
     */
    @Bean
    public ErrorPageRegistrar errorPageRegistrar()
    {
        return webServerFactory -> webServerFactory.addErrorPages(new ErrorPage("/error"));
    }

    /**
     * ⬅️TomcatServletWebServerFactory 初始化前用它增强, 注册所有 ErrorPageRegistrar
     */
    @Bean
    public ErrorPageRegistrarBeanPostProcessor errorPageRegistrarBeanPostProcessor()
    {
        return new ErrorPageRegistrarBeanPostProcessor();
    }

    @Controller
    public static class MyController
    {
        @RequestMapping("test")
        public ModelAndView test()
        {
            int i = 1 / 0;
            return null;
        }

        /*@RequestMapping("/error")
        @ResponseBody
        public Map<String, Object> error(HttpServletRequest request)
        {
            Throwable e = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
            return Map.of("error", e.getMessage());
        }*/
    }

    /**
     * ⬅️ErrorProperties 封装环境键值, ErrorAttributes 控制有哪些错误信息
     */
    @Bean
    public BasicErrorController basicErrorController()
    {
        ErrorProperties errorProperties = new ErrorProperties();
        errorProperties.setIncludeException(true);
        return new BasicErrorController(new DefaultErrorAttributes(), errorProperties);
    }

    /**
     * ⬅️名称为 error 的视图, 作为 BasicErrorController 的 text/html 响应结果
     */
    @Bean
    public View error()
    {
        return (model, request, response) ->
        {
            log.info("model : {}", model);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("""
                    <h3>服务器内部错误</h3>
                    """);
        };
    }

    /**
     * ⬅️收集容器中所有 View 对象, bean 的名字作为视图名
     */
    @Bean
    public ViewResolver viewResolver()
    {
        return new BeanNameViewResolver();
    }
}
