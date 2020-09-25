package com.ld.order.enums;

import lombok.Getter;

@Getter
/**
 * 订单状态
 */
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"已支付"),
    CANCEL(2,"取消");

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
