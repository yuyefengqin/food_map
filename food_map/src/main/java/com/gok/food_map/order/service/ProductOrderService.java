package com.gok.food_map.order.service;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gok.food_map.district.entity.MAddress;
import com.gok.food_map.district.mapper.MAddressMapper;
import com.gok.food_map.merchant.entity.MMerchant;
import com.gok.food_map.merchant.mapper.MMerchantMapper;
import com.gok.food_map.order.dto.OrderDirectBuyDto;
import com.gok.food_map.order.dto.OrderGetListDTO;
import com.gok.food_map.order.dto.OrderRemoveDTO;
import com.gok.food_map.order.dto.OrderSaveDTO;
import com.gok.food_map.order.entity.OrderItem;
import com.gok.food_map.order.entity.ProductOrder;
import com.gok.food_map.order.mapper.OrderItemMapper;
import com.gok.food_map.order.mapper.ProductOrderMapper;
import com.gok.food_map.order.vo.BuyProduct;
import com.gok.food_map.order.vo.OrderGetListVO;
import com.gok.food_map.order.vo.UserOrderInfoVO;
import com.gok.food_map.product.entity.ProductSpu;
import com.gok.food_map.product.mapper.ProductSpuMapper;
import com.gok.food_map.user.entity.MUser;
import com.gok.food_map.user.mapper.MUserMapper;
import com.gok.food_map.util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @author 32741
* @description 针对表【product_order(普通商品订单表)】的数据库操作Service实现
* @createDate 2025-10-05 01:07:19
*/
@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class ProductOrderService{
    private static volatile long state = 0L;
    private final ProductOrderMapper productOrderMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemService orderItemService;
    private final MUserMapper mUserMapper;
    private final MAddressMapper mAddressMapper;
    private final ProductSpuMapper productSpuMapper;
    private final MMerchantMapper mMerchantMapper;

    public void remove(OrderRemoveDTO dto) {
        ProductOrder productOrder = productOrderMapper.selectById(dto.getOrderId());

        if(productOrder == null){
            throw new RuntimeException("该订单不存在");
        }
        orderItemService.remove(dto.getOrderId());
        productOrderMapper.deleteById(dto.getOrderId());

    }

    public void edit(OrderSaveDTO dto) {
        Long orderId = dto.getOrderId();
        ProductOrder productOrder = productOrderMapper.selectById(orderId);
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
        productOrderMapper.updateById(productOrder);//更新数据
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
        List<OrderGetListVO> orders = productOrderMapper.selectBy(
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
    @Transactional(rollbackFor = Exception.class)
    public void directBuy(OrderDirectBuyDto dto) {
        System.out.println(dto);

        long orderId = IdUtil.createSnowflake(1, 1).nextId();

        ProductOrder productOrder = new ProductOrder();
        productOrder.setOrderId(orderId);
        productOrder.setUserId(dto.getUserId());
        productOrder.setMerchantId(dto.getMerchantId());
        productOrder.setOrderAmount(new BigDecimal(dto.getTotalPrice()));
        productOrder.setProductAmount(new BigDecimal(dto.getTotalPrice()));
        productOrder.setDeliveryFee(BigDecimal.ONE);
        productOrder.setDeliveryMethod("顺丰快递");
        productOrder.setDeliveryTime(null);
        productOrder.setAddressId(checkUserAddress(dto.getUserId()).getAddressId());
        productOrder.setPayMethod("网页支付");
        productOrder.setTradeNo(nextId()+10L);
        productOrder.setLogisticsCompany("顺丰公司");
        productOrder.setLogisticsNo(nextId()+20L);
        productOrder.setOrderStatus(4);
        productOrder.setCreateTime(LocalDateTime.now());
        productOrder.setPayTime(LocalDateTime.now());
        productOrder.setShipTime(LocalDateTime.now());
        productOrder.setReceiveTime(LocalDateTime.now().plusDays(3));
        productOrder.setCompleteTime(LocalDateTime.now());
        productOrder.setCloseTime(LocalDateTime.now());

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);
        orderItem.setSpuId(dto.getSpuId());
        orderItem.setProductName(checkProduct(dto.getSpuId()).getSpuName());
        orderItem.setSpecs(dto.getSpecs());
        orderItem.setUnitPrice(new BigDecimal(dto.getUnitPrice()));
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setCreateTime(LocalDateTime.now());
        orderItem.setSubAmount(new BigDecimal(dto.getTotalPrice()));
        productOrderMapper.insert(productOrder);
        orderItemMapper.insert(orderItem);
    }
    private MAddress checkUserAddress(Long userId){
        LambdaQueryWrapper<MAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq((userId != null ),MAddress::getUserId,userId);
        wrapper.eq(MAddress::getisDefault,true);
        return mAddressMapper.selectOne(wrapper);
    }
    private ProductSpu checkProduct(Long productId){
        return productSpuMapper.selectById(productId);
    }
    private MMerchant checkMerchant(Long merchantId){
        return mMerchantMapper.selectById(merchantId);
    }
    @Transactional(rollbackFor = Exception.class)
    public List<UserOrderInfoVO> getUserOrderInfos(HttpServletRequest request) {
        List<UserOrderInfoVO> userOrderInfoVOS = new ArrayList<>();

        Map<String, String> idMap = TokenUtil.getIdByTokenUnsafe(request);
        long id = Long.parseLong(idMap.get("id"));
        LambdaQueryWrapper<ProductOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductOrder::getUserId,id);
        List<ProductOrder> productOrders = productOrderMapper.selectList(wrapper);
        productOrders.forEach(pO -> {
            UserOrderInfoVO VO = new UserOrderInfoVO();
            List<BuyProduct> buyProducts = new ArrayList<>();
            MAddress mAddress = checkUserAddress(pO.getUserId());
            VO.setOrderId(pO.getOrderId().toString());
            VO.setTradeNo(pO.getTradeNo().toString());
            VO.setMerchantName(checkMerchant(pO.getMerchantId()).getMerchantName());
            VO.setOrderAmount(pO.getOrderAmount().add(pO.getDeliveryFee()).toString());
            VO.setDeliveryFee(pO.getDeliveryFee().toString());
            VO.setDeliveryMethod(pO.getDeliveryMethod());
            VO.setLogisticsNo(pO.getLogisticsNo().toString());
            VO.setReceiver(mAddress.getReceiver());
            VO.setTelephone(mAddress.getTelephone());
            VO.setDetailAddress(mAddress.getDetailAddress());
            VO.setOrderStatus(checkOrderStatus(pO.getOrderStatus()));
            VO.setCreateTime(pO.getCreateTime().toString());
            VO.setPayTime(pO.getPayTime().toString());
            VO.setShipTime(pO.getShipTime().toString());
            VO.setReceiveTime(pO.getReceiveTime().toString());
            VO.setCompleteTime(pO.getCompleteTime().toString());

            LambdaQueryWrapper<OrderItem> oIWrapper = new LambdaQueryWrapper<>();
            oIWrapper.eq(OrderItem::getOrderId,pO.getOrderId());
            List<OrderItem> orderItems = orderItemMapper.selectList(oIWrapper);
            orderItems.forEach(oI ->{
                BuyProduct buyProduct = new BuyProduct();
                ProductSpu productSpu = checkProduct(oI.getSpuId());
                buyProduct.setSpuName(oI.getProductName());
                buyProduct.setMainImage(productSpu.getMainImage().toString());
                buyProduct.setSpecs(oI.getSpecs());
                buyProduct.setQuantity(oI.getQuantity());
                buyProduct.setUnitPrice(oI.getUnitPrice().toString());
                VO.setTotalQuantity(+buyProduct.getQuantity());
                buyProducts.add(buyProduct);
            });
            VO.setBuyProducts(buyProducts);
            userOrderInfoVOS.add(VO);
        });
        return userOrderInfoVOS;
    }

    private String checkOrderStatus(Integer orderStatus) {
        return switch (orderStatus) {
            case 1 -> "待付款";
            case 2 -> "待发货";
            case 3 -> "待收货";
            case 4 -> "已完成";
            case 5 -> "已取消";
            case 6 -> "交易完成";
            case 7 -> "交易关闭";
            case 8 -> "未开始";
            default -> null;
        };
    }
}




