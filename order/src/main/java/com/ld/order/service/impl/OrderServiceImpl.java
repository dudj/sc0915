package com.ld.order.service.impl;

import com.ld.order.client.ProductClient;
import com.ld.order.dataobject.OrderDetail;
import com.ld.order.dataobject.OrderMaster;
import com.ld.order.dataobject.ProductInfo;
import com.ld.order.dto.CartDTO;
import com.ld.order.dto.OrderDTO;
import com.ld.order.enums.OrderStatusEnum;
import com.ld.order.enums.PayStatusEnum;
import com.ld.order.repostiory.OrderDetailRepository;
import com.ld.order.repostiory.OrderMasterRepository;
import com.ld.order.service.OrderService;
import com.ld.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;

    /**
     * 1.商品详情
     * 2.计算总金额
     * 3.扣库存
     * 4.入库
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.generateUniqueKey();
        //商品详情
        List<String> productIdList = orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfo> productInfoList = this.productClient.listForOrder(productIdList);

        //扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        this.productClient.deductStock(cartDTOList);

        //计算总金额 --- 同时赋值订单详情
        List<OrderDetail> orderDetailList = new ArrayList<>();
        BigDecimal total = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            for (ProductInfo productInfo : productInfoList){
                if(productInfo.getProductId().equals(orderDetail.getProductId())){
                    BigDecimal price = new BigDecimal(orderDetail.getProductQuantity()).multiply(productInfo.getProductPrice());
                    total = total.add(price);
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setProductPrice(price);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.generateUniqueKey());
                    //详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(total);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.UNPAID.getCode());
        this.orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
