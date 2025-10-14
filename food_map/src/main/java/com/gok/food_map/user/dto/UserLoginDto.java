package com.gok.food_map.user.dto;

import org.springframework.validation.annotation.Validated;

@Validated
public class UserLoginDto {
    private String code;
    private String password;
    public UserLoginDto(String code, String password) {
        this.code = code;
        this.password = password;
    }
    public UserLoginDto() {}
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
