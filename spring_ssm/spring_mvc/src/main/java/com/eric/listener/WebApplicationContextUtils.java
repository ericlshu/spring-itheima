package com.eric.listener;

import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-08 12:51
 */
public class WebApplicationContextUtils {
    public static ApplicationContext getApplicationContext(ServletContext servletContext)
    {
        return (ApplicationContext) servletContext.getAttribute("applicationContext");
    }
}
