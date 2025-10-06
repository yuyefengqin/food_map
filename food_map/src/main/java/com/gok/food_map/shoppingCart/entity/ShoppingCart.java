package com.gok.food_map.shoppingCart.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import com.gok.food_map.definetypehandler.JsonbTypeHandler;
import lombok.Data;

/**
 * 购物车表
 * @TableName shopping_cart
 */
@TableName(value ="shopping_cart")
@Data
public class ShoppingCart {
    /**
     * 购物车ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long cartId;

    /**
     * 所属用户ID
     */
    private Long userId;

    /**
     * 商品SPU ID
     */
    private Long spuId;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 规格:价钱
     */
    @TableField(typeHandler = JsonbTypeHandler.class)
    private Map<String,String> specsPrice;

    /**
     * 是否选中
     */
    private Boolean isSelected;

    /**
     * 添加时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;
    /**
     * 总价
     */
    private BigDecimal totalPrice;

    public ShoppingCart() {
    }

    public ShoppingCart(Long cartId, Long userId, Long spuId, Integer quantity, Map<String, String> specsPrice, Boolean isSelected, LocalDateTime createTime, LocalDateTime updateTime, BigDecimal totalPrice) {
        this.cartId = cartId;
        this.userId = userId;
        this.spuId = spuId;
        this.quantity = quantity;
        this.specsPrice = specsPrice;
        this.isSelected = isSelected;
        if (createTime != null) {
            this.createTime = Timestamp.valueOf(createTime);
        }
        if (updateTime != null) {
            this.updateTime = Timestamp.valueOf(updateTime);
        }
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCreateTime() {
        if (createTime == null) {
            return null;
        }
        return createTime.toLocalDateTime();
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = Timestamp.valueOf(createTime);
    }

    public LocalDateTime getUpdateTime() {
        if (updateTime == null) {
            return null;
        }
        return updateTime.toLocalDateTime();
    }

    public void setUpdateTime(LocalDateTime updateTime) {

        this.updateTime = Timestamp.valueOf(updateTime);
    }
}