package com.gok.food_map.shoppingCart.dto;

import java.util.Map;

public class ShoppingCartGetDto {
    private String userId;
    private String spuId;
    private Integer quantity;
    private Map<String,String> specsPrice;
    private String totalPrice;

    public ShoppingCartGetDto() {
    }

    public ShoppingCartGetDto(String userId, String spuId, Integer quantity, Map<String,String> specsPrice, String totalPrice) {
        this.userId = userId;
        this.spuId = spuId;
        this.quantity = quantity;
        this.specsPrice = specsPrice;
        this.totalPrice = totalPrice;
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


    public Map<String,String> getSpecsPrice() {
        return specsPrice;
    }

    public void setSpecsPrice(Map<String,String> specsPrice) {
        this.specsPrice = specsPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
