package com.gok.food_map.merchant.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商户表
 * @TableName m_merchant
 */
@TableName(value ="m_merchant")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MMerchant {
    /**
     * 商户ID
     */
    @TableId
    private Long merchantId;

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 备注
     */
    private String notes;

    /**
     * 所在城市
     */
    private String merchantAddress;

    /**
     * 联系人电话
     */
    private String contactPhone;

    /**
     * 企业类型
     */
    private String enterpriseType;

    /**
     * logo图片URL
     */
    private Long logoUrl;

    /**
     * 营业执照URL
     */
    private Long businessLicense;

    /**
     * 商户管理账号
     */
    private String manageAccount;

    /**
     * 管理账号密码
     */
    private String managePassword;

    /**
     * 商户状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}