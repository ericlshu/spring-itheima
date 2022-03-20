package com.eric.controller.utils;

import org.apache.el.parser.AstFalse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Description :
 * Spring MVC Exception handler
 *
 * @author Eric L SHU
 * @date 2022-03-20 21:15
 */
@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex)
    {
        ex.printStackTrace();
        return new Result("服务器异常，请稍后重试！");
    }
}
