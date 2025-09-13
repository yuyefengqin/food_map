package com.gok.food_map.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户
 * @TableName m_user
 */
@TableName(value ="m_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MUser implements Serializable {
    /**
     * 编号
     */
    @TableId
    private Long id;

    /**
     * 账号
     */
    private Long code;

    /**
     * 名称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private Long avatar;

    /**
     * 性别
     */
    private String sex;

    /**
     * 城市
     */
    private String city;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 有效
     */
    private Boolean enable;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}