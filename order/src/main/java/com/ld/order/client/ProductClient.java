package com.ld.order.client;

import com.ld.order.dataobject.ProductInfo;
import com.ld.order.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product")
public interface ProductClient {
    @GetMapping("/msg")
    String productMsg();
    @PostMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);
    @PostMapping("/product/deductStock")
    void deductStock(@RequestBody List<CartDTO> cartDTOList);
}
