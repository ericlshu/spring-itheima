package com.eric;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class Day10DaoRedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads()
    {

    }

    @Test
    void testSetAndGet()
    {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("age", 28);
        int age = (int) valueOperations.get("age");
        System.out.println("age = " + age);
    }

    @Test
    void testHash()
    {
        HashOperations opsForHash = redisTemplate.opsForHash();
        opsForHash.put("info","a","aa");
        opsForHash.put("info","b","bb");
        Object o = opsForHash.get("info", "a");
        System.out.println("o = " + o);
    }

}
