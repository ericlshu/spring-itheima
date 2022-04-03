package com.eric.service.impl.kafka;

import com.eric.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-03 22:07
 * @since jdk-11.0.14
 */
@Slf4j
@Service("kafkaMessageService")
public class KafkaMessageServiceImpl implements MessageService
{
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(String id)
    {
        log.warn("待发送短信的订单[{}]已进入处理队列", id);
        kafkaTemplate.send("eric",id);
    }

    @Override
    public String doSendMessage()
    {
        return null;
    }
}
