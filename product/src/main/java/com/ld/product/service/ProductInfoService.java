package com.ld.product.service;

import com.ld.product.dataobject.ProductInfo;
import com.ld.product.dto.CartDTO;

import java.util.List;

public interface ProductInfoService {
    public List<ProductInfo> findAllByStatus();

    /**
     * \根据商品ID 查询商品详情
     * @param productIdList
     * @return
     */
    List<ProductInfo> findAllById(List<String> productIdList);

    /**
     * 扣库存
     * @param cartDTOList
     */
    void deductStock(List<CartDTO> cartDTOList);
}
