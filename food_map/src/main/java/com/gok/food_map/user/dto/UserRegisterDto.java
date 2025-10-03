package com.gok.food_map.user.dto;

public class UserRegisterDto {
    private String code;
    private String password;
    public UserRegisterDto(String code, String password) {
        this.code = code;
        this.password = password;
    }
    public UserRegisterDto() {
    }

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
