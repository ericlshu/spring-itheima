package com.eric;

import com.eric.config.SpringConfig;
import com.eric.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description : AOP实现步骤,工作流程和核心概念
 * <p>
 * 步骤1:添加依赖
 * -->> spring-context
 * -->> aspectjweaver
 * 步骤2:定义接口与实现类
 * 步骤3:定义通知类和通知
 * -->> com.eric.aop.MyAdvice#method
 * 步骤4:定义切入点
 * -->> com.eric.aop.MyAdvice#pointcut()
 * -->> 切入点定义依托一个不具有实际意义的方法进行，即无参数、无返回值、方法体无实际逻辑。
 * 步骤5:制作切面
 * -->> 绑定切入点与通知关系，并指定通知添加到原始连接点的具体执行位置
 * 步骤6:将通知类配给容器并标识其为切面类
 * -->> @Component
 * -->> @Aspect
 * 步骤7:开启注解格式AOP功能
 * -->> SpringConfig -> @EnableAspectJAutoProxy
 * <p>
 * 流程1:Spring容器启动
 * -->> 容器启动就需要去加载bean,哪些类需要被加载呢?
 * -->> 需要被增强的类，如:BookServiceImpl
 * -->> 通知类，如:MyAdvice
 * -->> 注意此时bean对象还没有创建成功
 * 流程2:读取所有切面配置中的切入点
 * 流程3:初始化bean
 * -->> 注意第1步在容器启动的时候，bean对象还没有被创建成功。
 * -->> 要被实例化bean对象的类中的方法和切入点进行匹配
 * -->> 匹配失败，创建原始对象,如`UserDao`
 * --->>> 匹配失败说明不需要增强，直接调用原始对象的方法即可。
 * -->> 匹配成功，创建原始对象（==目标对象==）的==代理==对象,如:`BookDao`
 * --->>> 匹配成功说明需要对其进行增强
 * --->>> 对哪个类做增强，这个类对应的对象就叫做目标对象
 * --->>> 因为要对目标对象进行功能增强，而采用的技术是动态代理，所以会为其创建一个代理对象
 * --->>> 最终运行的是代理对象的方法，在该方法中会对原始方法进行功能增强
 * 流程4:获取bean执行方法
 * -->> 获取的bean是原始对象时，调用方法并执行，完成操作
 * -->> 获取的bean是代理对象时，根据代理对象的运行模式运行原始方法与增强的内容，完成操作
 * <p>
 * 验证容器中是否为代理对象
 * -->> 如果目标对象中的方法会被增强，那么容器中将存入的是目标对象的代理对象
 * -->> 如果目标对象中的方法不被增强，那么容器中将存入的是目标对象本身。
 * <p>
 * AOP核心概念
 * -->> 目标对象(Target)：原始功能去掉共性功能对应的类产生的对象，这种对象是无法直接完成最终工作的
 * -->> 代理(Proxy)：目标对象无法直接完成工作，需要对其进行功能回填，通过原始对象的代理对象实现
 * --->>> 目标对象就是要增强的类[如:BookServiceImpl类]对应的对象，也叫原始对象，不能说它不能运行，只能说它在运行的过程中对于要增强的内容是缺失的。
 * --->>> SpringAOP是在不改变原有设计(代码)的前提下对其进行增强的，它的底层采用的是代理模式实现的，所以要对原始对象进行增强，就需要对原始对象创建代理对象，
 * --->>> 在代理对象中的方法把通知[如:MyAdvice中的method方法]内容加进去，就实现了增强,这就是我们所说的代理(Proxy)。
 *
 * @author Eric L SHU
 */
public class AppForAOP
{
    public static void main(String[] args)
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDao bookDao = context.getBean(BookDao.class);
        bookDao.update();
        System.out.println(bookDao);
        System.out.println(bookDao.getClass());
    }
}
