package com.ld.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ld.order.dataobject.OrderDetail;
import com.ld.order.dto.OrderDTO;
import com.ld.order.enums.ResultEnum;
import com.ld.order.exception.OrderException;
import com.ld.order.verify.OrderVerify;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderVerifyToOrderDTOConverter {
    public static OrderDTO convert(OrderVerify orderVerify){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderVerify.getName());
        orderDTO.setBuyerAddress(orderVerify.getAddress());
        orderDTO.setBuyerOpenid(orderVerify.getOpenid());
        orderDTO.setBuyerPhone(orderVerify.getPhone());
        //list转化需要转类型
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try{
            orderDetailList = gson.fromJson(orderVerify.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        }catch (Exception e){
            log.error("【参数转化异常】:{}" + orderVerify.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
