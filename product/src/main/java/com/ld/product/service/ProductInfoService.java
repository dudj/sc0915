package com.ld.product.service;

import com.ld.product.dataobject.ProductInfo;

import java.util.List;

public interface ProductInfoService {
    public List<ProductInfo> findAllByStatus();
}
