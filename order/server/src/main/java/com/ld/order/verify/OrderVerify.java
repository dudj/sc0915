package com.ld.order.verify;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 订单认证
 */
@Data
public class OrderVerify {
    @NotEmpty(message = "名称不能为空")
    private String name;

    @NotEmpty(message = "手机号不能为空")
    private String phone;

    @NotEmpty(message = "地址不能为空")
    private String address;

    @NotEmpty(message = "微信唯一识别不能为空")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
