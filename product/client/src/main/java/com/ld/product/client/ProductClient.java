package com.ld.product.client;

import com.ld.product.common.DeductStockInPut;
import com.ld.product.common.ProductInfoOutPut;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product")
public interface ProductClient {
    @PostMapping("/product/listForOrder")
    List<ProductInfoOutPut> listForOrder(@RequestBody List<String> productIdList);
    @PostMapping("/product/deductStock")
    void deductStock(@RequestBody List<DeductStockInPut> deductStockInPutList);
}
