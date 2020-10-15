package com.ld.product.repository;

import com.ld.product.common.ProductInfoOutPut;
import com.ld.product.dataobject.ProductInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Test
    void findByProductStatus() {
        List<ProductInfo> infos = this.productInfoRepository.findByProductStatus(0);
        for (ProductInfo item:infos) {
            System.out.println(item.getProductId()+"---"+item.getProductName());
        }
    }

    @Test
    void findByProductIdIn(){
        List<ProductInfo> infos = this.productInfoRepository.findByProductIdIn(Arrays.asList("111","222"));
        for (ProductInfo item:infos) {
            System.out.println(item.getProductId()+"---"+item.getProductName());
        }
    }
}