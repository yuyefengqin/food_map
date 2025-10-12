package com.gok.food_map.merchant.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
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
     * 商户编号
     */
    @TableId
    private Long merchantId;

    /**
     * 用户编号
     */
    private Long userId;
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
     * 商户状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime ;

    public LocalDateTime getCreateTime() {
        return this.createTime != null ? this.createTime : LocalDateTime.now();
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime != null ? createTime : LocalDateTime.now();
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}