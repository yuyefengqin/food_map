package com.gok.food_map.product.vo;

public class ProductSpuGetListVO {
    private String spuId;
    private String spuName;
    private String spuDesc;
    private String merchantId;
    private String mainImage;
    private String productCategory;

    public ProductSpuGetListVO() {
    }

    public ProductSpuGetListVO(String spuId, String spuName, String spuDesc, String merchantId, String mainImage, String productCategory) {
        this.spuId = spuId;
        this.spuName = spuName;
        this.spuDesc = spuDesc;
        this.merchantId = merchantId;
        this.mainImage = mainImage;
        this.productCategory = productCategory;
    }

    public String getSpuId() {
        return this.spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getSpuName() {
        return this.spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getSpuDesc() {
        return this.spuDesc;
    }

    public void setSpuDesc(String spuDesc) {
        this.spuDesc = spuDesc;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMainImage() {
        return this.mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getProductCategory() {
        return this.productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}