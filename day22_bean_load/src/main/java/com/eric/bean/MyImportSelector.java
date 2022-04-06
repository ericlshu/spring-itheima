package com.eric.bean;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-06 19:50
 * @since jdk-11.0.14
 */
public class MyImportSelector implements ImportSelector
{
    @Override
    public String[] selectImports(AnnotationMetadata metadata)
    {
        return new String[]{"com.eric.bean.Cat"};
    }
}
