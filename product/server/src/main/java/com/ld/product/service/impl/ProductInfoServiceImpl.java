package com.ld.product.service.impl;

import com.ld.product.common.DeductStockInPut;
import com.ld.product.common.ProductInfoOutPut;
import com.ld.product.dataobject.ProductInfo;
import com.ld.product.dto.CartDTO;
import com.ld.product.enums.ProductStatusEnum;
import com.ld.product.enums.ResultEnum;
import com.ld.product.exception.ProductException;
import com.ld.product.repository.ProductInfoRepository;
import com.ld.product.service.ProductInfoService;
import com.ld.product.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findAllByStatus() {
        List<ProductInfo> productInfos = this.productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());

        return productInfos;
    }

    @Override
    public List<ProductInfoOutPut> findAllById(List<String> productIdList) {
        //进行转化
        return this.productInfoRepository.findByProductIdIn(productIdList).stream()
                .map(e -> {
                    ProductInfoOutPut productInfoOutPut = new ProductInfoOutPut();
                    BeanUtils.copyProperties(e,productInfoOutPut);
                    return productInfoOutPut;
                }).collect(Collectors.toList());
    }

    @Override
    public void deductStock(List<DeductStockInPut> deductStockInPutList) {
        //遍历 1.判断商品是否存在 2.判断库存是否满足 3.扣除库存
        List<ProductInfo> productInfoList = deductStockProcess(deductStockInPutList);
        List<ProductInfoOutPut> productInfoOutPutList = productInfoList.stream().map(e -> {
            ProductInfoOutPut outPut = new ProductInfoOutPut();
            BeanUtils.copyProperties(e, outPut);
            return outPut;
        }).collect(Collectors.toList());
        //发送扣库存消息 mq
        log.info("产品发送扣除库消息");
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutPutList));
    }

    //注意：发送mq扣除库存的消息 一定是在完成整个购物车扣库存逻辑之后，否则中间出现异常，数据库消息不会更改，但是会发送mq消息
    @Transactional
    public List<ProductInfo> deductStockProcess(List<DeductStockInPut> deductStockInPutList){
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DeductStockInPut deductStockInPut:deductStockInPutList){
            Optional<ProductInfo> productInfoOptional = this.productInfoRepository.findById(deductStockInPut.getProductId());
            if(!productInfoOptional.isPresent()){
                log.error("【商品服务】：商品不存在");
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXISTS);
            }
            ProductInfo productInfo = productInfoOptional.get();
            Integer stock = productInfo.getProductStock() - deductStockInPut.getProductQuantity();
            if(stock < 0){
                log.error("【商品服务】：" + ResultEnum.PRODUCT_STOCK_NOT_ENOUGH.getMessage());
                throw new ProductException(ResultEnum.PRODUCT_STOCK_NOT_ENOUGH);
            }
            productInfo.setProductStock(stock);
            this.productInfoRepository.save(productInfo);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
