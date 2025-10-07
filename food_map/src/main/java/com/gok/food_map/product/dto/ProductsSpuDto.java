package com.gok.food_map.product.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsSpuDto {
    private Long spuId;
    private String spuName;
    private String spuDesc;
    private String productCategory;
    private String merchantId;
    private Long mainImage;
    private String shelfStatus;
    private String approvalStatus;
    private Map<String,String> elementSpecs;
    private Map<String,String> specsPrice;
    private List<String> imageUrl;
    private Integer stock;
}
