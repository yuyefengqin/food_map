package com.gok.food_map.product.vo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gok.food_map.definetypehandler.ListStrToArrayTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsGetListVO {
    @TableId
    private String spuId;

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
    private String merchantId;

    /**
     * 商品分类
     */
    private String productCategory;

    /**
     * 主图
     */
    private String mainImage;

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
    private String sales;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 库存数量
     */
    private String stock;

    /**
     * SKU图片URL
     */
    private String imageUrl;

}
