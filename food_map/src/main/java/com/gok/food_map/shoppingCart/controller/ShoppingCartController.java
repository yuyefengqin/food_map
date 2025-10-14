package com.gok.food_map.shoppingCart.controller;

import com.gok.food_map.annotation.Auth;
import com.gok.food_map.exception.ResponseEnum;
import com.gok.food_map.exception.ServiceException;
import com.gok.food_map.shoppingCart.dto.ShoppingCartGetDto;
import com.gok.food_map.shoppingCart.dto.ShoppingCartRemoveDto;
import com.gok.food_map.shoppingCart.entity.ShoppingCart;
import com.gok.food_map.shoppingCart.service.ShoppingCartServiceImpl;
import com.gok.food_map.shoppingCart.vo.ShoppingCartGetVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Resource
    private ShoppingCartServiceImpl shoppingCartService;
    @Auth
    @PostMapping("/getShoppingCartById")
    public List<ShoppingCartGetVO> getShoppingCartById(HttpServletRequest request) {
        return shoppingCartService.getShoppingCart(request);
    }
    @Auth
    @PostMapping("/deleteShoppingCart")
    public void deleteShoppingCart(@RequestBody ShoppingCartRemoveDto dto) {
        if (shoppingCartService.removeById(Long.valueOf(dto.getCartId()))) {
            return;
        }else {
            ServiceException.build(ResponseEnum.NO_EXIST_DATA);
        }
    }
    @Auth
    @PostMapping("/addShoppingCart")
    public void addShoppingCart(@RequestBody ShoppingCartGetDto cart) {
        shoppingCartService.savaShoppingCart(cart);

    }
}
