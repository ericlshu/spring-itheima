package com.eric.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-11 13:26
 */
public class MyAspect {

    public static final Logger LOGGER = LogManager.getLogger();

    public void before()
    {
        LOGGER.debug("before ...");
    }

    public void after()
    {
        LOGGER.debug("after ...");
    }
}
