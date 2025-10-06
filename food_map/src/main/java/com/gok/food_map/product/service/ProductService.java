package com.gok.food_map.product.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gok.food_map.file.service.FileService;
import com.gok.food_map.merchant.vo.MerchantGetListVO;
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
        //分页限制
        IPage<ProductsGetListVO> page = new Page<>(dto.getCurrent() == null ? 1 : dto.getCurrent(), dto.getSize() == null ? 20 : dto.getSize());

        //xml查询
        IPage<ProductsGetListVO> productsGetListVOList = productMapper.selectBy(
                page,
                (!dto.getSpuId().isEmpty()) ?Long.parseLong(dto.getSpuId()) : null,
                dto.getProductCategory(),
                (!dto.getMerchantId().isEmpty())? Long.parseLong(dto.getMerchantId()) : null,
                dto.getSpuName(),
                time,
                dto.getShelfStatus(),
                dto.getApprovalStatus());

        //讲查询结果copy到res
        IPage<ProductsGetListVO> res = new Page<>();
        BeanUtils.copyProperties(productsGetListVOList, res);

        //传到前端
        res.setRecords(productsGetListVOList.getRecords().stream().map(productsGetListVO -> {
            ProductsGetListVO vo = new ProductsGetListVO();
            BeanUtils.copyProperties(productsGetListVO, vo);
            return vo;
        }).toList());
        return res;
    }
}
