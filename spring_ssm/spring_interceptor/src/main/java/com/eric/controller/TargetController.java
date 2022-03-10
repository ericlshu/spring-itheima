package com.eric.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-10 11:47
 */
@Controller
public class TargetController {

    public static final Logger LOGGER = LogManager.getLogger();

    @RequestMapping("/target")
    public ModelAndView show()
    {
        LOGGER.debug("com.eric.controller.TargetController.show ......");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "eric");
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
