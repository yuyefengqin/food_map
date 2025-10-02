package com.gok.food_map.product.vo;

import java.util.List;
import java.util.Map;

public class ProductSkuGetListVO {
    private String skuId;
    private String spuId;
    private Map<String, String> specsPrice;
    private Map<String, String> elementSpecs;
    private List<String> imageUrl;

    public ProductSkuGetListVO() {
    }

    public ProductSkuGetListVO(String skuId, String spuId, Map<String, String> specsPrice, Map<String, String> elementSpecs, List<String> imageUrl) {
        this.skuId = skuId;
        this.spuId = spuId;
        this.specsPrice = specsPrice;
        this.elementSpecs = elementSpecs;
        this.imageUrl = imageUrl;
    }

    public String getSkuId() {
        return this.skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSpuId() {
        return this.spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public Map<String, String> getSpecsPrice() {
        return this.specsPrice;
    }

    public void setSpecsPrice(Map<String, String> specsPrice) {
        this.specsPrice = specsPrice;
    }

    public Map<String, String> getElementSpecs() {
        return this.elementSpecs;
    }

    public void setElementSpecs(Map<String, String> elementSpecs) {
        this.elementSpecs = elementSpecs;
    }

    public List<String> getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }
}