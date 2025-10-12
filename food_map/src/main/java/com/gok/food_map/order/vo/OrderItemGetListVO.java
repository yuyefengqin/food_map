package com.gok.food_map.order.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemGetListVO {
    private String merchantName;
    private String itemId;
    private String productName;
    private List<String> specs;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subAmount;

}
