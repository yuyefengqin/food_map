package com.gok.food_map.order.vo;

import java.util.List;

public class BuyProduct{
    private String spuName;
    private String mainImage;
    private List<String> specs;
    private Integer quantity;
    private String unitPrice;

    public BuyProduct() {
    }

    public BuyProduct(String spuName, String mainImage, List<String> specs, Integer quantity, String unitPrice) {
        this.spuName = spuName;
        this.mainImage = mainImage;
        this.specs = specs;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public List<String> getSpecs() {
        return specs;
    }

    public void setSpecs(List<String> specs) {
        this.specs = specs;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
}