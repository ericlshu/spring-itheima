package com.eric;

import com.eric.config.SpringConfig;
import com.eric.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description : AOP实现步骤
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
    }
}
