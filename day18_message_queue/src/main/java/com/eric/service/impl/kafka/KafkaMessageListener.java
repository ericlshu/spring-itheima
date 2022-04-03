package com.eric.service.impl.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-03 22:13
 * @since jdk-11.0.14
 */
@Slf4j
@Component
public class KafkaMessageListener
{
    @KafkaListener(topics = {"eric"})
    public void onMessage(ConsumerRecord<?, ?> record)
    {
        log.warn("已完成订单[{}]短信发送业务", record.value());
        log.warn("-------------------------------------------");
    }
}
