package com.eric.a10.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyService
{
    final public void foo()
    {
        log.debug("foo()");
        this.bar();
    }

    public void bar()
    {
        log.debug("bar()");
    }
}
