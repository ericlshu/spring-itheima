package com.eric.service.impl.rabbitmq.direct;

import com.eric.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-01 15:47
 */
@Slf4j
@Service("rabbitmqDirectMessageService")
public class RabbitmqDirectMessageServiceImpl implements MessageService {

    @Resource
    public AmqpTemplate amqpTemplate;

    @Override
    public void sendMessage(String id)
    {
        log.warn("待发送短信的订单[{}]已进入处理队列", id);
        amqpTemplate.convertAndSend("directExchange", "direct", id);
    }

    @Override
    public String doSendMessage()
    {
        return null;
    }
}
