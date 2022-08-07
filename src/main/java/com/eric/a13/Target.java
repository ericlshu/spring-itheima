package com.eric.a13;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Target
{
    public void save()
    {
        log.debug("save()");
    }

    public void save(int i)
    {
        log.debug("save(int)");
    }

    public void save(long j)
    {
        log.debug("save(long)");
    }
}
