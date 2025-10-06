package com.gok.food_map.order.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderGetListDTO {
    private String current;
    private String size;
    private Long orderId;
    private Integer orderStatus;
    private Long merchantId;
    private String userCode;
    private String beginTime;
    private String endTime;
    private String payMethod;
}
