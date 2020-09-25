package com.ld.product.service;

import com.ld.product.dataobject.ProductInfo;
import com.ld.product.dto.CartDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ld.product.ProductApplicationTests;

import java.util.Arrays;
import java.util.List;

@Component
class ProductInfoServiceTest extends ProductApplicationTests{

    @Autowired
    private ProductInfoService productInfoService;
    @Test
    void findAllByStatus() {
        List<ProductInfo> allByStatus = this.productInfoService.findAllByStatus();
    }

    @Test
    void deductStock(){
        CartDTO cartDTO = new CartDTO("157875196366160022",2);
        this.productInfoService.deductStock(Arrays.asList(cartDTO));
    }
}