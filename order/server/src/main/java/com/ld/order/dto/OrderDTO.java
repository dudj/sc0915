package com.ld.order.dto;

import com.ld.order.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {
    private String orderId;
    private String buyerName;//买家姓名
    private String buyerPhone;//买家电话
    private String buyerAddress;//买家地址
    private String buyerOpenid;//唯一识别
    private BigDecimal orderAmount;//总金额
    private Integer orderStatus;//订单状态
    private Integer payStatus;//支付状态
    private List<OrderDetail> orderDetailList;
}
