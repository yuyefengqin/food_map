package com.gok.food_map.product.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gok.food_map.file.service.FileService;
import com.gok.food_map.product.dto.ProductsGetListDto;
import com.gok.food_map.product.entity.ProductSpu;
import com.gok.food_map.product.mapper.ProductMapper;
import com.gok.food_map.product.mapper.ProductSpuMapper;
import com.gok.food_map.product.vo.ProductsGetListVO;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class ProductService {
    //商品管理系统
    private final ProductSpuMapper productSpuMapper;
    private final ProductMapper productMapper;
    private final FileService fileService;
    public IPage<ProductsGetListVO> getList(ProductsGetListDto dto) {
        IPage<ProductSpu> page = new Page<>(dto.getCurrent() == null ? 1 : dto.getCurrent(), dto.getSize() == null ? 20 : dto.getSize());
        LambdaQueryWrapper<ProductSpu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(dto.getSpuId() != null, ProductSpu::getSpuId, dto.getSpuId());
        wrapper.like(StringUtils.hasText(dto.getProductCategory()),ProductSpu::getProductCategory, dto.getProductCategory());
        wrapper.like(StringUtils.hasText(dto.getMerchantId()),ProductSpu::getMerchantId, dto.getMerchantId());
        wrapper.like(StringUtils.hasText(dto.getSpuName()),ProductSpu::getSpuName, dto.getSpuName());
        wrapper.ge(dto.getCreateDate() != null, ProductSpu::getCreateTime, dto.getCreateDate());
        wrapper.like(StringUtils.hasText(dto.getShelfStatus()),ProductSpu::getShelfStatus, dto.getShelfStatus());
        wrapper.like(StringUtils.hasText(dto.getApprovalStatus()),ProductSpu::getApprovalStatus, dto.getApprovalStatus());
        page = productSpuMapper.selectPage(page, wrapper);

        List<ProductsGetListVO> productsGetListVOList = productMapper.selectBy();
        IPage<ProductsGetListVO> res = new Page<>();
        BeanUtils.copyProperties(productsGetListVOList, res);
        res.setRecords(page.getRecords().stream().map(product ->{
                    ProductsGetListVO vo= new ProductsGetListVO();
                    BeanUtils.copyProperties(product, vo);
                    vo.setSpuId(product.getSpuId().toString());
                    vo.setMerchantId(product.getMerchantId().toString());
                    vo.setCreateTime(product.getCreateTime());
                    vo.setMainImage(product.getMainImage() == null ? null : product.getMainImage().toString());
                    return vo;
                }
        ).toList());
        return res;
    }
}
