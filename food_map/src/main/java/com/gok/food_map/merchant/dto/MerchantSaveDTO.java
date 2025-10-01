package com.gok.food_map.merchant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



//新增
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantSaveDTO {

    private Long merchantId;

    //商户管理账号
    private String manageAccount;

    //商户名称
    private String merchantName;

    //企业类型
    private String enterpriseType;

    //商户联系方式
    private String contactPhone;

    //地址
    private String merchantAddress;

    //状态
    private Integer status;

    //营业执照URL
    private Long businessLicense;

    //logo图片URL
    private Long logoUrl;

    //管理账号密码
    private String managePassword;

    //备注
    private String notes;

}