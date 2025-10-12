package com.gok.food_map.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemGetListDTO {
    private String current;
    private String size;
    private String orderId;
    private String merchantName;
}
