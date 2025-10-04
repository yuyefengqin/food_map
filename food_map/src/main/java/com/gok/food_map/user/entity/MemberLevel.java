package com.gok.food_map.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 会员等级表
 * @TableName member_level
 */
@TableName(value ="member_level")
@Data
public class MemberLevel {
    /**
     * 会员等级ID
     */
    @TableId(type = IdType.AUTO)
    private Integer levelId;

    /**
     * 会员等级名称
     */
    private String levelName;

    /**
     * 折扣率
     */
    private BigDecimal discountRate;
}