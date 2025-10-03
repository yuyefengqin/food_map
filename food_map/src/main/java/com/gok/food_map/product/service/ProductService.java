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
//        IPage<ProductSpu> page = new Page<>(dto.getCurrent() == null ? 1 : dto.getCurrent(), dto.getSize() == null ? 20 : dto.getSize());
//        LambdaQueryWrapper<ProductsGetListVO> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(dto.getSpuId() != null, ProductsGetListVO::getSpuId, dto.getSpuId());
//        wrapper.like(StringUtils.hasText(dto.getProductCategory()),ProductsGetListVO::getProductCategory, dto.getProductCategory());
//        wrapper.like(StringUtils.hasText(dto.getMerchantId()),ProductsGetListVO::getMerchantId, dto.getMerchantId());
//        wrapper.like(StringUtils.hasText(dto.getSpuName()),ProductsGetListVO::getSpuName, dto.getSpuName());
//        wrapper.ge(dto.getCreateDate() != null, ProductsGetListVO::getCreateTime, dto.getCreateDate());
//        wrapper.like(StringUtils.hasText(dto.getShelfStatus()),ProductsGetListVO::getShelfStatus, dto.getShelfStatus());
//        wrapper.like(StringUtils.hasText(dto.getApprovalStatus()),ProductsGetListVO::getApprovalStatus, dto.getApprovalStatus());
        List<ProductsGetListVO> productsGetListVOList = new ArrayList<>();

        if (!dto.getSpuId().toString().isEmpty()){
            productsGetListVOList = productMapper.selectBy(dto.getSpuId().toString());
        } else {
            return null;
        }


        productsGetListVOList.forEach(System.out::println);
        IPage<ProductsGetListVO> res = new Page<>();
        BeanUtils.copyProperties(productsGetListVOList, res);
        res.setRecords(productsGetListVOList.stream().map(productsGetListVO -> {
            ProductsGetListVO vo = new ProductsGetListVO();
            BeanUtils.copyProperties(productsGetListVO, vo);
            return vo;
        }).toList());
        return res;
    }
}
