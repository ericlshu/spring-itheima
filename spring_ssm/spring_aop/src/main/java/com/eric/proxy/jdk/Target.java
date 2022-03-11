package com.eric.proxy.jdk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-11 12:29
 */
public class Target implements TargetInterface{

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String save()
    {
        LOGGER.debug("save ...");
        return "save";
    }
}
