package com.ld.product.service;

import com.ld.product.common.DeductStockInPut;
import com.ld.product.dataobject.ProductInfo;
import com.ld.product.dto.CartDTO;
import org.junit.Test;
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
        DeductStockInPut deductStockInPut = new DeductStockInPut("157875196366160022",2);
        this.productInfoService.deductStock(Arrays.asList(deductStockInPut));
    }
}