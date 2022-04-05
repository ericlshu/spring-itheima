package com.eric.config;

import com.eric.bean.MyPostProcessor;
import com.eric.bean.MyRegistrar1;
import com.eric.bean.MyRegistrar2;
import com.eric.service.impl.BookServiceImpl1;
import org.springframework.context.annotation.Import;

/**
 * Description : 导入实现了BeanDefinitionRegistryPostProcessor接口的类，通过BeanDefinition的注册器注册实名bean，实现对容器中bean的最终裁定
 * <p>
 * BookServiceImpl1 : BookServiceImpl1
 * MyRegistrar1     : BookServiceImpl2
 * MyRegistrar2     : BookServiceImpl3
 * MyPostProcessor  : BookServiceImpl4
 *
 * @author Eric L SHU
 * @date 2022-04-05 21:56
 * @since jdk-11.0.14
 */
@Import({MyPostProcessor.class, MyRegistrar2.class, MyRegistrar1.class, BookServiceImpl1.class})
public class SpringConfig8
{
}
