package com.ld.product.repository;

import com.ld.product.dataobject.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Test
    void findByCategoryTypeIn() {
        List<ProductCategory> type = this.productCategoryRepository.findByCategoryTypeIn(Arrays.asList(11,22));
    }
}