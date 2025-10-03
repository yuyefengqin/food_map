package com.gok.food_map.util;


import com.gok.food_map.exception.ResponseEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class Resp<T> implements Serializable {

    private static final long serialVersionUID = 56665257244236049L;

    private Integer code;

    private String message;

    private T data;

    private Resp() {
    }

    public static <T> Resp<T> ok(T data) {
        Resp<T> response = new Resp<>();
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMsg());
        response.setData(data);
        return response;
    }

    public static <T> Resp<T> error(Integer errCode, String errMessage) {
        Resp<T> response = new Resp<>();
        response.setCode(errCode);
        response.setMessage(errMessage);
        return response;
    }

    public static <T> Resp<T> error(ResponseEnum responseEnum) {
        Resp<T> response = new Resp<>();
        response.setCode(responseEnum.getCode());
        response.setMessage(responseEnum.getMsg());
        return response;
    }
}

