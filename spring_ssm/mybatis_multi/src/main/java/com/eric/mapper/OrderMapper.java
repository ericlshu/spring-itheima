package com.eric.mapper;

import com.eric.domain.Order;

import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-13 15:33
 */
public interface OrderMapper {
    /**
     * find all orders
     *
     * @return order list
     */
    List<Order> findAll();
}
