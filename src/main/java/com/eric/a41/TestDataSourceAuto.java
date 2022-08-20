package com.eric.a41;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 1. DataSourceAutoConfiguration
 * -> 对应的自动配置类为：org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
 * -> 它内部采用了条件装配，通过检查容器的 bean，以及类路径下的 class，来决定该 @Bean 是否生效
 * 简单说明一下，Spring Boot 支持两大类数据源：
 * -> EmbeddedDatabase - 内嵌数据库连接池
 * -> PooledDataSource - 非内嵌数据库连接池
 * PooledDataSource 又支持如下数据源
 * -> hikari 提供的 HikariDataSource
 * -> tomcat-jdbc 提供的 DataSource
 * -> dbcp2 提供的 BasicDataSource
 * -> oracle 提供的 PoolDataSourceImpl
 * <p>
 * 2. MybatisAutoConfiguration
 * MyBatis 自动配置类为 `org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration`
 * 它主要配置了两个 bean
 * -> SqlSessionFactory - MyBatis 核心对象，用来创建 SqlSession
 * -> SqlSessionTemplate - SqlSession 的实现，此实现会与当前线程绑定
 * -> 用 ImportBeanDefinitionRegistrar 的方式扫描所有标注了 @Mapper 注解的接口
 * -> 用 AutoConfigurationPackages 来确定扫描的包
 * 还有一个相关的 bean：MybatisProperties，它会读取配置文件中带 `mybatis.` 前缀的配置项进行定制配置
 * -> @MapperScan 注解的作用与 MybatisAutoConfiguration 类似，会注册 MapperScannerConfigurer 有如下区别
 * -->> @MapperScan 扫描具体包（当然也可以配置关注哪个注解）
 * -->> @MapperScan 如果不指定扫描具体包，则会把引导类范围内，所有接口当做 Mapper 接口
 * -->> MybatisAutoConfiguration 关注的是所有标注 @Mapper 注解的接口，会忽略掉非 @Mapper 标注的接口
 * 这里有同学有疑问，之前介绍的都是将具体类交给 Spring 管理，怎么到了 MyBatis 这儿，接口就可以被管理呢？
 * -> 其实并非将接口交给 Spring 管理，而是每个接口会对应一个 MapperFactoryBean，是后者被 Spring 所管理，接口只是作为 MapperFactoryBean 的一个属性来配置
 * <p>
 * 3. TransactionAutoConfiguration
 * 事务自动配置类有两个：
 * -> `org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration`
 * -> `org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration`
 * 前者配置了 DataSourceTransactionManager 用来执行事务的提交、回滚操作
 * 后者功能上对标 @EnableTransactionManagement，包含以下三个 bean
 * -> BeanFactoryTransactionAttributeSourceAdvisor 事务切面类，包含通知和切点
 * -> TransactionInterceptor 事务通知类，由它在目标方法调用前后加入事务操作
 * -> AnnotationTransactionAttributeSource 会解析 @Transactional 及事务属性，也包含了切点功能
 * 如果自己配置了 DataSourceTransactionManager 或是在引导类加了 @EnableTransactionManagement，则以自己配置的为准
 */
@Slf4j
public class TestDataSourceAuto
{
    public static void main(String[] args)
    {
        GenericApplicationContext context = new GenericApplicationContext();
        StandardEnvironment env = new StandardEnvironment();
        env.getPropertySources().addLast(new SimpleCommandLinePropertySource(
                "--spring.datasource.url=jdbc:mysql://localhost:3306/test",
                "--spring.datasource.username=root",
                "--spring.datasource.password=root"
        ));
        context.setEnvironment(env);
        AnnotationConfigUtils.registerAnnotationConfigProcessors(context.getDefaultListableBeanFactory());
        context.registerBean(Config.class);

        String packageName = TestDataSourceAuto.class.getPackageName();
        log.info("当前包名:" + packageName);
        AutoConfigurationPackages.register(context.getDefaultListableBeanFactory(), packageName);

        context.refresh();

        for (String name : context.getBeanDefinitionNames())
        {
            String resourceDescription = context.getBeanDefinition(name).getResourceDescription();
            if (resourceDescription != null)
                log.debug(name + " <from:> " + resourceDescription);
        }
    }

    @Configuration
    @Import(MyImportSelector.class)
    static class Config
    {
    }

    static class MyImportSelector implements DeferredImportSelector
    {
        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata)
        {
            return new String[]{
                    DataSourceAutoConfiguration.class.getName(),
                    MybatisAutoConfiguration.class.getName(),
                    DataSourceTransactionManagerAutoConfiguration.class.getName(),
                    TransactionAutoConfiguration.class.getName()
            };
        }
    }
}
