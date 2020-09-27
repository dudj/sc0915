package com.ld.product.common;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class ProductInfoOutPut {
    private String productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 单价
     */
    private BigDecimal productPrice;
    /**
     *  库存
     */
    private Integer productStock;
    /**
     * 描述
     */
    private String productDescription;
    /**
     * 图片
     */
    private String productIcon;
    /**
     * 商品状态,0正常1下架
     */
    private Integer productStatus;
    /**
     * 类型
     */
    private Integer categoryType;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
