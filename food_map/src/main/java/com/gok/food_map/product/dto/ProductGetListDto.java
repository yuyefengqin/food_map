package com.gok.food_map.product.dto;

public class ProductGetListDto {
    private String searchQuery;
    private Integer currentPage;
    private Integer pageSize;

    public ProductGetListDto(String searchQuery, Integer currentPage, Integer pageSize) {
        this.searchQuery = searchQuery;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public ProductGetListDto() {
    }

    public String getSearchQuery() {
        return this.searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
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