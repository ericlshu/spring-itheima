package com.eric.a12;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 1. 方法重写可以增强逻辑，只不过这【增强逻辑】千变万化，不能写死在代理内部
 * 2. 通过接口回调将【增强逻辑】置于代理类之外
 * 3. 配合接口方法反射（是多态调用），就可以再联动调用目标方法
 * 4. 会用 arthas 的 jad 工具反编译代理类
 * 5. 限制⛔：代理增强是借助多态来实现，因此成员变量、静态方法、final 方法均不能通过代理实现
 */
public class $Proxy0 extends Proxy implements A12.Foo
{
    // private InvocationHandler h;

    public $Proxy0(InvocationHandler h)
    {
        super(h);
    }

    @Override
    public void foo()
    {
        try
        {
            h.invoke(this, foo, new Object[0]);
        }
        catch (RuntimeException | Error e)
        {
            throw e;
        }
        catch (Throwable e)
        {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public int bar()
    {
        try
        {
            Object result = h.invoke(this, bar, new Object[0]);
            return (int) result;
        }
        catch (RuntimeException | Error e)
        {
            throw e;
        }
        catch (Throwable e)
        {
            throw new UndeclaredThrowableException(e);
        }
    }

    static Method foo;
    static Method bar;

    static
    {
        try
        {
            foo = A12.Foo.class.getMethod("foo");
            bar = A12.Foo.class.getMethod("bar");
        }
        catch (NoSuchMethodException e)
        {
            throw new NoSuchMethodError(e.getMessage());
        }
    }
}
