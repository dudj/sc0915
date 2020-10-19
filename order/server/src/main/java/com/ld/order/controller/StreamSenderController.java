package com.ld.order.controller;

import com.ld.order.dto.OrderDTO;
import com.ld.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/sendMessage")
public class StreamSenderController {
    @Autowired
    private StreamClient streamClient;

//    @GetMapping("/process")
//    public void process(){
//        this.streamClient.output().send(MessageBuilder.withPayload("now : "+ new Date()).build());
//    }

    /**
     * 发送orderDTO对象
      */
    @GetMapping("/process")
    public void process(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("123123");
        this.streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
