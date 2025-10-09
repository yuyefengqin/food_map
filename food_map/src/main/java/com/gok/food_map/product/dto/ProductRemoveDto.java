package com.gok.food_map.product.dto;


public class ProductRemoveDto {
    private String spuId;

    public ProductRemoveDto(String spuId) {
        this.spuId = spuId;
    }

    public ProductRemoveDto() {
    }

    public Long getSpuId() {
        return Long.valueOf(spuId);
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }
}
