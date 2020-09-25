package com.ld.product.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXISTS(1,"商品不存在"),
    PRODUCT_STOCK_NOT_ENOUGH(2,"该商品库存不足"),
    ;
    private Integer code;
    private String message;
    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
