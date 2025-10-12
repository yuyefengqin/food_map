package com.gok.food_map.order.service;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gok.food_map.district.entity.MAddress;
import com.gok.food_map.district.mapper.MAddressMapper;
import com.gok.food_map.merchant.entity.MMerchant;
import com.gok.food_map.merchant.mapper.MMerchantMapper;
import com.gok.food_map.order.dto.*;
import com.gok.food_map.order.entity.OrderItem;
import com.gok.food_map.order.entity.ProductOrder;
import com.gok.food_map.order.mapper.OrderItemMapper;
import com.gok.food_map.order.mapper.ProductOrderMapper;
import com.gok.food_map.order.vo.BuyProduct;
import com.gok.food_map.order.vo.OrderGetListVO;
import com.gok.food_map.order.vo.OrderItemGetListVO;
import com.gok.food_map.order.vo.UserOrderInfoVO;
import com.gok.food_map.product.entity.ProductSpu;
import com.gok.food_map.product.mapper.ProductSpuMapper;
import com.gok.food_map.shoppingCart.entity.ShoppingCart;
import com.gok.food_map.shoppingCart.mapper.ShoppingCartMapper;
import com.gok.food_map.user.mapper.MUserMapper;
import com.gok.food_map.util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
    private final ShoppingCartMapper shoppingCartMapper;

    @Transactional(rollbackFor = Exception.class)
    public void directBuy(OrderDirectBuyDto dto) {
        System.out.println(dto);

        long orderId = IdUtil.createSnowflake(1, 1).nextId();

        MAddress mAddress = checkUserAddress(dto.getUserId());
        ProductOrder productOrder = setProductOrder(
                orderId,
                dto.getUserId(),
                dto.getMerchantId(),
                mAddress.getAddressId(),
                new BigDecimal(dto.getTotalPrice()).add(BigDecimal.TEN),
                new BigDecimal(dto.getTotalPrice())
                );

        OrderItem orderItem = OrderItem
                .builder()
                .orderId(orderId)
                .spuId(dto.getSpuId())
                .productName(checkProduct(dto.getSpuId()).getSpuName())
                .specs(dto.getSpecs())
                .unitPrice(new BigDecimal(dto.getUnitPrice()))
                .quantity(dto.getQuantity())
                .createTime(LocalDateTime.now())
                .subAmount(new BigDecimal(dto.getTotalPrice()))
                .build();
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
    private MMerchant checkMerchantByID(Long merchantId){
        return mMerchantMapper.selectById(merchantId);
    }
    private MMerchant checkMerchantSpuId(Long SpuId){
        return mMerchantMapper.selectById(SpuId);
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
            List<BuyProduct> buyProducts = new ArrayList<>();
            MAddress mAddress = checkUserAddress(pO.getUserId());
            UserOrderInfoVO VO = UserOrderInfoVO.builder()
                    .orderId(pO.getOrderId().toString())
                    .tradeNo(pO.getTradeNo().toString())
                    .merchantName(checkMerchantByID(pO.getMerchantId()).getMerchantName())
                    .orderAmount(pO.getOrderAmount().add(pO.getDeliveryFee()).toString())
                    .deliveryFee(pO.getDeliveryFee().toString())
                    .deliveryMethod(pO.getDeliveryMethod())
                    .logisticsNo(pO.getLogisticsNo().toString())
                    .receiver(mAddress.getReceiver())
                    .telephone(mAddress.getTelephone())
                    .detailAddress(mAddress.getDetailAddress())
                    .orderStatus(checkOrderStatus(pO.getOrderStatus()))
                    .createTime(pO.getCreateTime().toString())
                    .payTime(pO.getPayTime().toString())
                    .shipTime(pO.getShipTime().toString())
                    .receiveTime(pO.getReceiveTime().toString())
                    .completeTime(pO.getCompleteTime().toString())
                    .build();

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
    @Transactional(rollbackFor = Exception.class)
    public boolean buyByCart(List<BuyByCartDto> buyByCartDtos,HttpServletRequest request) {
        try {
            Map<String, String> idByTokenSafe = TokenUtil.getIdByTokenSafe(request);
            Long id = Long.parseLong(idByTokenSafe.get("id"));
            Long addressId = checkUserAddress(id).getAddressId();
            HashMap<Long, List<BuyByCartDto>> map = new HashMap<>();
            buyByCartDtos.forEach(dto ->
                    map.computeIfAbsent(Long.valueOf(dto.getMerchantId()
                    ), k -> new ArrayList<>()
                    ).add(dto));

            System.out.println();
            map.forEach((key, value) -> {
                Long orderId = IdUtil.createSnowflake(1, 1).nextId();
                Price price = new Price();
                value.forEach(v -> {
                    OrderItem orderItem = setOrderItem(orderId, v);
                    orderItemMapper.insert(orderItem);
                    price.setProductAmount(v.getTotalPrice());
                });
                price.setOrderAmount(price.getProductAmount().add(BigDecimal.TEN));
                ProductOrder productOrder = setProductOrder(orderId,id,key,addressId,price.getOrderAmount(),price.getProductAmount());

                productOrderMapper.insert(productOrder);
            });
            return deleteShoppingCart(buyByCartDtos);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    protected boolean deleteShoppingCart(List<BuyByCartDto> buyByCartDtos) {
        try {
            buyByCartDtos.forEach(dto -> shoppingCartMapper.deleteById(Long.parseLong(dto.getCartId())));
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Data
    static class Price{
        private BigDecimal orderAmount = BigDecimal.ZERO;
        private BigDecimal productAmount = BigDecimal.ZERO;

        public void setOrderAmount(BigDecimal orderAmount) {
            this.orderAmount = new BigDecimal(String.valueOf(this.productAmount)).add(orderAmount);
        }

        public void setProductAmount(BigDecimal productAmount) {
            this.productAmount = new BigDecimal(String.valueOf(this.productAmount)).add(productAmount);
        }
    }
    private OrderItem setOrderItem(Long orderId,BuyByCartDto buyByCartDto) {
        return OrderItem.builder()
                        .orderId(orderId)
                        .spuId(Long.parseLong(buyByCartDto.getSpuId()))
                        .productName(buyByCartDto.getSpuName())
                        .specs(buyByCartDto.getSpecs())
                        .unitPrice(buyByCartDto.getUnitPrice())
                        .quantity(buyByCartDto.getQuantity())
                        .subAmount(buyByCartDto.getTotalPrice())
                        .createTime(LocalDateTime.now())
                        .build();
    }
    private ProductOrder setProductOrder(Long orderId,
                                         Long userId,
                                         Long merchantId,
                                         Long addressId,
                                         BigDecimal orderAmount,
                                         BigDecimal productAmount) {
        return  ProductOrder.builder()
                            .orderId(orderId)
                            .userId(userId)
                            .merchantId(merchantId)
                            .orderAmount(orderAmount)
                            .productAmount(productAmount)
                            .deliveryFee(BigDecimal.TEN)
                            .deliveryMethod("顺丰快递")
                            .deliveryTime(null)
                            .addressId(addressId)
                            .payMethod("网页支付")
                            .tradeNo(orderId+10L)
                            .logisticsCompany("顺丰公司")
                            .logisticsNo(orderId+20L)
                            .orderStatus(4)
                            .createTime(LocalDateTime.now())
                            .payTime(LocalDateTime.now())
                            .shipTime(LocalDateTime.now())
                            .receiveTime(LocalDateTime.now().plusDays(3))
                            .completeTime(LocalDateTime.now())
                            .closeTime(LocalDateTime.now())
                            .build();
    }
    //导入
    public void export(OrderGetListDTO dto, HttpServletResponse response) {
        File file= new File("./order.txt");
        String fileName= file.getName();
        List<String> timeList = dto.getTime();
        if(timeList==null|| timeList.isEmpty()){
            timeList.add(null);
            timeList.add(null);
        }
        List<OrderGetListVO> orders = productOrderMapper.selectBy(
                dto.getOrderId()==null? null:dto.getOrderId(),
                dto.getOrderStatus(),
                (!StringUtils.hasText(dto.getMerchantName()))? null:dto.getMerchantName(),
                (!StringUtils.hasText(dto.getUserCode()))? null:dto.getUserCode(),
                dto.getTime().get(0) == null ? null : OffsetDateTime.parse(dto.getTime().get(0)).toLocalDateTime(),
                dto.getTime().get(1) == null ? null : OffsetDateTime.parse(dto.getTime().get(1)).toLocalDateTime(),
                (!StringUtils.hasText(dto.getPayMethod())) ? null:dto.getPayMethod()
        );
        if (orders==null || orders.isEmpty()){
            throw new RuntimeException("没有订单");
        }
        try(BufferedWriter os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
//            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
//            os.writeObject(users);
            for (OrderGetListVO order : orders) {
                os.write(order.toString());
                os.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //设置content-type: application/octet-stream（告知浏览器这是二进制文件）
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        //设置content-disposition（attachment用附加方式下载，filename指定文件名【中文名正确解析需要进行编码】）
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        //复制文件
        try(InputStream inputStream= new FileInputStream(file)) {
            OutputStream outputStream= response.getOutputStream();
            StreamUtils.copy(inputStream, outputStream);
        } catch(Exception e) {
            throw new RuntimeException("文件下载失败");
        }
    }
    //查
    public IPage<OrderItemGetListVO> getOrderItems(OrderItemGetListDTO dto) {
        IPage<OrderItem> page = new Page<>(dto.getCurrent() == null ? 1 : Integer.parseInt(dto.getCurrent()), dto.getSize() == null ? 10 : Integer.parseInt(dto.getSize()));
        LambdaQueryWrapper<OrderItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderItem::getOrderId,Long.valueOf(dto.getOrderId()));
        page = orderItemMapper.selectPage(page,queryWrapper);
        IPage<OrderItemGetListVO> res = new Page<>();
        BeanUtils.copyProperties(page, res);
        res.setRecords(page.getRecords().stream().map(oi ->{
            OrderItemGetListVO vo = new OrderItemGetListVO();
            BeanUtils.copyProperties(oi, vo);
            vo.setItemId(oi.getItemId().toString());
            vo.setMerchantName(dto.getMerchantName());
//            vo.setProductName(oi.getProductName());
//            vo.setSpecs(oi.getSpecs());
//            vo.setQuantity(oi.getQuantity());
//            vo.setUnitPrice(oi.getUnitPrice());
//            vo.setSubAmount(oi.getSubAmount());
            return vo;
        }).toList());
        return res;
    }
    //查
    public IPage<OrderGetListVO> getList(OrderGetListDTO dto) {
        IPage<OrderGetListVO> page = new Page<>(dto.getCurrent() == null ? 1 : Integer.parseInt(dto.getCurrent()), dto.getSize() == null ? 20 : Integer.parseInt(dto.getSize()));
        List<String> timeList = dto.getTime();
        if(timeList.isEmpty()){
            timeList.add(null);
            timeList.add(null);
        }
        page = productOrderMapper.selectIPageBy(
                page,
                dto.getOrderId()==null? null:dto.getOrderId(),
                dto.getOrderStatus(),
                (!StringUtils.hasText(dto.getMerchantName()))? null:dto.getMerchantName(),
                (!StringUtils.hasText(dto.getUserCode()))? null:dto.getUserCode(),
                dto.getTime().get(0) == null ? null : OffsetDateTime.parse(dto.getTime().get(0)).toLocalDateTime(),
                dto.getTime().get(1) == null ? null : OffsetDateTime.parse(dto.getTime().get(1)).toLocalDateTime(),
                (!StringUtils.hasText(dto.getPayMethod())) ? null:dto.getPayMethod()
        );
        IPage<OrderGetListVO> res = new Page<>();
        BeanUtils.copyProperties(page,res);
        res.setRecords(page.getRecords().stream().map(orders->{
            OrderGetListVO vo = new OrderGetListVO();
            BeanUtils.copyProperties(orders,vo);
            return vo;
        }).toList());
        return res;
    }
}




