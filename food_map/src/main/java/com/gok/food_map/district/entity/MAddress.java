package com.gok.food_map.district.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 收货地址表
 * @TableName m_address
 */
@TableName(value ="m_address")
@Data
public class MAddress {
    /**
     * 地址ID
     */
    @TableId
    private Long addressId;

    /**
     * 所属用户ID
     */
    private Long userId;

    /**
     * 收件人
     */
    private String receiver;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 邮政编码
     */
    private String zipCode;

    /**
     * 是否默认地址
     */
    private Boolean isDefault;
}