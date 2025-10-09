package com.gok.food_map.order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gok.food_map.annotation.Auth;
import com.gok.food_map.order.dto.OrderDirectBuyDto;
import com.gok.food_map.order.dto.OrderGetListDTO;
import com.gok.food_map.order.dto.OrderRemoveDTO;
import com.gok.food_map.order.dto.OrderSaveDTO;
import com.gok.food_map.order.service.ProductOrderService;
import com.gok.food_map.order.vo.OrderGetListVO;
import com.gok.food_map.order.vo.UserOrderInfoVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Lazy)
@RequestMapping("/order")
public class OrderController {
    private final ProductOrderService service;
    @Auth
    @PostMapping("/directBuy")
    public void directBuy(@RequestBody OrderDirectBuyDto dto) {
        service.directBuy(dto);
    }
//    @PostMapping("/remove")
//    public void removeOrder(@RequestBody OrderRemoveDTO dto) {
//        service.remove(dto);
//    }
//    @PostMapping("/edit")
//    public void editOrder(@RequestBody OrderSaveDTO dto) {
//        service.edit(dto);
//    }
//    @PostMapping("/getList")
//    public IPage<OrderGetListVO> getList(@RequestBody OrderGetListDTO dto){
//        return service.getList(dto);
//    }

   @Auth
   @PostMapping("/getOrders")
   public List<UserOrderInfoVO> getOrders(HttpServletRequest request){
        return service.getUserOrderInfos(request);
   }
}
