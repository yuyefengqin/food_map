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

    private Long total;

    //页大小
    private Long size;

    //账号
    private String code;

    //名称
    private String name;

    //创建时间
    private LocalDateTime creatTime;

}
