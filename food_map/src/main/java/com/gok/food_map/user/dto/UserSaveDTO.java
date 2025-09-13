package com.gok.food_map.user.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveDTO {

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
}
