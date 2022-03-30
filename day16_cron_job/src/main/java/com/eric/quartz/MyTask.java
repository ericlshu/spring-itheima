package com.eric.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-30 14:26
 */
@Slf4j
@Component
public class MyTask {

    @Scheduled(cron = "0/2 * * * * ?")
    public void print()
    {
        log.info("[{}] -  print is running ... ", Thread.currentThread().getName());
    }
}
