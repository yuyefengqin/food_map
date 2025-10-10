package com.gok.food_map.order.dto;

import com.gok.food_map.annotation.Auth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyByCartDto {
    private String cartId;
    private String userId;
    private String spuId;
    private String merchantId;
    private String spuName;
    private Integer quantity;
    private List<String> specs;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private Boolean isSelected;


}
