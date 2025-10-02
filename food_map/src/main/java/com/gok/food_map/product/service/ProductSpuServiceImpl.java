package com.gok.food_map.product.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gok.food_map.product.dto.MerchantGetGoodsDto;
import com.gok.food_map.product.dto.ProductGetListDto;
import com.gok.food_map.product.entity.ProductSpu;

import com.gok.food_map.product.mapper.ProductSpuMapper;
import com.gok.food_map.product.vo.ProductSpuGetListVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author LeiNa
* @description 针对表【product_spu(商品SPU表)】的数据库操作Service实现
* @createDate 2025-10-01 22:04:55
*/
@Service
public class ProductSpuServiceImpl extends ServiceImpl<ProductSpuMapper, ProductSpu> implements IService<ProductSpu> {
    @Resource
    private ProductSpuMapper productSpuMapper;

    public List<String> getBanner(){
        QueryWrapper<ProductSpu> wrapper = new QueryWrapper<>();
        wrapper.select("main_Image");
        wrapper.orderByDesc("sales");
        wrapper.last("limit 6");
        List<ProductSpu> productSpus = productSpuMapper.selectList(wrapper);
        List<String> list = new ArrayList<>();
        productSpus.forEach(productSpu -> list.add(productSpu.getMainImage().toString()));
        return list;
    }
    public IPage<ProductSpuGetListVO> getMerchantProducts(MerchantGetGoodsDto dto) {
        IPage<ProductSpu> page = new Page<>(dto.getCurrentPage() == null ? 1 : dto.getCurrentPage(),
                dto.getPageSize() == null ? 8 : dto.getPageSize());
        LambdaQueryWrapper<ProductSpu> wrapper = new LambdaQueryWrapper<>();
        wrapper.select( ProductSpu::getSpuId,
                ProductSpu::getSpuName,
                ProductSpu::getSpuDesc,
                ProductSpu::getMerchantId,
                ProductSpu::getMainImage,
                ProductSpu::getProductCategory);
        wrapper.eq(dto.getMerchantId() != null,ProductSpu::getMerchantId, Long.valueOf(dto.getMerchantId()));
        return entityToVO(page, wrapper);
    }

    private IPage<ProductSpuGetListVO> entityToVO(IPage<ProductSpu> page, LambdaQueryWrapper<ProductSpu> wrapper) {
        IPage<ProductSpu> productSpus = productSpuMapper.selectPage(page,wrapper);
        IPage<ProductSpuGetListVO> productGetListVOIPage = new Page<>();
        BeanUtils.copyProperties(productSpus,productGetListVOIPage);
        productGetListVOIPage.setRecords(productSpus
                .getRecords()
                .stream()
                .map(productSpu -> {
                    ProductSpuGetListVO vo = new ProductSpuGetListVO();
                    BeanUtils.copyProperties(productSpu,vo);
                    vo.setSpuId(productSpu.getSpuId().toString());
                    vo.setSpuName(productSpu.getSpuName());
                    vo.setSpuDesc(productSpu.getSpuDesc());
                    vo.setMerchantId(productSpu.getMerchantId().toString());
                    vo.setMainImage(String.valueOf(productSpu.getMainImage()));
                    vo.setProductCategory(productSpu.getProductCategory());
                    return vo;
                }).toList());
        return  productGetListVOIPage;
    }

    public IPage<ProductSpuGetListVO> getProducts(ProductGetListDto dto) {
        IPage<ProductSpu> page = new Page<>(dto.getCurrentPage() == null ? 1 : dto.getCurrentPage(),
                                            dto.getPageSize() == null ? 8 : dto.getPageSize());
        LambdaQueryWrapper<ProductSpu> wrapper = new LambdaQueryWrapper<>();
        wrapper.select( ProductSpu::getSpuId,
                        ProductSpu::getSpuName,
                        ProductSpu::getSpuDesc,
                        ProductSpu::getMerchantId,
                        ProductSpu::getMainImage,
                        ProductSpu::getProductCategory);
        wrapper.like(dto.getSearchQuery() != null,ProductSpu::getProductCategory, dto.getSearchQuery())
                .or()
                .like(dto.getSearchQuery() != null,ProductSpu::getSpuName, dto.getSearchQuery());
        return entityToVO(page, wrapper);
    }
}




