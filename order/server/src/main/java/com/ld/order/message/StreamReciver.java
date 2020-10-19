package com.ld.order.message;

import com.ld.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReciver {
//    public void process(Object message){
//        log.info("StreamReciver:{}",message);
//    }
    /**
     * 接收 orderDTO对象 消息
     */
    @StreamListener(StreamClient.INPUT)
    @SendTo(StreamClient.INPUT2)
    public String process(OrderDTO message){
        log.info("StreamReciver:{}",message);
        return "reciver.";
    }

    @StreamListener(StreamClient.INPUT2)
    public void process2(String message){
        log.info("StreamReciver2:{}",message);
    }
}
