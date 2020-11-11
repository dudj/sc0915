package com.ld.order.verify;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * 订单认证
 */
@Data
@ApiModel(value = "订单验证类")
public class OrderVerify {
    @NotEmpty(message = "名称不能为空")
    @ApiModelProperty(value = "名称")
    private String name;

    @NotEmpty(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @NotEmpty(message = "地址不能为空")
    @ApiModelProperty(value = "地址")
    private String address;

    @NotEmpty(message = "微信唯一识别不能为空")
    @ApiModelProperty(value = "微信唯一识别码")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    @ApiModelProperty(value = "购物车列表")
    private String items;
}
