package com.eric.service.impl.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-03 19:12
 * @since jdk-11.0.14
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "order_id", consumerGroup = "group_rocketmq")
public class RocketmqMessageListener implements RocketMQListener<String>
{
    @Override
    public void onMessage(String id)
    {
        log.warn("已完成订单[{}]短信发送业务", id);
        log.warn("-------------------------------------------");
    }
}
