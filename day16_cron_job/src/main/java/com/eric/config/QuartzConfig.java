package com.eric.config;

import com.eric.quartz.MyQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-30 13:18
 */
@Configuration
public class QuartzConfig {

    /**
     * 绑定具体的工作
     */
    @Bean
    public JobDetail printJobDetail()
    {
        return JobBuilder
                .newJob(MyQuartz.class)
                .storeDurably()
                .build();
    }

    /**
     * 绑定对应的工作明细
     */
    @Bean
    public Trigger printJobTrigger()
    {
        ScheduleBuilder<CronTrigger> scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(printJobDetail())
                .withSchedule(scheduleBuilder)
                .build();
    }
}
