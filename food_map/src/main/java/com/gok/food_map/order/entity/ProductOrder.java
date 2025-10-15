package com.gok.food_map.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.*;

/**
 * 普通商品订单表
 * @TableName product_order
 */
@TableName(value ="product_order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ProductOrder {
    /**
     * 订单ID/订单编号
     */
    @TableId
    private Long orderId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 商户ID
     */
    private String merchantName;

    /**
     * 订单总金额（商品+配送费）
     */
    private BigDecimal orderAmount;
    /**
     * 商品金额
     */
    private BigDecimal productAmount;

    /**
     * 配送费
     */
    private BigDecimal deliveryFee;

    /**
     * 配送方式（如顺丰速递）
     */
    private String deliveryMethod;

    /**
     * 配送时间（用户选择）
     */
    private LocalDateTime deliveryTime;

    /**
     * 收货地址ID（关联user_address）
     */
    private Long addressId;

    /**
     * 支付方式
     */
    private String payMethod;

    /**
     * 交易流水号（支付成功后生成）
     */
    private Long tradeNo;

    /**
     * 物流公司
     */
    private String logisticsCompany;

    /**
     * 物流单号
     */

    private Long logisticsNo;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 发货时间
     */
    private LocalDateTime shipTime;

    /**
     * 收货时间
     */
    private LocalDateTime receiveTime;

    /**
     * 交易完成时间
     */
    private LocalDateTime completeTime;

    /**
     * 交易关闭时间
     */
    private LocalDateTime closeTime;
}