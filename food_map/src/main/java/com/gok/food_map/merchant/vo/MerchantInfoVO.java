package com.gok.food_map.merchant.vo;

public class MerchantInfoVO {
    private String merchantId;
    private String merchantName;
    private String logoUrl;

    public MerchantInfoVO() {
    }

    public MerchantInfoVO(String merchantId, String merchantName, String logoUrl) {
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.logoUrl = logoUrl;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return this.merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getLogoUrl() {
        return this.logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}