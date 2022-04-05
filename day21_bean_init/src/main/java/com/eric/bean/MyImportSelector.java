package com.eric.bean;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * Description : 导入实现了ImportSelector接口的类，实现对导入源的编程式处理
 *
 * @author Eric L SHU
 * @date 2022-04-05 21:54
 * @since jdk-11.0.14
 */
public class MyImportSelector implements ImportSelector
{
    @Override
    public String[] selectImports(AnnotationMetadata metadata)
    {
        System.out.println("className = " + metadata.getClassName());
        boolean hasAnnotation = metadata.hasAnnotation("org.springframework.context.annotation.Configuration");
        System.out.println("hasAnnotation = " + hasAnnotation);
        Map<String, Object> attributes = metadata.getAnnotationAttributes("org.springframework.context.annotation.ComponentScan");
        System.out.println("attributes = " + attributes);
        // 各种条件的判定，判定完毕后，决定是否装在指定的bean
        if (hasAnnotation)
            return new String[]{"com.eric.bean.Dog"};
        else
            return new String[]{"com.eric.bean.Cat"};
    }
}
