package com.eric.a08;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Slf4j
@Scope("session")
@Component
public class BeanForSession
{
    @PreDestroy
    public void destroy()
    {
        log.debug("destroy");
    }
}
