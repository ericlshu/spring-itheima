package com.eric.a23.generics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ResolvableType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * 获取泛型参数小技巧
 */
@Slf4j
public class TestGenericType
{
    public static void main(String[] args)
    {
        // 1. java api
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Type type = StudentDao.class.getGenericSuperclass();
        log.debug(type.toString());
        if (type instanceof ParameterizedType parameterizedType)
        {
            log.debug(parameterizedType.getActualTypeArguments()[0].toString());
        }

        // 2. spring api 1
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Class<?> t = GenericTypeResolver.resolveTypeArgument(StudentDao.class, BaseDao.class);
        log.debug(String.valueOf(t));

        // 3. spring api 2
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.debug(Objects.requireNonNull(ResolvableType.forClass(TeacherDao.class)
                .getSuperType().getGeneric().resolve()).toString());
    }
}
