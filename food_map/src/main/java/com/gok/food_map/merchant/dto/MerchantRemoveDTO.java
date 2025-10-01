package com.gok.food_map.merchant.dto;

public class MerchantRemoveDTO {
    private String merchantId;

    public MerchantRemoveDTO() {
    }

    public MerchantRemoveDTO(String merchantId) {
        this.merchantId = merchantId;
    }

    public Long getMerchantId() {
        return Long.valueOf(merchantId);
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
}
