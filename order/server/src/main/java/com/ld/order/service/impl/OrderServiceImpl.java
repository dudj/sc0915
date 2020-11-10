package com.ld.order.service.impl;

import com.ld.order.dataobject.OrderDetail;
import com.ld.order.dataobject.OrderMaster;
import com.ld.order.dto.OrderDTO;
import com.ld.order.enums.OrderStatusEnum;
import com.ld.order.enums.PayStatusEnum;
import com.ld.order.enums.ResultEnum;
import com.ld.order.exception.OrderException;
import com.ld.order.repository.OrderDetailRepository;
import com.ld.order.repository.OrderMasterRepository;
import com.ld.order.service.OrderService;
import com.ld.order.utils.KeyUtil;
import com.ld.product.client.ProductClient;
import com.ld.product.common.DeductStockInPut;
import com.ld.product.common.ProductInfoOutPut;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
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
        //商品列表详情
        List<String> productIdList = orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        log.info("获取商品列表详情");
        List<ProductInfoOutPut> productInfoList = this.productClient.listForOrder(productIdList);

        //扣库存
        List<DeductStockInPut> deductStockInPutList = orderDTO.getOrderDetailList().stream().map(e->new DeductStockInPut(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        this.productClient.deductStock(deductStockInPutList);

        //计算总金额 --- 同时赋值订单详情
        List<OrderDetail> orderDetailList = new ArrayList<>();
        BigDecimal total = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            for (ProductInfoOutPut productInfo : productInfoList){
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

    @Override
    public OrderDTO finish(String orderId) {
        //1.查询是否存在订单
        Optional<OrderMaster> orderMasterOptional = this.orderMasterRepository.findById(orderId);
        if(!orderMasterOptional.isPresent()){
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        //2.判断订单状态
        OrderMaster orderMaster = orderMasterOptional.get();
        if(!OrderStatusEnum.NEW.getCode().equals(orderMaster.getOrderStatus())){
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //3.修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        this.orderMasterRepository.save(orderMaster);

        //查询订单详情
        List<OrderDetail> orderDetailList = this.orderDetailRepository.findAllByOrderId(orderMaster.getOrderId());
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new OrderException(ResultEnum.ORDER_DETAIL_ERROR);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
