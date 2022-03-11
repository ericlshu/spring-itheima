package com.eric.proxy.cglib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-11 12:29
 */
public class Target {

    public static final Logger LOGGER = LogManager.getLogger();

    public String save()
    {
        LOGGER.debug("save ...");
        return "save";
    }
}
