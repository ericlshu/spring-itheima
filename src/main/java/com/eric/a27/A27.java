package com.eric.a27;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.method.annotation.*;
import org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.util.UrlPathHelper;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

/*
 * 目标: 解析控制器方法的返回值
 * 常见的返回值处理器
 * -> org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler@4c9e38
 * -> org.springframework.web.method.annotation.ModelMethodProcessor@5d1e09bc
 * -> org.springframework.web.servlet.mvc.method.annotation.ViewMethodReturnValueHandler@4bdc8b5d
 * -> org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler@3bcd426c
 * -> org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBodyReturnValueHandler@5f14a673
 * -> org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor@726a17c4
 * -> org.springframework.web.servlet.mvc.method.annotation.HttpHeadersReturnValueHandler@5dc3fcb7
 * -> org.springframework.web.servlet.mvc.method.annotation.CallableMethodReturnValueHandler@c4c0b41
 * -> org.springframework.web.servlet.mvc.method.annotation.DeferredResultMethodReturnValueHandler@76911385
 * -> org.springframework.web.servlet.mvc.method.annotation.AsyncTaskMethodReturnValueHandler@5467eea4
 * -> org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor@160396db
 * -> org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor@7a799159
 * -> org.springframework.web.servlet.mvc.method.annotation.ViewNameMethodReturnValueHandler@40ab8a8
 * -> org.springframework.web.method.annotation.MapMethodProcessor@6ff37443
 * -> org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor@65cc8228
 */
