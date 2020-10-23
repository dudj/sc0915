package com.ld.order.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ld.order.utils.JsonUtil;
import com.ld.product.common.ProductInfoOutPut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ProductInfoReceiver {
    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message){
        /*//message ===> 转换成 productInfoOut
        ProductInfoOutPut outPut = (ProductInfoOutPut) JsonUtil.fromJson(message, ProductInfoOutPut.class);
        log.info("从队列{}接收到消息:{}","productInfo",outPut);
        //将接收到的消息存储到redis中
        stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE,outPut.getProductId()),String.valueOf(outPut.getProductStock()));*/
        //将list的json字符串转成对象
        List<ProductInfoOutPut> productInfoOutPutList = (List<ProductInfoOutPut>) JsonUtil.fromJson(message, new TypeReference<List<ProductInfoOutPut>>() {});

        log.info("从队列{}接收到消息:{}","productInfo",productInfoOutPutList);

        for (ProductInfoOutPut productInfoOutPut : productInfoOutPutList) {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfoOutPut.getProductId()), String.valueOf(productInfoOutPut.getProductStock()));
        }
    }
}
