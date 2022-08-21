package com.eric.a47;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 5. 如果待装配类型是数组，需要获取数组元素类型，根据此类型找到多个 bean 进行装配
 * 6. 如果待装配类型是 Collection 或其子接口，需要获取 Collection 泛型，根据此类型找到多个 bean
 * 7. 如果待装配类型是 ApplicationContext 等特殊类型
 * -> 会在 BeanFactory 的 resolvableDependencies 成员按类型查找装配
 * -> resolvableDependencies 是 map 集合，key 是特殊类型，value 是其对应对象
 * -> 不能直接根据 key 进行查找，而是用 isAssignableFrom 逐一尝试右边类型是否可以被赋值给左边的 key 类型
 * 8. 如果待装配类型有泛型参数
 * -> 需要利用 ContextAnnotationAutowireCandidateResolver 按泛型参数类型筛选
 * 9. 如果待装配类型有 @Qualifier
 * -> 需要利用 ContextAnnotationAutowireCandidateResolver 按注解提供的 bean 名称筛选
 */
@Slf4j
@Configuration
@SuppressWarnings("all")
public class A47_2
{
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(A47_2.class);
        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 1. 数组类型             <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        testArray(beanFactory);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 2. List 类型           <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        testList(beanFactory);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 3. applicationContext <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        testApplicationContext(beanFactory);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 4. 泛型                <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        testGeneric(beanFactory);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 5. @Qualifier         <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        testQualifier(beanFactory);
    }

    private static void testQualifier(DefaultListableBeanFactory beanFactory) throws NoSuchFieldException
    {
        DependencyDescriptor dd5 = new DependencyDescriptor(Target.class.getDeclaredField("service"), true);
        Class<?> type = dd5.getDependencyType();
        ContextAnnotationAutowireCandidateResolver resolver = new ContextAnnotationAutowireCandidateResolver();
        resolver.setBeanFactory(beanFactory);
        for (String beanName : BeanFactoryUtils.beanNamesForTypeIncludingAncestors(beanFactory, type))
        {
            log.debug("Bean Name : {}", beanName);
            BeanDefinition bd = beanFactory.getMergedBeanDefinition(beanName);
            // @Qualifier("service2")
            if (resolver.isAutowireCandidate(new BeanDefinitionHolder(bd, beanName), dd5))
            {
                log.debug("└─── Item : {}", dd5.resolveCandidate(beanName, type, beanFactory));
            }
        }
    }

    private static void testGeneric(DefaultListableBeanFactory beanFactory) throws NoSuchFieldException
    {
        DependencyDescriptor dd4 = new DependencyDescriptor(Target.class.getDeclaredField("dao"), true);
        Class<?> type = dd4.getDependencyType();
        ContextAnnotationAutowireCandidateResolver resolver = new ContextAnnotationAutowireCandidateResolver();
        resolver.setBeanFactory(beanFactory);
        for (String beanName : BeanFactoryUtils.beanNamesForTypeIncludingAncestors(beanFactory, type))
        {
            log.debug("Bean Name : {}", beanName);
            BeanDefinition beanDefinition = beanFactory.getMergedBeanDefinition(beanName);
            // 对比 BeanDefinition 与 DependencyDescriptor 的泛型是否匹配
            if (resolver.isAutowireCandidate(new BeanDefinitionHolder(beanDefinition, beanName), dd4))
            {
                log.debug("└─── Item : {}", dd4.resolveCandidate(beanName, type, beanFactory));
            }
        }
    }

    private static void testApplicationContext(DefaultListableBeanFactory beanFactory) throws NoSuchFieldException, IllegalAccessException
    {
        DependencyDescriptor dd3 = new DependencyDescriptor(Target.class.getDeclaredField("applicationContext"), true);

        Field resolvableDependencies = DefaultListableBeanFactory.class.getDeclaredField("resolvableDependencies");
        resolvableDependencies.setAccessible(true);
        Map<Class<?>, Object> dependencies = (Map<Class<?>, Object>) resolvableDependencies.get(beanFactory);
        // dependencies.forEach((k, v) -> log.debug("key:" + k + " value: " + v));

        for (Map.Entry<Class<?>, Object> entry : dependencies.entrySet())
        {
            // 左边类型                      右边类型
            if (entry.getKey().isAssignableFrom(dd3.getDependencyType()))
            {
                log.debug("Entry Values : {}", entry.getValue());
                break;
            }
        }
    }

    private static void testList(DefaultListableBeanFactory beanFactory) throws NoSuchFieldException
    {
        DependencyDescriptor dd2 = new DependencyDescriptor(Target.class.getDeclaredField("serviceList"), true);
        if (dd2.getDependencyType() == List.class)
        {
            Class<?> resolveType = dd2.getResolvableType().getGeneric().resolve();
            log.debug("Resolve Type : {}", resolveType);
            List<Object> serviceList = new ArrayList<>();
            String[] names = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(beanFactory, resolveType);
            for (String name : names)
            {
                serviceList.add(dd2.resolveCandidate(name, resolveType, beanFactory));
            }
            log.debug("Service List : {}", serviceList);
            serviceList.forEach(item -> log.debug("Service Item : {}", item));
        }
    }

    private static void testArray(DefaultListableBeanFactory beanFactory) throws NoSuchFieldException
    {
        DependencyDescriptor dd1 = new DependencyDescriptor(Target.class.getDeclaredField("serviceArray"), true);
        if (dd1.getDependencyType().isArray())
        {
            Class<?> componentType = dd1.getDependencyType().getComponentType();
            log.debug("ComponentType : {}", componentType);
            String[] names = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(beanFactory, componentType);
            List<Object> beans = new ArrayList<>();
            for (String name : names)
            {
                log.debug("Bean name     : {}", name);
                beans.add(dd1.resolveCandidate(name, componentType, beanFactory));
            }
            Object serviceArray = beanFactory.getTypeConverter().convertIfNecessary(beans, dd1.getDependencyType());
            log.debug("Service Array : {}", serviceArray);
        }
    }

    static class Target
    {
        @Autowired
        private Service[] serviceArray;
        @Autowired
        private List<Service> serviceList;
        @Autowired
        private ConfigurableApplicationContext applicationContext;
        @Autowired
        private Dao<Teacher> dao;
        @Autowired
        @Qualifier("service2")
        private Service service;
    }

    interface Dao<T>
    {
    }

    @Component("dao1")
    static class Dao1 implements Dao<Student>
    {
    }

    @Component("dao2")
    static class Dao2 implements Dao<Teacher>
    {
    }

    static class Student
    {
    }

    static class Teacher
    {
    }

    interface Service
    {
    }

    @Component("service1")
    static class Service1 implements Service
    {
    }

    @Component("service2")
    static class Service2 implements Service
    {
    }

    @Component("service3")
    static class Service3 implements Service
    {
    }
}
