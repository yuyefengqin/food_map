package com.gok.food_map.product.mapper;

import com.gok.food_map.product.entity.ProductSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;

import java.io.Serializable;
import java.util.List;

/**
* @author LeiNa
* @description 针对表【product_sku(商品SKU表)】的数据库操作Mapper
* @createDate 2025-10-01 22:04:55
* @Entity com.gok.food_map.product.entity.ProductSku
*/
@Mapper
public interface ProductSkuMapper extends BaseMapper<ProductSku> {
    @ResultMap("skuResultMap")
    List<ProductSku> selectById(String spuId);
}




