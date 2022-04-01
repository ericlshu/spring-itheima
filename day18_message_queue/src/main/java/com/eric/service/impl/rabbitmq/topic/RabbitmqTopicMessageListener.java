package com.eric.service.impl.rabbitmq.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-01 20:20
 */
@Component
@Slf4j
public class RabbitmqTopicMessageListener {

    @RabbitListener(queues = {"topic_queue_1"})
    public void receive1(String id)
    {
        log.warn("topic_queue_1 : 已完成订单[{}]短信发送业务", id);
    }


    @RabbitListener(queues = {"topic_queue_2"})
    public void receive2(String id)
    {
        log.warn("topic_queue_2 : 已完成订单[{}]短信发送业务", id);
    }
}
