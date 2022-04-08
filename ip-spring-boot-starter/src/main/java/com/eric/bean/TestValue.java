package com.eric.bean;

import org.springframework.stereotype.Component;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-08 22:40
 * @since jdk-11.0.14
 */
@Component("abc")
public class TestValue
{
    private Integer interval = 1;

    public Integer getInterval()
    {
        return interval;
    }

    public void setInterval(Integer interval)
    {
        this.interval = interval;
    }
}
