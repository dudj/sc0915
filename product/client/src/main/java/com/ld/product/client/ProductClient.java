package com.ld.product.client;

import com.ld.product.common.DeductStockInPut;
import com.ld.product.common.ProductInfoOutPut;
//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product",fallback = ProductClient.ProductClientFallback.class)
public interface ProductClient {
    @PostMapping("/product/listForOrder")
    List<ProductInfoOutPut> listForOrder(@RequestBody List<String> productIdList);
    @PostMapping("/product/deductStock")
    void deductStock(@RequestBody List<DeductStockInPut> deductStockInPutList);

    //外部调用 如果出错 ，会降级
    @Component
    static class ProductClientFallback implements ProductClient{

        @Override
        public List<ProductInfoOutPut> listForOrder(List<String> productIdList) {
            return null;
        }

        @Override
        public void deductStock(List<DeductStockInPut> deductStockInPutList) {

        }
    }
}
