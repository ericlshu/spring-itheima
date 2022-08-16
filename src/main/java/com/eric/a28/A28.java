package com.eric.a28;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 1. MessageConverter 的作用
 * -> @ResponseBody 是返回值处理器解析的
 * -> 但具体转换工作是 MessageConverter 做的
 * 2. 如何选择 MediaType
 * -> 首先看 @RequestMapping 上有没有指定 ?
 * -> 其次看 request 的 Accept 头有没有指定
 * -> 最后按 MessageConverter 的顺序, 谁能谁先转换
 */
@Slf4j
public class A28
{
    public static void main(String[] args) throws IOException, NoSuchMethodException, HttpMediaTypeNotAcceptableException
    {
        test1();
        log.warn("<=================================================================>");
        test2();
        log.warn("<=================================================================>");
        test3();
        log.warn("<=================================================================>");
        test4();
    }

    private static void test4() throws IOException, HttpMediaTypeNotAcceptableException, NoSuchMethodException
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        ServletWebRequest webRequest = new ServletWebRequest(request, response);

        request.addHeader("Accept", "application/xml");
        response.setContentType("application/json");

        RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(
                List.of(new MappingJackson2XmlHttpMessageConverter(), new MappingJackson2HttpMessageConverter())
                // List.of(new MappingJackson2HttpMessageConverter(), new MappingJackson2XmlHttpMessageConverter())
        );
        processor.handleReturnValue(
                new User("张三", 18),
                new MethodParameter(A28.class.getMethod("user"), -1),
                new ModelAndViewContainer(),
                webRequest
        );
        log.info(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
    }

    private static void test3() throws IOException
    {
        MockHttpInputMessage message = new MockHttpInputMessage("""
                {
                    "name":"李四",
                    "age":20
                }
                """.getBytes(StandardCharsets.UTF_8));
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        if (converter.canRead(User.class, MediaType.APPLICATION_JSON))
        {
            Object read = converter.read(User.class, message);
            log.info(read.toString());
        }
    }

    private static void test2() throws IOException
    {
        MockHttpOutputMessage message = new MockHttpOutputMessage();
        MappingJackson2XmlHttpMessageConverter converter = new MappingJackson2XmlHttpMessageConverter();
        if (converter.canWrite(User.class, MediaType.APPLICATION_XML))
        {
            converter.write(new User("李四", 20), MediaType.APPLICATION_XML, message);
            log.info(message.getBodyAsString());
        }
    }

    public static void test1() throws IOException
    {
        MockHttpOutputMessage message = new MockHttpOutputMessage();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        if (converter.canWrite(User.class, MediaType.APPLICATION_JSON))
        {
            converter.write(new User("张三", 18), MediaType.APPLICATION_JSON, message);
            log.info(message.getBodyAsString());
        }
    }

    @ResponseBody
    @RequestMapping(produces = "application/json")
    public User user()
    {
        return null;
    }

    @Data
    public static class User
    {
        private String name;
        private int age;

        @JsonCreator
        public User(@JsonProperty("name") String name, @JsonProperty("age") int age)
        {
            this.name = name;
            this.age = age;
        }
    }
}
