package com.ld.product.service;

import com.ld.product.common.DeductStockInPut;
import com.ld.product.common.ProductInfoOutPut;
import com.ld.product.dataobject.ProductInfo;

import java.util.List;

public interface ProductInfoService {
    List<ProductInfo> findAllByStatus();

    /**
     * \根据商品ID 查询商品详情
     * @param productIdList
     * @return
     */
    List<ProductInfoOutPut> findAllById(List<String> productIdList);

    /**
     * 扣库存
     * @param deductStockInPutList
     */
    void deductStock(List<DeductStockInPut> deductStockInPutList);
}
