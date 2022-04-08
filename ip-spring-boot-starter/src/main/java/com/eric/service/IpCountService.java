package com.eric.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-08 21:03
 * @since jdk-11.0.14
 */
@Slf4j
public class IpCountService
{
    private final Map<String, Integer> ipCountMap = new HashMap<>(10);

    /**
     * 当前的request对象的注入工作由使用当前starter的工程提供自动装配
     */
    @Resource
    private HttpServletRequest httpServletRequest;

    public void count()
    {
        // 每次调用当前操作，就记录当前访问的IP，然后累加访问次数
        log.debug("<--- com.eric.service.IpCountService.count --->");
        // 1.获取当前操作的IP地址
        String ip = httpServletRequest.getRemoteAddr();
        log.debug("<--- ip = {} --->", ip);
        // 2.根据IP地址从Map取值，并递增
        ipCountMap.put(ip, ipCountMap.getOrDefault(ip, 0) + 1);
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void output()
    {
        log.warn("<--------- IP 访问监控 --------->");
        log.warn("+-----ip-address-----+--count--+");

        for (Map.Entry<String, Integer> entry : ipCountMap.entrySet())
        {
            log.info(String.format("|%18s  |  %-6d |", entry.getKey(), entry.getValue()));
        }
        log.info("+--------------------+---------+");
    }

    public static void main(String[] args)
    {
        new IpCountService().output();
    }
}
