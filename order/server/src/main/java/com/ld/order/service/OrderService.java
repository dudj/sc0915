package com.ld.order.service;

import com.ld.order.dto.OrderDTO;

public interface OrderService {
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 完结订单 (只能卖家操作)
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);
}
