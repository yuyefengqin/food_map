package com.gok.food_map.merchant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



//新增
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantSaveDTO {

    private Long id;

    //商户管理账号
    private String code;

    //商户名称
    private String name;

    //企业类型
    private String type;

    //商户联系方式
    private String mobile;

    //地址
    private String address;

    //状态
    private Integer valid;

    //营业执照URL
    private Long license;

    //logo图片URL
    private Long logo;

    //管理账号密码
    private String password;

    //备注
    private String notes;

}