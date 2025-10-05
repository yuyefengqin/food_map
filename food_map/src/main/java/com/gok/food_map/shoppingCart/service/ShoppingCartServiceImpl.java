package com.gok.food_map.shoppingCart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gok.food_map.exception.ServiceException;
import com.gok.food_map.shoppingCart.dto.ShoppingCartGetDto;
import com.gok.food_map.shoppingCart.entity.ShoppingCart;
import com.gok.food_map.shoppingCart.mapper.ShoppingCartMapper;
import com.gok.food_map.shoppingCart.vo.ShoppingCartGetVO;
import com.gok.food_map.util.TokenUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
* @author LeiNa
* @description 针对表【shopping_cart(购物车表)】的数据库操作Service实现
* @createDate 2025-10-05 14:13:14
*/
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
    implements IService<ShoppingCart> {
    @Resource
    private ShoppingCartMapper shoppingCartMapper;
    public List<ShoppingCartGetVO> getShoppingCart(HttpServletRequest request) {
        if(request == null){
            ServiceException.build("request is null");
        }
        Object o = request.getSession().getAttribute("token");
        if(o == null){
            ServiceException.build("token is null");
        }
        String string = o.toString();
        if (string.isEmpty() || string.isBlank()) {
            ServiceException.build("无效请求");
        }
        if (TokenUtil.checkToken(string)) {
            ServiceException.build("登录过期");
        }
        Map<String, String> map = TokenUtil.getToken(string);
        List<ShoppingCartGetVO> id = shoppingCartMapper.selectByUserId(map.get("id"));
        System.out.println(id);
        return id;
    }
    public void savaShoppingCart(ShoppingCartGetDto cart) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(cart, shoppingCart);
        shoppingCart.setCreateTime(LocalDateTime.now());
        shoppingCart.setIsSelected(false);
        shoppingCart.setSpuId(Long.parseLong(cart.getSpuId()));
        shoppingCart.setUserId(Long.parseLong(cart.getUserId()));
        shoppingCart.setTotalPrice(BigDecimal.valueOf(Long.parseLong(cart.getTotalPrice())));
        if (shoppingCartMapper.insert(shoppingCart) == 0) {
            ServiceException.build("添加失败");
        }
    }
}




