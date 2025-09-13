package com.gok.food_map.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGetListDTO {

    //当前页
    private Long current;

    //页大小
    private Long size;

    //账号
    private String code;

    //名称
    private String name;

    //开始时间
    private LocalDateTime beginTime;

    //结束时间
    private LocalDateTime endTime;
}
