package com.eric.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * Description : Spring核心配置文件
 * <p>
 * -> excludeFilters属性：设置扫描加载bean时，排除的过滤规则
 * -> type属性：设置排除规则，当前使用按照bean定义时的注解类型进行排除
 * -->> ANNOTATION：按照注解排除
 * -->> ASSIGNABLE_TYPE:按照指定的类型过滤
 * -->> ASPECTJ:按照Aspectj表达式排除，基本上不会用
 * -->> REGEX:按照正则表达式排除
 * -->> CUSTOM:按照自定义规则排除
 * -> classes属性：设置排除的具体注解类，当前设置排除@Controller定义的bean
 *
 * @author Eric SHU
 */
@Configuration
//@ComponentScan({"com.eric.service","com.eric.dao"})
// 设置spring配置类加载bean时的过滤规则，当前要求排除掉表现层对应的bean
// excludeFilters属性：设置扫描加载bean时，排除的过滤规则
// type属性：设置排除规则，当前使用按照bean定义时的注解类型进行排除
// classes属性：设置排除的具体注解类，当前设置排除@Controller定义的bean
@ComponentScan(
        value = "com.eric",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Controller.class
        )
)
@PropertySource("classpath:jdbc.properties")
@Import({JdbcConfig.class, MybatisConfig.class})
public class SpringConfig
{
}
