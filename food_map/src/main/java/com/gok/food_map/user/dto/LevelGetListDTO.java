package com.gok.food_map.user.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
public class LevelGetListDTO {
    private String current;

    private String size;
}
