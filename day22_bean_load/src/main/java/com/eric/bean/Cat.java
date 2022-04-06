package com.eric.bean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.stereotype.Component;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-06 19:46
 * @since jdk-11.0.14
 */
@Component("tom")
@ConditionalOnBean(name = "jerry")
@ConditionalOnNotWebApplication
public class Cat
{
}
