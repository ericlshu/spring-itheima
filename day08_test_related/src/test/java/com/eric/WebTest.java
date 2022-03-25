package com.eric;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
        log.warn("com.eric.WebTest.testRandomWeb ...");
        // 创建虚拟请求，当前访问/books
        RequestBuilder builder = MockMvcRequestBuilders.get("/books");
        // 执行请求
        mvc.perform(builder);
    }
}
