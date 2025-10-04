package com.gok.food_map.order.mapper;

import com.gok.food_map.order.entity.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 32741
* @description 针对表【order_item(订单明细表)】的数据库操作Mapper
* @createDate 2025-10-04 23:15:31
* @Entity com.gok.food_map.order.entity.OrderItem
*/
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}




