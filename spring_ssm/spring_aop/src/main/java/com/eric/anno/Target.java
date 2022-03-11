package com.eric.anno;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-11 12:29
 */
@Component("target")
public class Target implements TargetInterface {

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String save()
    {
        LOGGER.debug("save ...");
        // int i = 1 / 0;
        return "save";
    }
}
