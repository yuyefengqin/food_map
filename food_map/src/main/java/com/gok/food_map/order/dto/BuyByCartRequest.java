package com.gok.food_map.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyByCartRequest {
    private List<BuyByCartDto> buyByCartDtoList;
}
