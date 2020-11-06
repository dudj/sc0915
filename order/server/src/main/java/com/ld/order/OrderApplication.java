package com.ld.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
@EnableFeignClients(basePackages = "com.ld.product.*")
@SpringCloudApplication
/**
 * SpringCloudApplication注解 包含了
 * @SpringBootApplication
 * @EnableDiscoveryClient
 * @EnableCircuitBreaker
 * 这三个
 */
@ComponentScan(basePackages = "com.ld")
@EnableHystrixDashboard
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
