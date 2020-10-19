package com.ld.order.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/mqsender")
public class MqSenderController {

    @Autowired
    private AmqpTemplate amqpTemplate;
    @GetMapping("/send")
    public void send(){
        amqpTemplate.convertAndSend("myQueue","date:" + new Date());
    }

    @GetMapping("/computer")
    public void computer(){
        amqpTemplate.convertAndSend("myOrder","computer","computer date:" + new Date());
    }
}
