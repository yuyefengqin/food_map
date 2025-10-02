package com.gok.food_map.product.dto;

public class MerchantGetGoodsDto {
    private String merchantId;
    private Integer currentPage;
    private Integer pageSize;

    public MerchantGetGoodsDto() {
    }

    public MerchantGetGoodsDto(String merchantId, Integer currentPage, Integer pageSize) {
        this.merchantId = merchantId;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}