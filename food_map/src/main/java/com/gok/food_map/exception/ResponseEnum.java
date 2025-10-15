package com.gok.food_map.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
//自定义响应枚举类
@AllArgsConstructor
@Getter
public enum ResponseEnum {

    ADDRESS_EX(300,"请先设置默认收获地址或地址不存在"),

    PRODUCT_EX(300,"商品不存在或已下架"),

    MERCHANT_EX(300,"商户不存在"),

    PARAM_ERROR(400, "参数错误"),

    UNAUTHORIZED(401, "未授权"),

    FORBIDDEN(403, "禁止访问"),

    NOT_FOUND(404, "资源不存在"),

    SYSTEM_ERROR(500, "系统错误"),

    SERVICE_UNAVAILABLE(503, "服务不可用"),

    USER_EXISTED(303,"用户已存在"),

    IDENTITY_EX(303,"身份异常"),

    USER_NO_EXIST(500,"用户不存在"),

    ADD_FAIL(500,"添加失败"),

    PARA_EX(401,"参数异常"),

    NO_EXIST_DATA(500,"删除失败：数据不存在"),

    SUCCESS(200, "操作成功"),

    FAIL(300,"操作失败"),

    USER_EX(301,"用户不存在，请重新登录"),

    ERROR(302,"错误请求"),

    USERNAME_PASSWORD_ERROR(303,"用户名或密码错误"),

    NO_TOKEN(400,"无token，请重新登录"),

    TOKEN_VERIFY_ERROR(401,"token验证失败，请重新登录"),

    TOKEN_EX(402,"token已过期");


    private final Integer code;

    private final String msg;

    public static ResponseEnum getResultCode(Integer code){
        for (ResponseEnum value : ResponseEnum.values()) {
            if (code.equals(value.getCode())){
                return value;
            }
        }
        return ResponseEnum.ERROR;
    }
}
