package com.eric.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Description :
 *
 * @author Eric SHU
 */

public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer
{

    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return new Class[]{SpringMVCConfig.class};
    }

    @Override
    protected String[] getServletMappings()
    {
        return new String[]{"/"};
    }

    /**
     * 设置字符集编码过滤器处理中文乱码问题
     */
    @Override
    protected Filter[] getServletFilters()
    {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        return new Filter[]{encodingFilter};
    }
}
