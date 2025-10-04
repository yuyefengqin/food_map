package com.gok.food_map.user.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LevelSaveDTO {
    private Integer levelId;

    /**
     * 会员等级名称
     */
    private String levelName;

    /**
     * 折扣率
     */
    private BigDecimal discountRate;
}
