package com.eric.a09.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyService
{
    public static void foo()
    {
        log.debug("foo()");
    }
}
