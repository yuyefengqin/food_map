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

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class ProductService {
    //商品管理系统
    private final ProductMapper productMapper;
    private final FileService fileService;
    public IPage<ProductsGetListVO> getList(ProductsGetListDto dto) {
        List<ProductsGetListVO> productsGetListVOList = productMapper.selectBy(String.valueOf(dto.getSpuId()), dto.getProductCategory(), dto.getMerchantId(), dto.getSpuName(), String.valueOf(dto.getCreateTime()), dto.getShelfStatus(), dto.getApprovalStatus());
        productsGetListVOList.forEach(System.out::println);
        IPage<ProductsGetListVO> res = new Page<>(dto.getCurrent() == null ? 1 : dto.getCurrent(), dto.getSize() == null ? 20 : dto.getSize());
        BeanUtils.copyProperties(productsGetListVOList, res);
        res.setRecords(productsGetListVOList.stream().map(productsGetListVO -> {
            ProductsGetListVO vo = new ProductsGetListVO();
            BeanUtils.copyProperties(productsGetListVO, vo);
            return vo;
        }).toList());
        return res;
    }
}
