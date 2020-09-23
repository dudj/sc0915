package com.ld.product.service.impl;

import com.ld.product.dataobject.ProductInfo;
import com.ld.product.enums.ProductStatusEnum;
import com.ld.product.repository.ProductInfoRepository;
import com.ld.product.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Override
    public List<ProductInfo> findAllByStatus() {
        List<ProductInfo> productInfos = this.productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());

        return productInfos;
    }
}
