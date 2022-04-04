package com.eric.app;

import com.eric.config.SpringConfig3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-04 15:44
 * @since jdk-11.0.14
 */
public class App3
{
    public static void main(String[] args)
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig3.class);

        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String definitionName : definitionNames)
        {
            System.out.println(definitionName);
        }
        System.out.println("-----------------------------------------------------");
        // 从spring容器中获取对象默认为单例
        System.out.println(applicationContext.getBean("dog"));
        System.out.println(applicationContext.getBean("dog"));
        System.out.println(applicationContext.getBean("dog"));

        System.out.println("-----------------------------------------------------");

        SpringConfig3 springConfig3 = applicationContext.getBean("springConfig3", SpringConfig3.class);
        System.out.println(springConfig3);
        System.out.println(springConfig3.cat());
        System.out.println(springConfig3.cat());
        System.out.println(springConfig3.cat());

        // proxyBeanMethods = false 时创建原对象，三次运行返回不同对象
        // com.eric.config.SpringConfig3@6cd24612
        // com.eric.bean.Cat@5dafbe45
        // com.eric.bean.Cat@2254127a
        // com.eric.bean.Cat@51891008

        // proxyBeanMethods = true 时创建代理对象，三次运行返回相同对象
        // com.eric.config.SpringConfig3$$EnhancerBySpringCGLIB$$8a45c3da@7357a011
        // com.eric.bean.Cat@68f4865
        // com.eric.bean.Cat@68f4865
        // com.eric.bean.Cat@68f4865
    }
}
