package com.gok.food_map.order.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class OrderDirectBuyDto {
    private Long userId;
    private Long spuId;
    private Long merchantId;
    private List<String> specs;
//    private Map<String,String> specsPrice;
    private String unitPrice;
    private Integer quantity;
    private String totalPrice;

    public OrderDirectBuyDto() {
    }

    public OrderDirectBuyDto(Long userId, Long spuId, Long merchantId, List<String> specs, String unitPrice, Integer quantity, String totalPrice) {
        this.userId = userId;
        this.spuId = spuId;
        this.merchantId = merchantId;
        this.specs = specs;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public List<String> getSpecs() {
        return specs;
    }

    public void setSpecs(List<String> specs) {
        this.specs = specs;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }


}
