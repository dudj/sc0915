package com.ld.order.enums;

import lombok.Getter;

@Getter
/**
 * 支付状态
 */
public enum PayStatusEnum {
    PAID(1,"已支付"),
    UNPAID(0,"未支付");

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
