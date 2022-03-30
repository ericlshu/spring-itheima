package com.eric.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-30 13:17
 */
@Slf4j
public class MyQuartz extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException
    {
        log.warn("[{}] - executeInternal is running ... ", Thread.currentThread().getName());
    }
}
