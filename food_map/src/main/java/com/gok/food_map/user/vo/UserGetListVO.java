package com.gok.food_map.user.vo;

import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

public class UserGetListVO {

    /**
     * 编号
     */
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
}
