package com.ld.order.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
@Table(name = "order_master")
public class OrderMaster {
    @Id
    private String orderId;
    private String buyerName;//买家姓名
    private String buyerPhone;//买家电话
    private String buyerAddress;//买家地址
    private String buyerOpenid;//唯一识别
    private BigDecimal orderAmount;//总金额
    private Integer orderStatus;//订单状态
    private Integer payStatus;//支付状态
    private Date createTime;
    private Date updateTime;
}
