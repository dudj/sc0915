package com.ld.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
/**
 * 熔断、降级
 */
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {
    @GetMapping("getProductList")
//    @HystrixCommand(defaultFallback = "fallback")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    /**
     * 不是服务之间调用不到，才会降级，自己的程序如果出现了异常也可以降级
     */
    public String getProductList(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8095/product/listForOrder",
                Arrays.asList("157875196366160022"),
                String.class
        );
//        throw new RuntimeException("异常了，小老弟");
    }
    public String fallback(){
        return "卧槽，人太多了，你等会吧！";
    }
    public String defaultFallback(){
        return "默认提示：卧槽，人太多了，你等会吧！";
    }
}
