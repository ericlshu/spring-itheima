package com.eric.a23;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ServletRequestDataBinderFactory;

import java.util.Date;

/**
 * ServletRequestDataBinderFactory 的用法和扩展点
 * 1. 可以解析控制器的 @InitBinder 标注方法作为扩展点，添加自定义转换器
 * -> 控制器私有范围
 * 2. 可以通过 ConfigurableWebBindingInitializer 配置 ConversionService 作为扩展点，添加自定义转换器
 * -> 公共范围
 * 3. 同时加了 @InitBinder 和 ConversionService 的转换优先级
 * -> 1. 优先采用 @InitBinder 的转换器
 * -> 2. 其次使用 ConversionService 的转换器
 * -> 3. 使用默认转换器
 * -> 4. 特殊处理（例如有参构造）
 */
@Slf4j
public class TestServletDataBinderFactory
{
    public static void main(String[] args) throws Exception
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("birthday", "1999|01|02");
        request.setParameter("address.name", "西安");

        User target = new User();

        // "0. ServletRequestDataBinder 无法转换特殊的日期类型"
        // ServletRequestDataBinder dataBinder = new ServletRequestDataBinder(target);

        // "1. 用工厂, 无转换功能"
        // ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(null, null);

        // "2. 用 @InitBinder 转换"          PropertyEditorRegistry PropertyEditor
        // InvocableHandlerMethod method = new InvocableHandlerMethod(
        //         new MyController(), MyController.class.getMethod("aaa", WebDataBinder.class));
        // ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(List.of(method), null);

        // "3. 用 ConversionService 转换"    ConversionService Formatter
        // FormattingConversionService service = new FormattingConversionService();
        // service.addFormatter(new MyDateFormatter("用 ConversionService 方式扩展转换功能；"));
        // ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
        // initializer.setConversionService(service);
        // ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(null, initializer);

        // "4. 同时加了 @InitBinder 和 ConversionService"
        // InvocableHandlerMethod method = new InvocableHandlerMethod(
        //         new MyController(), MyController.class.getMethod("aaa", WebDataBinder.class));

        // FormattingConversionService service = new FormattingConversionService();
        // service.addFormatter(new MyDateFormatter("用 ConversionService 方式扩展转换功能；"));
        // ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
        // initializer.setConversionService(service);

        // ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(List.of(method), initializer);

        // "5. 使用默认 ConversionService 转换，配合@DateTimeFormat注解指定日期格式"
        ApplicationConversionService service = new ApplicationConversionService();
        ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
        initializer.setConversionService(service);

        ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(null, initializer);

        WebDataBinder dataBinder = factory.createBinder(new ServletWebRequest(request), target, "user");
        dataBinder.bind(new ServletRequestParameterPropertyValues(request));
        log.info("result : {}", target);
    }

    static class MyController
    {
        @InitBinder
        public void aaa(WebDataBinder dataBinder)
        {
            // 扩展 dataBinder 的转换器
            dataBinder.addCustomFormatter(new MyDateFormatter("用 @InitBinder 方式扩展的转换器；"));
        }
    }

    @Data
    public static class User
    {
        @DateTimeFormat(pattern = "yyyy|MM|dd")
        private Date birthday;
        private Address address;
    }

    @Data
    public static class Address
    {
        private String name;
    }
}
