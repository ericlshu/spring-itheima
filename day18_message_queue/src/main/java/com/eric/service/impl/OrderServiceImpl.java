package com.eric.service.impl;

import com.eric.service.MessageService;
import com.eric.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-31 14:58
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private MessageService messageService;

    @Override
    public void executeOrder(String id)
    {
        log.warn("订单处理开始--->");
        messageService.sendMessage(id);
        log.warn("订单处理结束<---");
        log.warn("--------------------------------------------------------------------");
    }
}
