package com.eric;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.HeaderResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-25 11:09
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //开启虚拟MVC调用
public class WebTest {

    @Test
    void testRandomPort()
    {
        log.warn("com.eric.WebTest.testRandomPort ...");
    }

    /**
     * @param mvc // 注入虚拟MVC调用对象
     */
    @Test
    void testMockMvc(@Autowired MockMvc mvc) throws Exception
    {
        log.warn("com.eric.WebTest.testMockMvc ...");
        // 创建虚拟请求，当前访问/books
        RequestBuilder builder = MockMvcRequestBuilders.get("/books/1");
        // 执行请求
        mvc.perform(builder);
    }

    /**
     * java.lang.AssertionError: Status expected:<200> but was:<404>
     */
    @Test
    void testStatus(@Autowired MockMvc mvc) throws Exception
    {
        log.warn("com.eric.WebTest.testStatus ...");

        RequestBuilder builder = MockMvcRequestBuilders.get("/books/1");
        ResultActions actions = mvc.perform(builder);

        // 使用本次真实执行结果与预期结果进行比对

        // 1. 通过结果匹配器获得当前模拟运行状态
        StatusResultMatchers status = MockMvcResultMatchers.status();

        // 2. 定义预期执行状态
        ResultMatcher statusOk = status.isOk();

        // 3. 添加预期值到本次调用过程中进行匹配
        actions.andExpect(statusOk);
    }

    /**
     * Response content expected:<springboot web testing> but was:<springboot web test>
     */
    @Test
    void testBody(@Autowired MockMvc mvc) throws Exception
    {
        log.warn("com.eric.WebTest.testBody ...");

        RequestBuilder builder = MockMvcRequestBuilders.get("/books/1");
        ResultActions actions = mvc.perform(builder);

        // 1. 通过结果匹配器获得当前模拟运行结果
        ContentResultMatchers content = MockMvcResultMatchers.content();

        // 2. 定义字符串格式预期执行状态
        ResultMatcher resultAsString = content.string("springboot web testing");

        // 3. 添加预期值到本次调用过程中进行匹配
        actions.andExpect(resultAsString);
    }

    /**
     * MockHttpServletResponse:
     *            Status = 200
     *     Error message = null
     *           Headers = [Content-Type:"application/json"]
     *      Content type = application/json
     *              Body = {"id":1,"name":"Java编程思想"}
     *     Forwarded URL = null
     *    Redirected URL = null
     *           Cookies = []
     *
     * java.lang.AssertionError: name
     * Expected: JAVA编程思想
     *      got: Java编程思想
     */
    @Test
    void testJson(@Autowired MockMvc mvc) throws Exception
    {
        log.warn("com.eric.WebTest.testJson ...");

        RequestBuilder builder = MockMvcRequestBuilders.get("/books/2");
        ResultActions actions = mvc.perform(builder);

        // 1. 通过结果匹配器获得当前模拟运行结果
        ContentResultMatchers content = MockMvcResultMatchers.content();

        // 2. 定义Json格式预期执行状态
        ResultMatcher resultAsJson = content.json("{\"id\":1,\"name\":\"JAVA编程思想\"}");

        // 3. 添加预期值到本次调用过程中进行匹配
        actions.andExpect(resultAsJson);
    }

    /**
     * MockHttpServletResponse:
     *            Status = 200
     *     Error message = null
     *           Headers = [Content-Type:"text/plain;charset=UTF-8", Content-Length:"19"]
     *      Content type = text/plain;charset=UTF-8
     *              Body = springboot web test
     *     Forwarded URL = null
     *    Redirected URL = null
     *           Cookies = []
     *
     * java.lang.AssertionError: Response header 'Content-Type' expected:<application/json> but was:<text/plain;charset=UTF-8>
     * Expected :application/json
     * Actual   :text/plain;charset=UTF-8
     */
    @Test
    void testContentType(@Autowired MockMvc mvc) throws Exception
    {
        log.warn("com.eric.WebTest.testContentType ...");
        RequestBuilder builder = MockMvcRequestBuilders.get("/books/1");
        ResultActions actions = mvc.perform(builder);

        // 1. 通过结果匹配器获得当前模拟运行结果头信息
        HeaderResultMatchers header = MockMvcResultMatchers.header();

        // 2. 定义预期执行header结果
        ResultMatcher contentType = header.string("Content-Type", "application/json");

        // 3. 添加预期值到本次调用过程中进行匹配
        actions.andExpect(contentType);
    }
}
