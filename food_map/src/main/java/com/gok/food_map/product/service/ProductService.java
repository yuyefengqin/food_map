package com.gok.food_map.product.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gok.food_map.file.service.FileService;
import com.gok.food_map.product.dto.ProductsGetListDto;
import com.gok.food_map.product.mapper.ProductMapper;
import com.gok.food_map.product.vo.ProductsGetListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class ProductService {
    //商品管理系统
    private final ProductMapper productMapper;
    private final FileService fileService;
    public IPage<ProductsGetListVO> getList(ProductsGetListDto dto) {
        LocalDateTime time = null;
        if (!dto.getCreateTime().isEmpty()) {
            try {
                time = new SimpleDateFormat("yy-MM-dd").parse(dto.getCreateTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        List<ProductsGetListVO> productsGetListVOList = productMapper.selectBy(
                (!dto.getSpuId().isEmpty()) ?Long.parseLong(dto.getSpuId()) : null,
                dto.getProductCategory(),
                (!dto.getMerchantId().isEmpty())? Long.parseLong(dto.getMerchantId()) : null,
                dto.getSpuName(),
                time,
                dto.getShelfStatus(),
                dto.getApprovalStatus(),
                dto.getSize(),
                dto.getCurrent());
//        productsGetListVOList.forEach(System.out::println);
        Integer count = productMapper.countBy((!dto.getSpuId().isEmpty()) ?Long.parseLong(dto.getSpuId()) : null,
                dto.getProductCategory(),
                (!dto.getMerchantId().isEmpty())? Long.parseLong(dto.getMerchantId()) : null,
                dto.getSpuName(),
                time,
                dto.getShelfStatus(),
                dto.getApprovalStatus());
        IPage<ProductsGetListVO> res = new Page<>(dto.getCurrent() == null ? 1 : dto.getCurrent(), dto.getSize() == null ? 20 : dto.getSize());
        BeanUtils.copyProperties(productsGetListVOList, res);
        res.setRecords(productsGetListVOList.stream().map(productsGetListVO -> {
            ProductsGetListVO vo = new ProductsGetListVO();
            BeanUtils.copyProperties(productsGetListVO, vo);
            return vo;
        }).toList());
        res.setTotal(count);
        return res;
    }
}
