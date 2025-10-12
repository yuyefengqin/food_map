package com.gok.food_map.order.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gok.food_map.order.entity.ProductOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gok.food_map.order.vo.OrderGetListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author 32741
* @description 针对表【product_order(普通商品订单表)】的数据库操作Mapper
* @createDate 2025-10-05 01:07:19
* @Entity com.gok.food_map.order.entity.ProductOrder
*/
@Mapper
public interface ProductOrderMapper extends BaseMapper<ProductOrder> {
    List<OrderGetListVO> selectBy(@Param("orderId") Long orderId,
                                  @Param("orderStatus") Integer orderStatus,
                                  @Param("merchantName") String merchantName,
                                  @Param("userCode") String  userCode,
                                  @Param("beginTime") LocalDateTime beginTime,
                                  @Param("endTime") LocalDateTime endTime,
                                  @Param("payMethod") String payMethod
    );
    IPage<OrderGetListVO> selectIPageBy(
            IPage<OrderGetListVO> page,
            @Param("orderId") Long orderId,
            @Param("orderStatus") Integer orderStatus,
            @Param("merchantName") String merchantName,
            @Param("userCode") String  userCode,
            @Param("beginTime") LocalDateTime beginTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("payMethod") String payMethod
    );
}




