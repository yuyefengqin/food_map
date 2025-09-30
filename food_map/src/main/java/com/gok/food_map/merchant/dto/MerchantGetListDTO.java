package com.gok.food_map.merchant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//表格
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantGetListDTO {
    //当前页
    private Long current;

    //页大小
    private Long size;

    //商户编号
    private String merchantNo;

    //商户名称
    private String merchantName;

    //创建时间
    private LocalDateTime createTime;

    //商户状态
    private Integer status;

    //商户管理账号
    private String manageAccount;
}
