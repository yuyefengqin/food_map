package com.gok.food_map.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 收货地址
 * @TableName m_address
 */
@TableName(value ="m_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MAddress implements Serializable {
    /**
     * 编号
     */
    @TableId
    private Long id;

    /**
     * 用户
     */
    private Long userId;

    /**
     * 收件人
     */
    private String name;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 地址详情
     */
    private String detail;

    /**
     * 默认地址
     */
    private Boolean isDefault;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}