package com.eric.service.impl.rabbitmq.direct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-01 20:20
 */
// @Component
@Slf4j
public class RabbitmqDirectMessageListener {

    @RabbitListener(queues = {"direct_queue"})
    public void receive(String id)
    {
        log.warn("已完成订单[{}]短信发送业务", id);
        log.warn("-------------------------------------------");
    }
}
