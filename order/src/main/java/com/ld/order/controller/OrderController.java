package com.ld.order.controller;

import com.ld.order.VO.ResultVO;
import com.ld.order.converter.OrderVerifyToOrderDTOConverter;
import com.ld.order.dto.OrderDTO;
import com.ld.order.enums.ResultEnum;
import com.ld.order.exception.OrderException;
import com.ld.order.service.OrderService;
import com.ld.order.utils.ResultUtil;
import com.ld.order.verify.OrderVerify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVO create(@Valid OrderVerify orderVerify, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】：参数有误，orderVerify={}",orderVerify);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        //orderVerify--->orderDto
        OrderDTO orderDTO = OrderVerifyToOrderDTOConverter.convert(orderVerify);
        //验证购物车
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】：购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDTO);

        Map map = new HashMap<>();
        map.put("orderId",result.getOrderId());
        return ResultUtil.success(map);
    }
}
