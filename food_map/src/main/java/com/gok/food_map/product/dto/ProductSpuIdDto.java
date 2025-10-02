package com.gok.food_map.product.dto;

public class ProductSpuIdDto {
    private String spuId;

    public ProductSpuIdDto(String spuId) {
        this.spuId = spuId;
    }

    public ProductSpuIdDto() {
    }

    public String getSpuId() {
        return this.spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }
}