package com.gok.food_map.product.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsSpuDto {
    private Long spuId;
    private String spuName;
    private String spuDesc;
    private String productCategory;
    private String sales;
    private String merchantId;
    private Long mainImage;
    private String shelfStatus;
    private String approvalStatus;
    private String createTime;
}
