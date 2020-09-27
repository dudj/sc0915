package com.ld.product.common;

import lombok.Data;

@Data
public class DeductStockInPut {
    private String productId;
    private Integer productQuantity;
    public DeductStockInPut() {
    }
    public DeductStockInPut(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
