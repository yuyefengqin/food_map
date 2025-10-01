package com.gok.food_map.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLevelChangeDTO {
    private String id;
    private String newLevel;
    private Boolean newValid;
}
