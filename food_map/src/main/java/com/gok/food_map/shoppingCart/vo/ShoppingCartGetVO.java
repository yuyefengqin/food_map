package com.gok.food_map.shoppingCart.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gok.food_map.definetypehandler.JsonbTypeHandler;
import com.gok.food_map.definetypehandler.ListStrToArrayTypeHandler;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
@Data
public class ShoppingCartGetVO {
    @TableId
    private String cartId;
    private String userId;
    private String spuId;
    private String merchantId;
    private Integer quantity;
    @TableField(typeHandler = ListStrToArrayTypeHandler.class)
    private List<String> specs;
    private BigDecimal unitPrice;
    private Boolean isSelected;
    private String mainImage;
    private String spuName;
    private BigDecimal totalPrice;
    public ShoppingCartGetVO() {
    }

    public ShoppingCartGetVO(String cartId, String userId, String spuId,String merchantId, Integer quantity, List<String> specs, Boolean isSelected, String mainImage, String spuName, BigDecimal totalPrice,BigDecimal unitPrice) {
        this.cartId = cartId;
        this.userId = userId;
        this.spuId = spuId;
        this.merchantId = merchantId;
        this.quantity = quantity;
        this.specs = specs;
        this.isSelected = isSelected;
        this.mainImage = mainImage;
        this.spuName = spuName;
        this.totalPrice = totalPrice;
        this.unitPrice = unitPrice;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
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

    public List<String> getSpecs() {
        return specs;
    }

    public void setSpecs(List<String> specs) {
        this.specs = specs;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

}
