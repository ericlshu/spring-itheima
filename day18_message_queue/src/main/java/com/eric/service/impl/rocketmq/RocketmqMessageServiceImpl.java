package com.eric.service.impl.rocketmq;

import com.eric.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-01 15:47
 */
@Slf4j
@Service("rocketmqMessageService")
public class RocketmqMessageServiceImpl implements MessageService
{
    @Autowired
    public RocketMQTemplate rocketMQTemplate;

    @Override
    public void sendMessage(String id)
    {
        log.warn("待发送短信的订单[{}]已进入处理队列", id);

        // 以下为同步调用，真实场景中多为异步调用
        // rocketMQTemplate.convertAndSend("order_id", id);
        
        SendCallback sendCallback = new SendCallback()
        {
            @Override
            public void onSuccess(SendResult sendResult)
            {
                log.info("消息[{}]发送成功", id);
            }

            @Override
            public void onException(Throwable e)
            {
                log.error("消息[{}]发送失败", id);
            }
        };
        rocketMQTemplate.asyncSend("order_id", id, sendCallback);
    }

    @Override
    public String doSendMessage()
    {
        return null;
    }
}
