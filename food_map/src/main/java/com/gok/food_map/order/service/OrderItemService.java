package com.gok.food_map.order.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gok.food_map.order.dto.OrderRemoveDTO;
import com.gok.food_map.order.entity.OrderItem;
import com.gok.food_map.order.mapper.OrderItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 32741
* @description 针对表【order_item(订单明细表)】的数据库操作Service实现
* @createDate 2025-10-04 23:15:30
*/
@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class OrderItemService{
    private final OrderItemMapper orderItemMapper;

    public void remove(Long orderId) {
        LambdaQueryWrapper<OrderItem> query = new LambdaQueryWrapper<OrderItem>();
        query.eq(orderId!=null ,OrderItem::getOrderId, orderId);
        List<OrderItem> orderItems = orderItemMapper.selectList(query);
        if(orderItems == null){
            throw new RuntimeException("详细订单不存在");
        }
        orderItemMapper.delete(query);
    }

}




