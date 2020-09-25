package com.ld.product.controller;

import com.ld.product.VO.ProductInfoVO;
import com.ld.product.VO.ProductVO;
import com.ld.product.VO.ResultVO;
import com.ld.product.dataobject.ProductCategory;
import com.ld.product.dataobject.ProductInfo;
import com.ld.product.dto.CartDTO;
import com.ld.product.service.ProductCategoryService;
import com.ld.product.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;
    /**
     * 1.查询所有上架的商品
     * 2.查询类目列表
     * 3.
     * 4.构造数据
     */
    @GetMapping("/list")
    public ResultVO<ProductVO> list(){
        List<ProductInfo> infoList = this.productInfoService.findAllByStatus();

        List<Integer> categoryTypeList = infoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        List<ProductCategory> categoryList = this.productCategoryService.findByCategoryTypeIn(categoryTypeList);
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory:categoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo:infoList){
                ProductInfoVO productInfoVO = new ProductInfoVO();
                if(productCategory.getCategoryType().equals(productInfo.getCategoryType())){
                    /*productInfoVO.setProductId(productInfo.getProductId());
                    productInfoVO.setProductName(productInfo.getProductName());
                    productInfoVO.setProductDescription(productInfo.getProductDescription());
                    productInfoVO.setProductIcon(productInfo.getProductIcon());
                    productInfoVO.setProductPrice(productInfo.getProductPrice());*/
                    BeanUtils.copyProperties(productInfo,productInfoVO);//相当于上面的赋值
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setFoods(productInfoVOList);
            productVOList.add(productVO);
        }
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(productVOList);
        return resultVO;
    }

    /**
     * 根据产品id返回产品详情(用于订单功能)
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList){
        List<ProductInfo> productInfoList = new ArrayList<>();
        productInfoList = this.productInfoService.findAllById(productIdList);
        return productInfoList;
    }
    @PostMapping("/deductStock")
    public void deductStock(@RequestBody List<CartDTO> cartDTOList){
        this.productInfoService.deductStock(cartDTOList);
    }
}
