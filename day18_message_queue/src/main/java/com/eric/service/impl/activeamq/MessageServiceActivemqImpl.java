package com.eric.service.impl.activeamq;

import com.eric.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-01 12:51
 */
@Slf4j
@Service("messageServiceActivemq")
public class MessageServiceActivemqImpl implements MessageService {

    @Autowired
    private JmsMessagingTemplate messagingTemplate;

    @Override
    public void sendMessage(String id)
    {
        log.warn("待发送短信的订单[{}]已进入处理队列", id);
        messagingTemplate.convertAndSend("order.queue.id", id);
    }

    @Override
    public String doSendMessage()
    {
        messagingTemplate.receiveAndConvert("order.queue.id", String.class);
        log.warn("--------------------------------------------------------------------");
        return null;
    }
}
