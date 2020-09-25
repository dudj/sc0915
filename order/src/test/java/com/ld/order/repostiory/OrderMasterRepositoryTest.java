package com.ld.order.repostiory;

import com.ld.order.OrderApplicationTests;
import com.ld.order.dataobject.OrderMaster;
import com.ld.order.enums.OrderStatusEnum;
import com.ld.order.enums.PayStatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@Component
class OrderMasterRepositoryTest  extends OrderApplicationTests {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Test
    public void test(){
        OrderMaster orderMaster = new OrderMaster();

        orderMaster.setOrderId("123123");
        orderMaster.setBuyerName("林子懿");
        orderMaster.setBuyerAddress("佳隆");
        orderMaster.setBuyerOpenid("wx12312313");
        orderMaster.setBuyerPhone("130200222222");
        orderMaster.setOrderAmount(new BigDecimal(22.6));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.UNPAID.getCode());
        OrderMaster result = this.orderMasterRepository.save(orderMaster);

    }
}