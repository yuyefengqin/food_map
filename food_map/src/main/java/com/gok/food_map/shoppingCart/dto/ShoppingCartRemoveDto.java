package com.gok.food_map.shoppingCart.dto;

import com.gok.food_map.exception.ResponseEnum;
import com.gok.food_map.exception.ServiceException;
import lombok.Data;

@Data
public class ShoppingCartRemoveDto {
    private String cartId;

    public ShoppingCartRemoveDto() {
    }

    public ShoppingCartRemoveDto(String cartId) {
        if (cartId == null || cartId.isEmpty()) {
            ServiceException.build(ResponseEnum.PARA_EX);
        }
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
}
