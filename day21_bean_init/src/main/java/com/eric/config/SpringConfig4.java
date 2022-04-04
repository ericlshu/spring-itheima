package com.eric.config;

import com.eric.bean.Dog;
import org.springframework.context.annotation.Import;

/**
 * Description : 使用@Import注解导入要注入的bean对应的字节码
 * 此形式可以有效的降低源代码与Spring技术的耦合度，在spring技术底层及诸多框架的整合中大量使用
 *
 * @author Eric L SHU
 * @date 2022-04-04 15:44
 * @since jdk-11.0.14
 */
@Import({Dog.class, DBConfig.class})
public class SpringConfig4
{
}
