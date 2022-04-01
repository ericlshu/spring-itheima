package com.eric.service.impl.base;

import com.eric.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-31 15:01
 */
@Service("messageServiceBase")
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final List<String> messageList = new ArrayList<>();

    @Override
    public void sendMessage(String id)
    {
        log.warn("待发送短信的订单[{}]已进入处理队列", id);
        messageList.add(id);
    }

    @Override
    public String doSendMessage()
    {
        String id = messageList.remove(0);
        log.warn("已完成订单[{}]短信发送业务", id);
        log.warn("--------------------------------------------------------------------");
        return null;
    }
}
