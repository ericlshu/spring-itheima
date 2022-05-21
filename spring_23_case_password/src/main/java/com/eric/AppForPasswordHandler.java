package com.eric;

import com.eric.config.SpringConfig;
import com.eric.service.ResourcesService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description :
 *
 * @author Eric SHU
 */
public class AppForPasswordHandler
{
    public static void main(String[] args)
    {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        ResourcesService resourcesService = ctx.getBean(ResourcesService.class);
        boolean flag = resourcesService.openURL("http://pan.baidu.com/haha", " root ");
        System.out.println(flag);
    }
}
