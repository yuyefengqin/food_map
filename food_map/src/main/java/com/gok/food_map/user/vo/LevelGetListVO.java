package com.gok.food_map.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LevelGetListVO {

    private Integer levelId;

    /**
     * 会员等级名称
     */
    private String levelName;
    private String discountRate;
}
