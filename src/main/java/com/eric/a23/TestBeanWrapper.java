package com.eric.a23;

import lombok.Data;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Date;

public class TestBeanWrapper
{
    public static void main(String[] args)
    {
        // 利用反射原理, 为 bean 的属性赋值
        MyBean target = new MyBean();
        BeanWrapperImpl wrapper = new BeanWrapperImpl(target);
        wrapper.setPropertyValue("a", "10");
        wrapper.setPropertyValue("b", "hello");
        wrapper.setPropertyValue("c", "1999/03/04");
        System.out.println(target);
    }

    @Data
    static class MyBean
    {
        private int a;
        private String b;
        private Date c;
    }
}
