package com.gok.food_map.order.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gok.food_map.order.dto.OrderGetListDTO;
import com.gok.food_map.order.dto.OrderRemoveDTO;
import com.gok.food_map.order.dto.OrderSaveDTO;
import com.gok.food_map.order.entity.OrderItem;
import com.gok.food_map.order.entity.ProductOrder;
import com.gok.food_map.order.mapper.OrderItemMapper;
import com.gok.food_map.order.mapper.ProductOrderMapper;
import com.gok.food_map.order.vo.OrderGetListVO;
import com.gok.food_map.user.entity.MUser;
import com.gok.food_map.user.mapper.MUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
* @author 32741
* @description 针对表【product_order(普通商品订单表)】的数据库操作Service实现
* @createDate 2025-10-05 01:07:19
*/
@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class ProductOrderService{
    private static volatile long state = 0L;
    private final ProductOrderMapper mapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemService orderItemService;
    private final MUserMapper mUserMapper;

    public void remove(OrderRemoveDTO dto) {
        ProductOrder productOrder = mapper.selectById(dto.getOrderId());

        if(productOrder == null){
            throw new RuntimeException("该订单不存在");
        }
        orderItemService.remove(dto.getOrderId());
        mapper.deleteById(dto.getOrderId());

    }

    public void edit(OrderSaveDTO dto) {
        Long orderId = dto.getOrderId();
        ProductOrder productOrder = mapper.selectById(orderId);
        if(productOrder == null){
            throw new RuntimeException("改订单不存在");
        }
        Long userId = dto.getUserId();
        MUser mUser = mUserMapper.selectById(userId);
        if(mUser == null){
            throw new RuntimeException("用户不存在");
        }
        Long merchantId = dto.getMerchantId();
        if(merchantId == null){
            throw new RuntimeException("商户不存在");
        }
        BigDecimal productAmount = productOrder.getProductAmount();
        BigDecimal deliveryFee =  productOrder.getDeliveryFee();
        if(deliveryFee == null || deliveryFee.compareTo(BigDecimal.ZERO) <= 0 ||
        productAmount == null || productAmount.compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("订单金额错误");
        }
        productOrder.setOrderAmount(productAmount.add(deliveryFee));
        dto.setDeliveryMethod("顺丰速递");
        LocalDateTime deliveryTime = dto.getDeliveryTime();
        if(deliveryTime == null){
            throw new RuntimeException("配送时间异常");
        }
        Long addressId =  dto.getAddressId();
        if(addressId == null){
            throw new RuntimeException("收获地址不存在");
        }
        dto.setPayMethod("网页端");
        dto.setTradeNo(nextId());
        dto.setLogisticsCompany("顺丰");
        dto.setLogisticsNo(nextId());
        dto.setOrderStatus(4);
        LocalDateTime createTime = dto.getCreateTime();
        if(createTime == null){
            throw new RuntimeException("创建时间错误");
        }
        dto.setPayTime(LocalDateTime.now());
        dto.setShipTime(LocalDateTime.now());
        //以上为简单版的检查
        BeanUtils.copyProperties(dto,productOrder);
        mapper.updateById(productOrder);//更新数据
    }
    //简单的雪花算法
    public static synchronized long nextId() {
        long currentTime = System.currentTimeMillis();
        long lastTimestamp = state >>> 32;
        long sequence = state & 0xFFFFFFFFL;

        if (currentTime < lastTimestamp) {
            throw new RuntimeException("时间回拨！");
        }

        if (currentTime == lastTimestamp) {
            sequence++;
            if (sequence > 4095) {
                while (currentTime <= lastTimestamp) {
                    currentTime = System.currentTimeMillis();
                }
                sequence = 0;
            }
        } else {
            sequence = 0;
        }

        // 更新状态
        state = (currentTime << 32) | sequence;

        return ((currentTime - 1704067200000L) << 22) | sequence;
    }

    public IPage<OrderGetListVO> getList(OrderGetListDTO dto) {
        IPage<ProductOrder> page = new Page<>(dto.getCurrent() == null ? 1 : Integer.parseInt(dto.getCurrent()), dto.getSize() == null ? 20 : Integer.parseInt(dto.getSize()));
        List<OrderGetListVO> orders = mapper.selectBy(
                dto.getOrderId()==null? null:dto.getOrderId(),
                dto.getOrderStatus(),
                dto.getMerchantId()==null? null:dto.getMerchantId(),
                dto.getUserCode()==null? null:dto.getUserCode(),
                dto.getBeginTime() == null? null: LocalDateTime.parse(dto.getBeginTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                dto.getEndTime() == null? null: LocalDateTime.parse(dto.getEndTime(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                (!StringUtils.hasText(dto.getPayMethod())) ? null:dto.getPayMethod()
        );
        IPage<OrderGetListVO> res = new Page<>();
        BeanUtils.copyProperties(page, res);
        res.setRecords(orders.stream().map(orderGetListVO->{
            OrderGetListVO vo = new OrderGetListVO();
            BeanUtils.copyProperties(orderGetListVO,vo);
            return vo;
        }).toList());
        return res;
    }
}




