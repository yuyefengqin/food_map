package com.gok.food_map.product.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gok.food_map.product.entity.ProductSku;
import com.gok.food_map.product.mapper.ProductSkuMapper;
import com.gok.food_map.product.vo.ProductSkuGetListVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author LeiNa
* @description 针对表【product_sku(商品SKU表)】的数据库操作Service实现
* @createDate 2025-10-01 22:04:55
*/
@Service
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSku> implements IService<ProductSku> {
    @Resource
    private ProductSkuMapper productSkuMapper;

    public IPage<ProductSkuGetListVO> getProductSkuBySpuId(String spuId) {

        List<ProductSku> productSkus = productSkuMapper.selectById(spuId);
        IPage<ProductSkuGetListVO> productGetListVOIPage = new Page<>();
        BeanUtils.copyProperties(productSkus, productGetListVOIPage);
        productGetListVOIPage.setRecords(
                productSkus
                .stream()
                .map(productSku -> {
                    ProductSkuGetListVO vo = new ProductSkuGetListVO();
                    BeanUtils.copyProperties(productSku, vo);
                    vo.setSkuId(productSku.getSkuId().toString());
                    vo.setSpuId(productSku.getSpuId().toString());
                    vo.setSpecsPrice(productSku.getSpecsPrice());
                    vo.setElementSpecs(productSku.getElementSpecs());
                    vo.setImageUrl(productSku.getImageUrl());
                    return vo;
                }).toList());
        return productGetListVOIPage;
    }
}




