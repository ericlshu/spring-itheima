package com.eric.config;

import com.eric.bean.Cat;
import com.eric.bean.DogFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Description : 使用spring配置类加载配置
 * <p>
 * 以下两组配置效果相同
 * <context:component-scan base-package="com.eric.bean,com.eric.config"/>
 * ComponentScan({"com.eric.bean", "com.eric.config"})
 * Configuration配置项如果不用于被扫描可以省略
 * <p>
 * 加载配置类并加载配置文件（系统迁移）
 * ImportResource("classpath:applicationContext1.xml")
 * <p>
 * 使用proxyBeanMethods=true可以保障调用此方法得到的对象是从容器中获取的而不是重新创建的
 *
 * @author Eric L SHU
 * @date 2022-04-04 17:20
 * @since jdk-11.0.14
 */
// @ComponentScan({"com.eric.bean", "com.eric.config"})
@ImportResource("classpath:applicationContext1.xml")
@Configuration(proxyBeanMethods = false)
public class SpringConfig3
{
    @Bean
    public DogFactoryBean dog()
    {
        return new DogFactoryBean();
    }

    @Bean
    public Cat cat()
    {
        return new Cat();
    }
}
