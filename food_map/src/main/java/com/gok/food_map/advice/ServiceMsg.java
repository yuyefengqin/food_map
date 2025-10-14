package com.gok.food_map.advice;

import com.gok.food_map.exception.ResponseEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServiceMsg {
    private Integer code;
    private String msg;
    private LocalDateTime time;
    public ServiceMsg(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
        this.time = LocalDateTime.now();
    }
}
