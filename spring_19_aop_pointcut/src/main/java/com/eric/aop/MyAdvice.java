package com.eric.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Description : AOP切入点表达式
 * <p>
 * 对于切入点表达式的语法为:
 * -->> 切入点表达式标准格式：动作关键字(访问修饰符 返回值 包名.类/接口名.方法名(参数) 异常名）
 * -->> execution(public User com.eric.service.UserService.findById(int))
 * --->>> execution：动作关键字，描述切入点的行为动作，例如execution表示执行到指定切入点
 * --->>> public           : 访问修饰符,还可以是public，private等，可以省略
 * --->>> User             : 返回值，写返回值类型
 * --->>> com.eric.service : 包名，多级包使用点连接
 * --->>> UserService      : 类/接口名称
 * --->>> findById         : 方法名
 * --->>> int              : 参数，直接写参数的类型，多个类型用逗号隔开
 * --->>> 异常名            : 方法定义中抛出指定异常，可以省略
 * <p>
 * 通配符
 * -> `*` : 单个独立的任意符号，可以独立出现，也可以作为前缀或者后缀的匹配符出现
 * -->> execution（public * com.eric.*.UserService.find*(*))
 * -->> 匹配com.eric包下的任意包中的UserService类或接口中所有find开头的带有一个参数的方法
 * -> `..` : 多个连续的任意符号，可以独立出现，常用于简化包名与参数的书写
 * -->> execution（public User com..UserService.findById(..))
 * -->> 匹配com包下的任意包中的UserService类或接口中所有名称为findById的方法
 * -> `+` : 专用于匹配子类类型
 * -->> execution(* *..*Service+.*(..))
 * -->> *Service+，表示所有以Service结尾的接口的子类。
 * <p>
 * 书写技巧
 * -->> 所有代码按照标准规范开发，否则以下技巧全部失效
 * -->> 描述切入点通常描述接口，而不描述实现类,如果描述到实现类，就出现紧耦合了
 * -->> 访问控制修饰符针对接口开发均采用public描述（可省略访问控制修饰符描述）
 * -->> 返回值类型对于增删改类使用精准类型加速匹配，对于查询类使用*通配快速描述
 * -->> 包名书写尽量不使用..匹配，效率过低，常用*做单个包描述匹配，或精准匹配
 * -->> 接口名/类名书写名称与模块相关的采用*匹配，例如UserService书写成*Service，绑定业务层接口名
 * -->> 方法名书写以动词进行精准匹配，名词采用*匹配，例如getById书写成getBy*,selectAll书写成selectAll
 * -->> 参数规则较为复杂，根据业务方法灵活调整
 * -->> 通常不使用异常作为匹配规则
 *
 * @author Eric L SHU
 */
@Component
@Aspect
public class MyAdvice
{
    // 执行com.eric.dao包下的BookDao接口中的无参数update方法
    // @Pointcut("execution(void com.eric.dao.BookDao.update())")

    // 执行com.eric.dao.impl包下的BookDaoImpl类中的无参数update方法
    // @Pointcut("execution(void com.eric.dao.impl.BookDaoImpl.update())")

    // 执行com.eric.dao.impl包下的BookDaoImpl类中的无参数任意返回值update方法
    // @Pointcut("execution(* com.eric.dao.impl.BookDaoImpl.update())")

    // 返回值任意，但是update方法必须要有一个参数，无法匹配，要想匹配需要在update接口和实现类添加参数
    // @Pointcut("execution(* com.eric.dao.impl.BookDaoImpl.update(*))")

    // 返回值为void,com包下的任意包三层包下的任意类的update方法，匹配到的是实现类，能匹配
    // @Pointcut("execution(void com.*.*.*.*.update())")

    // 返回值为void,com包下的任意两层包下的任意类的update方法，匹配到的是接口，能匹配
    // @Pointcut("execution(void com.*.*.*.update())")

    // 返回值为void，方法名是update的任意包下的任意类，能匹配
    // @Pointcut("execution(void *..update())")

    // 匹配项目中任意类的任意方法，能匹配，但是不建议使用这种方式，影响范围广
    // @Pointcut("execution(void *..*(..))")

    // 匹配项目中任意包任意类下只要以u开头的方法，update方法能满足，能匹配
    // @Pointcut("execution(void *..u*(..))")

    // 匹配项目中任意包任意类下只要以e结尾的方法，update和save方法能满足，能匹配
    // @Pointcut("execution(* *..*e(..))")

    // 返回值为void，com包下的任意包任意类任意方法，能匹配，*代表的是方法
    // @Pointcut("execution(void com..*())")

    // 将项目中所有业务层方法的以find开头的方法匹配
    // @Pointcut("execution(* com.eric.*.*Service.find*(..))")

    // 将项目中所有业务层方法的以save开头的方法匹配
    // @Pointcut("execution(* com.eric.*.*Service.save*(..))")
    @Pointcut("execution(* com.eric..*Dao.*(..))")
    private void pointcut()
    {
    }

    /**
     * 设置在切入点pointcut()的前面运行当前操作（前置通知）
     */
    @Before("pointcut()")
    public void method()
    {
        System.err.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
    }
}
