package com.gok.food_map.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderGetListDTO {
    private String current;
    private String size;
    private Long orderId;
    private Integer orderStatus;
    private String merchantName;
    private String userCode;
    private List<String> time;
    private String payMethod;
}