@Slf4j
@SuppressWarnings("all")
public class A27
{
    public static void main(String[] args) throws Exception
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);

        // test1() -> ModelAndViewMethodReturnValueHandler        -> 测试返回值类型为 ModelAndView
        // test2() -> ViewNameMethodReturnValueHandler            -> 测试返回值类型为 String 时, 把它当做视图名
        // test3() -> ServletModelAttributeMethodProcessor(false) -> 测试返回值添加了 @ModelAttribute 注解时, 此时需找到默认视图名
        // test4() -> ServletModelAttributeMethodProcessor(true)  -> 测试返回值不加 @ModelAttribute 注解且返回非简单类型时, 此时需找到默认视图名
        // test5() -> HttpEntityMethodProcessor                   -> 测试返回值类型为 ResponseEntity 时, 此时不走视图流程
        // test6() -> HttpHeadersReturnValueHandler               -> 测试返回值类型为 HttpHeaders 时, 此时不走视图流程
        // test7() -> RequestResponseBodyMethodProcessor          -> 测试返回值添加了 @ResponseBody 注解时, 此时不走视图流程
        test7(context);
    }

    private static void test7(AnnotationConfigApplicationContext context) throws Exception
    {
        Method method = Controller.class.getMethod("test7");
        Controller controller = new Controller();
        Object returnValue = method.invoke(controller); // 获取返回值

        HandlerMethod methodHandle = new HandlerMethod(controller, method);

        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = getReturnValueHandler();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        ServletWebRequest webRequest = new ServletWebRequest(request, response);
        if (composite.supportsReturnType(methodHandle.getReturnType()))
        { // 检查是否支持此类型的返回值
            composite.handleReturnValue(returnValue, methodHandle.getReturnType(), container, webRequest);
            log.info(String.valueOf(container.getModel()));
            log.info(container.getViewName());
            if (!container.isRequestHandled())
            {
                renderView(context, container, webRequest); // 渲染视图
            }
            else
            {
                for (String name : response.getHeaderNames())
                {
                    log.info(name + "=" + response.getHeader(name));
                }
                log.info(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
            }
        }
    }

    private static void test6(AnnotationConfigApplicationContext context) throws Exception
    {
        Method method = Controller.class.getMethod("test6");
        Controller controller = new Controller();
        Object returnValue = method.invoke(controller); // 获取返回值

        HandlerMethod methodHandle = new HandlerMethod(controller, method);

        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = getReturnValueHandler();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        ServletWebRequest webRequest = new ServletWebRequest(request, response);
        if (composite.supportsReturnType(methodHandle.getReturnType()))
        { // 检查是否支持此类型的返回值
            composite.handleReturnValue(returnValue, methodHandle.getReturnType(), container, webRequest);
            log.info(String.valueOf(container.getModel()));
            log.info(container.getViewName());
            if (!container.isRequestHandled())
            {
                renderView(context, container, webRequest); // 渲染视图
            }
            else
            {
                for (String name : response.getHeaderNames())
                {
                    log.info(name + "=" + response.getHeader(name));
                }
                log.info(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
            }
        }
    }

    private static void test5(AnnotationConfigApplicationContext context) throws Exception
    {
        Method method = Controller.class.getMethod("test5");
        Controller controller = new Controller();
        Object returnValue = method.invoke(controller); // 获取返回值

        HandlerMethod methodHandle = new HandlerMethod(controller, method);

        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = getReturnValueHandler();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        ServletWebRequest webRequest = new ServletWebRequest(request, response);
        if (composite.supportsReturnType(methodHandle.getReturnType()))
        { // 检查是否支持此类型的返回值
            composite.handleReturnValue(returnValue, methodHandle.getReturnType(), container, webRequest);
            log.info(String.valueOf(container.getModel()));
            log.info(container.getViewName());
            if (!container.isRequestHandled())
            {
                renderView(context, container, webRequest); // 渲染视图
            }
            else
            {
                log.info(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
            }
        }
    }

    private static void test4(AnnotationConfigApplicationContext context) throws Exception
    {
        Method method = Controller.class.getMethod("test4");
        Controller controller = new Controller();
        Object returnValue = method.invoke(controller); // 获取返回值

        HandlerMethod methodHandle = new HandlerMethod(controller, method);

        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = getReturnValueHandler();
        MockHttpServletRequest request = new MockHttpServletRequest();

        request.setRequestURI("/test4");
        UrlPathHelper.defaultInstance.resolveAndCacheLookupPath(request);

        ServletWebRequest webRequest = new ServletWebRequest(request, new MockHttpServletResponse());
        if (composite.supportsReturnType(methodHandle.getReturnType()))
        { // 检查是否支持此类型的返回值
            composite.handleReturnValue(returnValue, methodHandle.getReturnType(), container, webRequest);
            log.info(String.valueOf(container.getModel()));
            log.info(container.getViewName());
            renderView(context, container, webRequest); // 渲染视图
        }
    }

    private static void test3(AnnotationConfigApplicationContext context) throws Exception
    {
        Method method = Controller.class.getMethod("test3");
        Controller controller = new Controller();
        Object returnValue = method.invoke(controller); // 获取返回值

        HandlerMethod methodHandle = new HandlerMethod(controller, method);

        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = getReturnValueHandler();
        MockHttpServletRequest request = new MockHttpServletRequest();

        request.setRequestURI("/test3");
        UrlPathHelper.defaultInstance.resolveAndCacheLookupPath(request);
        ServletWebRequest webRequest = new ServletWebRequest(request, new MockHttpServletResponse());

        if (composite.supportsReturnType(methodHandle.getReturnType()))
        { // 检查是否支持此类型的返回值
            composite.handleReturnValue(returnValue, methodHandle.getReturnType(), container, webRequest);
            log.info(String.valueOf(container.getModel()));
            log.info(container.getViewName());
            renderView(context, container, webRequest); // 渲染视图
        }
    }

    private static void test2(AnnotationConfigApplicationContext context) throws Exception
    {
        Method method = Controller.class.getMethod("test2");
        Controller controller = new Controller();
        Object returnValue = method.invoke(controller); // 获取返回值
        HandlerMethod methodHandle = new HandlerMethod(controller, method);

        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = getReturnValueHandler();
        ServletWebRequest webRequest = new ServletWebRequest(new MockHttpServletRequest(), new MockHttpServletResponse());
        if (composite.supportsReturnType(methodHandle.getReturnType()))
        { // 检查是否支持此类型的返回值
            composite.handleReturnValue(returnValue, methodHandle.getReturnType(), container, webRequest);
            log.info(String.valueOf(container.getModel()));
            log.info(container.getViewName());
            renderView(context, container, webRequest); // 渲染视图
        }
    }

    private static void test1(AnnotationConfigApplicationContext context) throws Exception
    {
        Method method = Controller.class.getMethod("test1");
        Controller controller = new Controller();
        Object returnValue = method.invoke(controller); // 获取返回值
        HandlerMethod methodHandle = new HandlerMethod(controller, method);

        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = getReturnValueHandler();
        ServletWebRequest webRequest = new ServletWebRequest(new MockHttpServletRequest(), new MockHttpServletResponse());
        if (composite.supportsReturnType(methodHandle.getReturnType()))
        { // 检查是否支持此类型的返回值
            composite.handleReturnValue(returnValue, methodHandle.getReturnType(), container, webRequest);
            log.info(String.valueOf(container.getModel()));
            log.info(container.getViewName());
            renderView(context, container, webRequest); // 渲染视图
        }
    }

    public static HandlerMethodReturnValueHandlerComposite getReturnValueHandler()
    {
        HandlerMethodReturnValueHandlerComposite composite = new HandlerMethodReturnValueHandlerComposite();
        composite.addHandler(new ModelAndViewMethodReturnValueHandler());
        composite.addHandler(new ViewNameMethodReturnValueHandler());
        composite.addHandler(new ServletModelAttributeMethodProcessor(false));
        composite.addHandler(new HttpEntityMethodProcessor(List.of(new MappingJackson2HttpMessageConverter())));
        composite.addHandler(new HttpHeadersReturnValueHandler());
        composite.addHandler(new RequestResponseBodyMethodProcessor(List.of(new MappingJackson2HttpMessageConverter())));
        composite.addHandler(new ServletModelAttributeMethodProcessor(true));
        return composite;
    }

    private static void renderView(AnnotationConfigApplicationContext context, ModelAndViewContainer container,
                                   ServletWebRequest webRequest) throws Exception
    {
        log.debug(">>>>>> 渲染视图");
        FreeMarkerViewResolver resolver = context.getBean(FreeMarkerViewResolver.class);
        String viewName = container.getViewName() != null ? container.getViewName() : new DefaultRequestToViewNameTranslator().getViewName(webRequest.getRequest());
        log.debug("没有获取到视图名, 采用默认视图名: {}", viewName);
        // 每次渲染时, 会产生新的视图对象, 它并非被 Spring 所管理, 但确实借助了 Spring 容器来执行初始化
        View view = resolver.resolveViewName(viewName, Locale.getDefault());
        view.render(container.getModel(), webRequest.getRequest(), webRequest.getResponse());
        log.info(new String(((MockHttpServletResponse) webRequest.getResponse()).getContentAsByteArray(), StandardCharsets.UTF_8));
    }

    static class Controller
    {
        private static final Logger log = LoggerFactory.getLogger(Controller.class);

        public ModelAndView test1()
        {
            log.debug("test1()");
            ModelAndView mav = new ModelAndView("view1");
            mav.addObject("name", "张三");
            return mav;
        }

        public String test2()
        {
            log.debug("test2()");
            return "view2";
        }

        @ModelAttribute
        // @RequestMapping("/test3")
        public User test3()
        {
            log.debug("test3()");
            return new User("李四", 20);
        }

        public User test4()
        {
            log.debug("test4()");
            return new User("王五", 30);
        }

        public HttpEntity<User> test5()
        {
            log.debug("test5()");
            return new HttpEntity<>(new User("赵六", 40));
        }

        public HttpHeaders test6()
        {
            log.debug("test6()");
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/html");
            return headers;
        }

        @ResponseBody
        public User test7()
        {
            log.debug("test7()");
            return new User("钱七", 50);
        }
    }

    /**
     * 必须用 public 修饰, 否则 freemarker 渲染其 name, age 属性时失败
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User
    {
        private String name;
        private int age;
    }
}
