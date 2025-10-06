package com.gok.food_map.shoppingCart.mapper;

import com.gok.food_map.shoppingCart.entity.ShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gok.food_map.shoppingCart.vo.ShoppingCartGetVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author LeiNa
* @description 针对表【shopping_cart(购物车表)】的数据库操作Mapper
* @createDate 2025-10-05 14:13:14
* @Entity com.gok.food_map.shoppingCard.entity.ShoppingCart
*/
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
    List<ShoppingCartGetVO> selectByUserId(String userId);
}




