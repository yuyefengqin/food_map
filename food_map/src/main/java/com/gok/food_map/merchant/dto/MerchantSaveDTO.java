package com.gok.food_map.merchant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantSaveDTO {

    //商户管理账号
    private String manageAccount;

    //管理账号密码
    private String managePassword;

    //商户名称
    private String merchantName;

    //商户联系方式
    private String contactPhone;

    //营业执照URL
    private Long businessLicense;

    //logo图片URL
    private Long logoUrl;

    //企业类型
    private String enterpriseType;

    //备注
    private String notes;

}
