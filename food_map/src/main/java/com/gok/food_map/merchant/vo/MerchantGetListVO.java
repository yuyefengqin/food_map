package com.gok.food_map.merchant.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantGetListVO {
    /**
     * 商户编号
     */
    @TableId
    private String merchantId;

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
    private String logoUrl;

    /**
     * 营业执照URL
     */
    private String businessLicense;

    /**
     * 商户管理账号
     */
    private String manageAccount;


    /**
     * 商户状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
