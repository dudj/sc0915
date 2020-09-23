package com.ld.product.repository;

import com.ld.product.dataobject.ProductInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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
}