package com.eric.a22;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * 如何获取方法参数名, 注意把 a22 目录添加至模块的类路径
 * -> 1. a22 不在 src 是避免 idea 自动编译它下面的类
 * -> 2. spring boot 在编译时会加 -parameters
 * -> 3. 大部分 IDE 编译时都会加 -g
 * <p>
 * a. 如果编译时添加了 -parameters 可以生成参数表, 反射时就可以拿到参数名
 * b. 如果编译时添加了 -g 可以生成调试信息, 但分为两种情况
 * -> 1. 普通类, 会包含局部变量表, 用 asm 可以拿到参数名
 * -> 2. 接口, 不会包含局部变量表, 无法获得参数名 (这也是 MyBatis 在实现 Mapper 接口时为何要提供 @Param 注解来辅助获得参数名)
 * <p>
 * Spring在DefaultParameterNameDiscoverer集成了两种反射和本地变量表两种方式获取变量名称
 * -> addDiscoverer(new StandardReflectionParameterNameDiscoverer());
 * -> addDiscoverer(new LocalVariableTableParameterNameDiscoverer());
 */
@Slf4j
public class A22
{
    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException
    {
        // 1. 反射获取参数名
        Method foo = Bean2.class.getMethod("foo", String.class, int.class);
        for (Parameter parameter : foo.getParameters())
        {
            log.debug(parameter.getName());
        }

        // 2. 基于 LocalVariableTable 本地变量表
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(foo);
        log.info(Arrays.toString(parameterNames));
    }
}
