package com.gok.food_map.advice;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServiceMsg {
    private String msg;
    private LocalDateTime time;
    public ServiceMsg(String msg) {
        this.msg = msg;
        this.time = LocalDateTime.now();
    }
}
