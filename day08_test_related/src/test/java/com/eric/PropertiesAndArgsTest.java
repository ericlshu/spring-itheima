package com.eric;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-24 14:05
 */
@Slf4j
@SpringBootTest(
        properties = {"test.prop=property override","test.param=parameter in properties"},
        args = {"--test.args=argument override","--test.param=parameter in args"})
public class PropertiesAndArgsTest {

    @Value("${test.prop}")
    private String properties;

    @Value("${test.args}")
    private String arguments;

    @Value("${test.param}")
    private String parameter;

    @Test
    public void testProperties()
    {
        log.info("properties : " + properties);
    }

    @Test
    public void testArguments()
    {
        log.info("arguments : " + arguments);
    }

    @Test
    public void testPriority()
    {
        log.info("parameter : " + parameter);
    }

}
