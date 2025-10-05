package com.gok.food_map.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gok.food_map.product.entity.ProductSpu;
import com.gok.food_map.product.vo.ProductsGetListVO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author LeiNa
* @description 针对表【product_spu(商品SPU表)】的数据库操作Mapper
* @createDate 2025-10-01 22:04:55
* @Entity com.gok.food_map.product.entity.ProductSpu
*/
@Mapper
public interface ProductMapper extends BaseMapper<ProductsGetListVO> {
    IPage<ProductsGetListVO> selectBy(IPage<ProductsGetListVO> page,
                   Long spuId,
                   String productCategory,
                   Long merchantId,
                   String spuName,
                   LocalDateTime createTime,
                   String shelfStatus,
                   String approvalStatus);
}




