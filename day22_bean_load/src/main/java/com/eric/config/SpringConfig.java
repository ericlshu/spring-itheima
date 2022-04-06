package com.eric.config;

import com.eric.bean.Cat;
import com.eric.bean.Mouse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * Description :
 * <p>
 * 按类型匹配指定类         @ConditionalOnClass(Mouse.class)
 * 按类名匹配指定类         @ConditionalOnClass(name = "com.eric.bean.Mouse")
 * 按类名匹配未指定类       @ConditionalOnMissingClass("com.eric.bean.Wolf")
 * 按类名匹配指定bean      @ConditionalOnBean(name = "com.eric.bean.Mouse")
 * 按名称匹配指定bean      @ConditionalOnBean(name = "jerry")
 * 匹配web环境            @ConditionalOnWebApplication
 * 匹配非web环境          @ConditionalOnNotWebApplication
 *
 * @author Eric L SHU
 * @date 2022-04-06 19:48
 * @since jdk-11.0.14
 */
// @Import(MyImportSelector.class)
@Import(Mouse.class)
public class SpringConfig
{
    @Bean
    // @ConditionalOnClass(Mouse.class)
    // @ConditionalOnClass(name = "com.eric.bean.Mouse")
    // @ConditionalOnMissingClass("com.eric.bean.Wolf")
    // @ConditionalOnBean(name = "com.eric.bean.Mouse")

    // @ConditionalOnBean(name = "jerry")
    // @ConditionalOnMissingClass("com.eric.bean.Dog")
    // @ConditionalOnNotWebApplication
    @ConditionalOnWebApplication
    public Cat tom()
    {
        return new Cat();
    }
}
