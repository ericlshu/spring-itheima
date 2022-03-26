package com.eric;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-26 21:49
 */
@SpringBootTest
public class StringRedisTemplateTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void test()
    {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("name", "eric");
        String name = ops.get("name");
        System.out.println("name = " + name);
    }

}
