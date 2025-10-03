package com.gok.food_map.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * @TableName m_user
 */
@TableName(value ="m_user")
@Data
public class MUser implements Serializable {
    /**
     * 用户ID
     */
    @TableId
    private Long id;

    /**
     * 登录账号
     */
    private String code;

    /**
     * 加密密码
     */
    private String password;

    /**
     * 用户名
     */
    private String name;

    /**
     * 性别（男/女）
     */
    private String gender;

    /**
     * 头像
     */
    private Long avatar;

    /**
     * 注册城市
     */
    private Long city;

    /**
     * 会员等级
     */
    private Integer levelId;

    /**
     * 注册时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 状态
     */
    private Boolean valid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}