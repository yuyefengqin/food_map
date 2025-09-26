package com.gok.food_map.user.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGetListVO {

    /**
     * 编号
     */
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
    private String city;

    /**
     * 会员等级
     */
    private Integer levelId;

    /**
     * 注册时间
     */
    private String createTime;

    /**
     * 状态
     */
    private Boolean enable;
}
