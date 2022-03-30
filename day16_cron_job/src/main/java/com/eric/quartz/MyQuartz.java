package com.eric.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-30 13:17
 */
public class MyQuartz extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException
    {
        System.out.println("com.eric.quartz.MyQuartz.executeInternal ......");
    }
}
