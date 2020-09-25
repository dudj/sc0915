package com.ld.order.controller;

import com.ld.order.client.ProductClient;
import com.ld.order.dataobject.ProductInfo;
import com.ld.order.dto.CartDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class ClientController {
    //第二种
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    //第三种
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProductClient productClient;
    @GetMapping("/getProductMsg")
    public String getProductMsg(){
        //第一种
        /*RestTemplate restTemplate = new RestTemplate();
        String object = restTemplate.getForObject("http://localhost:8080/msg", String.class);*/
        //第二种方式
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort()) + "/msg";
//        RestTemplate restTemplate = new RestTemplate();
//        String object = restTemplate.getForObject(url, String.class);
        //第三种方式
//        String object = restTemplate.getForObject("http://PRODUCT/msg", String.class);
//        log.info("response={}"+object);
        //feign
        String object = productClient.productMsg();
        return object;
    }

    @GetMapping("/getProductList")
    public String getProductList(){
        List<ProductInfo> productInfoList = this.productClient.listForOrder(Arrays.asList("157875196366160022","157875227953464068"));
        log.info("response={}",productInfoList);
        return "ok";

    }

    @GetMapping("deductStock")
    public String deductStock(){
        this.productClient.deductStock(Arrays.asList(new CartDTO("157875196366160022",3)));
        log.info("response={}");
        return "ok";
    }
}
