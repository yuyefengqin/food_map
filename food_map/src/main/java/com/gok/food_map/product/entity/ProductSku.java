package com.gok.food_map.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gok.food_map.definetypehandler.JsonbTypeHandler;
import com.gok.food_map.definetypehandler.ListStrToArrayTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品SKU表
 * @TableName product_sku
 */
@TableName(value ="product_sku")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSku {
    /**
     * SKU ID
     */
    @TableId
    private Long skuId;

    /**
     * 关联SPU ID
     */
    private Long spuId;

    /**
     * 规格:价格
     */
    @TableField(typeHandler = JsonbTypeHandler.class)
    private Map<String,String> specsPrice;

    /**
     * 元素:规格
     */
    @TableField(typeHandler = JsonbTypeHandler.class)
    private Map<String,String> elementSpecs;

    /**
     * 库存数量
     */
    private Integer stock;

    /**
     * SKU图片URL
     */
    @TableField(typeHandler = ListStrToArrayTypeHandler.class)
    private List<String> imageUrl;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    public ProductSku(Long skuId, Long spuId, Map<String, String> specsPrice, Map<String, String> elementSpecs, Integer stock, List<String> imageUrl, LocalDateTime createTime,
                      LocalDateTime updateTime) {
        this.skuId = skuId;
        this.spuId = spuId;
        this.specsPrice = specsPrice;
        this.elementSpecs = elementSpecs;
        this.stock = stock;
        this.imageUrl = imageUrl;
        if (createTime != null) {
            this.createTime = Timestamp.valueOf(createTime);
        }
        if (updateTime != null) {
            this.updateTime = Timestamp.valueOf(updateTime);
        }
    }

    public LocalDateTime getUpdateTime() {
        if (updateTime == null) {
            return null;
        }
        return updateTime.toLocalDateTime();
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = Timestamp.valueOf(updateTime);
    }

    public LocalDateTime getCreateTime() {
        if (createTime == null) {
            return null;
        }
        return createTime.toLocalDateTime();
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = Timestamp.valueOf(createTime);
    }
}