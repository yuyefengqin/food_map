package com.gok.food_map.shoppingCart.dto;

import java.util.List;
import java.util.Map;

public class ShoppingCartGetDto {
    private String userId;
    private String spuId;
    private String merchantId;
    private Integer quantity;
    private List<String> specs;
    private String unitPrice;
    private String totalPrice;

    public ShoppingCartGetDto() {
    }

    public ShoppingCartGetDto(String userId, String spuId,String merchantId, Integer quantity, List<String> specs, String totalPrice, String unitPrice) {
        this.userId = userId;
        this.spuId = spuId;
        this.merchantId = merchantId;
        this.quantity = quantity;
        this.specs = specs;
        this.totalPrice = totalPrice;
        this.unitPrice = unitPrice;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
}
