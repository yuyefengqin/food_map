package com.gok.food_map.product.service;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gok.food_map.exception.ResponseEnum;
import com.gok.food_map.exception.ServiceException;
import com.gok.food_map.file.service.FileService;
import com.gok.food_map.product.dto.MerchantGetGoodsDto;
import com.gok.food_map.product.dto.ProductGetListDto;
import com.gok.food_map.product.dto.ProductsSpuDto;
import com.gok.food_map.product.entity.ProductSku;
import com.gok.food_map.product.entity.ProductSpu;

import com.gok.food_map.product.mapper.ProductSkuMapper;
import com.gok.food_map.product.mapper.ProductSpuMapper;
import com.gok.food_map.product.vo.ProductSpuGetListVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    @Resource
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private FileService fileService;

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



    //新增
    @Transactional
    public void add(ProductsSpuDto dto) {

        try {
            long spuId = IdUtil.createSnowflake(1, 1).nextId();

            ProductSpu productSpu = new ProductSpu();
            BeanUtils.copyProperties(dto,productSpu);
            productSpu.setSpuId(spuId);
            productSpu.setMerchantId(Long.valueOf(dto.getMerchantId()));
            productSpu.setCreateTime(LocalDateTime.now());
            productSpuMapper.insert(productSpu);

            ProductSku productSku = new ProductSku();
            productSku.setSpuId(spuId);
            productSku.setElementSpecs(dto.getElementSpecs());
            productSku.setSpecsPrice(dto.getSpecsPrice());
            productSku.setStock(dto.getStock());
            productSku.setImageUrl(dto.getImageUrl());
            productSku.setCreateTime(LocalDateTime.now());
            productSkuMapper.insert(productSku);

            System.out.println();
        } catch (BeansException | NumberFormatException e) {
            throw new RuntimeException(e.getMessage());
//            throw new ServiceException("添加商品失败" + e.getMessage());
        }
//        fileService.enable(productSpu.getMainImage());
    }
    //编辑
    @Transactional
    public void edit(ProductsSpuDto dto) {
        ProductSpu productSpu = productSpuMapper.selectById(dto.getSpuId());
        if (dto.getMainImage() == null) {
            fileService.remove(productSpu.getMainImage());
        } else {
            if (!dto.getMainImage().equals(productSpu.getMainImage())) {
                fileService.remove(productSpu.getMainImage());
                fileService.enable(dto.getMainImage());
            }
        }
        BeanUtils.copyProperties(dto,productSpu);
        productSpuMapper.updateById(productSpu);
    }

}




