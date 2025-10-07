package com.gok.food_map.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gok.food_map.product.dto.*;
import com.gok.food_map.product.service.ProductService;
import com.gok.food_map.product.service.ProductSkuServiceImpl;
import com.gok.food_map.product.service.ProductSpuServiceImpl;
import com.gok.food_map.product.vo.ProductSkuGetListVO;
import com.gok.food_map.product.vo.ProductSpuGetListVO;
import com.gok.food_map.product.vo.ProductsGetListVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/MGoods"})
public class ProductController {
    @Resource
    private ProductSpuServiceImpl productSpuService;
    @Resource
    private ProductSkuServiceImpl productSkuService;

    public ProductController() {
    }

    @PostMapping({"/getGoodsList"})
    public IPage<ProductSpuGetListVO> getGoodsList(@RequestBody ProductGetListDto dto) {
        return this.productSpuService.getProducts(dto);
    }

    @PostMapping({"/getSkuBySpuId"})
    public IPage<ProductSkuGetListVO> getSkuBySpuId(@RequestBody ProductSpuIdDto dto) {
        return this.productSkuService.getProductSkuBySpuId(dto.getSpuId());
    }

    @PostMapping({"/getMerchantsGoodsById"})
    public IPage<ProductSpuGetListVO> getMerchantGoods(@RequestBody MerchantGetGoodsDto dto) {
        return this.productSpuService.getMerchantProducts(dto);
    }
    @PostMapping("/getBanner")
    public List<String> geBanner() {
        return productSpuService.getBanner();
    }


    //查找
    @Resource
    private ProductService productService;
    @PostMapping("/getList")
    public IPage<ProductsGetListVO> getList(@RequestBody ProductsGetListDto dto) {
        return productService.getList(dto);
    }

    //新增
    @PostMapping("/add")
    public void add(@RequestBody ProductsSpuDto dto) {
        productSpuService.add(dto);
    }

    //编辑
    @PostMapping("/edit")
    public void edit(@RequestBody ProductsSpuDto dto) {
        productSpuService.edit(dto);
    }

    //跳转
    @RequestMapping("/detail")
    public String detail() {
        return "product_detail";
    }
}