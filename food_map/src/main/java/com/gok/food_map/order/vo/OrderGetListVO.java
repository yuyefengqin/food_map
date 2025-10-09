package com.gok.food_map.order.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderGetListVO {

    private String orderId;//
    /**
     * 用户账号
     */
    private String userCode;//

    /**
     * 商户编号
     */
    private String merchantId;

    /**
     * 商户名
     */
    private String merchantName;//

    /**
     * 订单总金额（商品+配送费）
     */
    private BigDecimal orderAmount;//
    /**
     * 商品金额
     */
    private BigDecimal productAmount;//

    /**
     * 配送费
     */
    private BigDecimal deliveryFee;//

    /**
     * 配送方式（如顺丰速递）
     */
    private String deliveryMethod;//
    /**
     * 收货地址ID（关联user_address）
     */
    private String addressId;//

    /**
     * 支付方式
     */
    private String payMethod;//

    /**
     * 交易流水号（支付成功后生成）
     */
    private String tradeNo;//

    /**
     * 物流公司
     */
    private String logisticsCompany;//

    /**
     * 物流单号
     */
    private String logisticsNo;//

    /**
     * 订单状态
     */
    private Integer orderStatus;//

    /**
     * 创建时间
     */
    private String createTime;//
}
