package com.gok.food_map.shoppingCart.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gok.food_map.definetypehandler.JsonbTypeHandler;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;
@Data
public class ShoppingCartGetVO {
    @TableId
    private String cartId;
    private String userId;
    private String spuId;
    private Integer quantity;
    @TableField(typeHandler = JsonbTypeHandler.class)
    private Map<String, Object> specsPrice;
    private Boolean isSelected;
    private String updateTime;
    private String mainImage;
    private String spuName;
    private BigDecimal totalPrice;
    public ShoppingCartGetVO() {
    }

    public ShoppingCartGetVO(String cartId, String userId, String spuId, Integer quantity, Map<String, Object> specsPrice, Boolean isSelected, String updateTime, String mainImage, String spuName, BigDecimal totalPrice) {
        this.cartId = cartId;
        this.userId = userId;
        this.spuId = spuId;
        this.quantity = quantity;
        this.specsPrice = specsPrice;
        this.isSelected = isSelected;
        this.updateTime = updateTime;
        this.mainImage = mainImage;
        this.spuName = spuName;
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Map<String, Object> getSpecsPrice() {
        return specsPrice;
    }

    public void setSpecsPrice(Map<String, Object> specsPrice) {
        this.specsPrice = specsPrice;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}
