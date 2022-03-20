package com.eric.controller.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-20 19:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private boolean flag;
    private Object data;
    private String message;

    public Result(boolean flag)
    {
        this.flag = flag;
    }

    public Result(boolean flag, Object data)
    {
        this.flag = flag;
        this.data = data;
    }

    public Result(String message)
    {
        this.flag = false;
        this.message = message;
    }

}
