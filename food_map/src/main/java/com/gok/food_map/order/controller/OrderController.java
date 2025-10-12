package com.gok.food_map.order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gok.food_map.annotation.Auth;
import com.gok.food_map.order.dto.*;
import com.gok.food_map.order.entity.OrderItem;
import com.gok.food_map.order.service.ProductOrderService;
import com.gok.food_map.order.vo.OrderGetListVO;
import com.gok.food_map.order.vo.OrderItemGetListVO;
import com.gok.food_map.order.vo.UserOrderInfoVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/getList")
    public IPage<OrderGetListVO> getList(@RequestBody OrderGetListDTO dto){
        return service.getList(dto);
    }

    @RequestMapping("/export")
    public void export( Long orderId,
                        Integer orderStatus,
                        String merchantName,
                        String userCode,
                        String[] time,
                        String payMethod,
                        String current,
                        String size, HttpServletResponse response) {
        List<String> timeList = new ArrayList<>();
        if (time == null || time.length == 0) {
            timeList.add(null);
            timeList.add(null);
        }else {
            timeList.add(time[0]);
            timeList.add(time[1]);
        }
        OrderGetListDTO dto  = new OrderGetListDTO(current,size,orderId,orderStatus,merchantName,userCode,timeList,payMethod);
        service.export(dto,response);
    }

   @Auth
   @PostMapping("/getOrders")
   public List<UserOrderInfoVO> getOrders(HttpServletRequest request){
        return service.getUserOrderInfos(request);
   }

   @Auth
   @PostMapping("/buyByCart")
   public Boolean  confirmBuy(@RequestBody Map<String,List<BuyByCartDto>> cartItems, HttpServletRequest request){
       List<BuyByCartDto> buyByCartDtos = cartItems.get("cartItems");
       return service.buyByCart(buyByCartDtos,request);
   }
    @PostMapping("/getOrderItems")
    public IPage<OrderItemGetListVO> getOrderItems(@RequestBody OrderItemGetListDTO dto){
        return service.getOrderItems(dto);
    }
}
