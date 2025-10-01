package com.gok.food_map.merchant.entity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商户表
 * @TableName m_merchant
 */
@TableName(value ="m_merchant")
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
    private Timestamp createTime ;

    public LocalDateTime getCreateTime() {
        return this.createTime != null ? this.createTime.toLocalDateTime() : null;
    }


    public Long getMerchantId() {
        return merchantId;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime != null ? Timestamp.valueOf(createTime) : null;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public Long getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(Long logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Long getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(Long businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getManageAccount() {
        return manageAccount;
    }

    public void setManageAccount(String manageAccount) {
        this.manageAccount = manageAccount;
    }

    public String getManagePassword() {
        return managePassword;
    }

    public void setManagePassword(String managePassword) {
        this.managePassword = managePassword;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}