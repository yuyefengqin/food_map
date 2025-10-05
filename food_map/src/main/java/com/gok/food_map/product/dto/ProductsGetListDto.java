package com.gok.food_map.product.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsGetListDto {
    //当前页
    private Long current;

    //页大小
    private Long size;

    //编号
    private String spuId;

    //商品分类
    private String productCategory;

    //所属商户
    private String merchantId;

    //商品名称
    private String spuName;

    //创建时间
    private String createTime;

    //上架状态
    private String shelfStatus;

    //审批状态
    private String approvalStatus;
}
