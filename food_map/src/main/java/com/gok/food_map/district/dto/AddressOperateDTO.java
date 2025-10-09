package com.gok.food_map.district.dto;

import lombok.Data;

@Data
public class AddressOperateDTO {
    private Long addressId; // 编辑的时候会用到
    private String userId;
    private String receiver;
    private String telephone;
    private String detailAddress;
    private String zipCode;
    private Boolean isDefault;
}