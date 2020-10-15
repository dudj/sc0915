package com.ld.order.repostiory;

import com.ld.order.OrderApplicationTests;
import com.ld.order.dataobject.OrderDetail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class OrderDetailRepositoryTest extends OrderApplicationTests{
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Test
    public void testsave(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12313");
        orderDetail.setOrderId("123123");
        orderDetail.setProductIcon("http://aaaaaa");
        orderDetail.setProductId("123123");
        orderDetail.setProductName("皮蛋");
        orderDetail.setProductPrice(new BigDecimal(5.55));
        orderDetail.setProductQuantity(1);
        OrderDetail result = this.orderDetailRepository.save(orderDetail);
    }
}