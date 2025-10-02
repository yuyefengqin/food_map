package com.gok.food_map.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExportDTO {
    private String code;
    //名称
    private String name;
    //创建时间
    private LocalDateTime createTime;
}
