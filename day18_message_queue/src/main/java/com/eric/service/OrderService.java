package com.eric.service;

import org.springframework.stereotype.Service;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-31 14:53
 */
@Service
public interface OrderService {
    /**
     * execute order
     *
     * @param id order id
     */
    void executeOrder(String id);
}
