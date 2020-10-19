package com.ld.order;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 测试 发送mq消息端
 */
@Component
public class MqSenderTest extends OrderApplicationTests
{
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send(){
        this.amqpTemplate.convertAndSend("myQueue", "now " + new Date());
    }
}
