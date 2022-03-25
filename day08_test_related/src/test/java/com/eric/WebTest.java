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
        RequestBuilder builder = MockMvcRequestBuilders.get("/books");
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

        RequestBuilder builder = MockMvcRequestBuilders.get("/books");
        ResultActions actions = mvc.perform(builder);

        // 使用本次真实执行结果与预期结果进行比对

        // 1. 通过结果匹配器获得当前模拟运行状态
        StatusResultMatchers status = MockMvcResultMatchers.status();

        // 2. 定义预期执行状态
        ResultMatcher statusOk = status.isOk();

        // 3. 添加预期值到本次调用过程中进行匹配
        actions.andExpect(statusOk);
    }
}
