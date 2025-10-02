package com.gok.food_map.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品SPU表
 * @TableName product_spu
 */
@TableName(value ="product_spu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSpu {
    /**
     * SPU ID
     */
    @TableId
    private Long spuId;

    /**
     * 商品名称
     */
    private String spuName;

    /**
     * 商品描述
     */
    private String spuDesc;

    /**
     * 所属商户ID
     */
    private Long merchantId;

    /**
     * 商品分类
     */
    private String productCategory;

    /**
     * 主图
     */
    private Long mainImage;

    /**
     * 审批状态
     */
    private String approvalStatus;

    /**
     * 上架状态
     */
    private String shelfStatus;

    /**
     * 总销量
     */
    private Integer sales;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    public LocalDateTime getCreateTime() {
        if (createTime == null) {
            return null;
        }
        return createTime.toLocalDateTime();
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = Timestamp.valueOf(createTime);
    }

    public LocalDateTime getUpdateTime() {
        if (this.updateTime == null) {
            return null;
        }
        return updateTime.toLocalDateTime();
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = Timestamp.valueOf(updateTime);
    }

}