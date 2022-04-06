package com.eric.config;

import com.eric.bean.MyImportSelector;
import org.springframework.context.annotation.Import;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-06 19:48
 * @since jdk-11.0.14
 */
@Import(MyImportSelector.class)
public class SpringConfig
{
}
