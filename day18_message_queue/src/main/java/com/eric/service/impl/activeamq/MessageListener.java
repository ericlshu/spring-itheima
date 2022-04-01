package com.eric.service.impl.activeamq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-01 13:02
 */
@Slf4j
@Component
public class MessageListener {

    /**
     * @ SendTo 将当前方法的返回值发送到其他消息队列中
     */
    @JmsListener(destination = "order.queue.id")
    @SendTo("order.other.queue.id")
    public String receive(String id)
    {
        log.warn("已完成订单[{}]短信发送业务", id);
        return "new:" + id;
    }
}
