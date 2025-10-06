package com.gok.food_map.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 订单明细表
 * @TableName order_item
 */
@TableName(value ="order_item")
@Data
public class OrderItem {
    /**
     * 明细ID
     */
    @TableId
    private Long itemId;

    /**
     * 关联订单ID
     */
    private Long orderId;

    /**
     * 商品SPU ID
     */
    private Long spuId;

    /**
     * 商品SKU ID
     */
    private Long skuId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 规格
     */
    private String specs;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 小计金额
     */
    private BigDecimal subAmount;

    /**
     * 退款状态
     */
    private String refundStatus;

    /**
     * 创建时间
     */
    private Date createTime;
}