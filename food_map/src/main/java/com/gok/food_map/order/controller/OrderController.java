package com.gok.food_map.order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gok.food_map.order.dto.OrderGetListDTO;
import com.gok.food_map.order.dto.OrderRemoveDTO;
import com.gok.food_map.order.dto.OrderSaveDTO;
import com.gok.food_map.order.service.ProductOrderService;
import com.gok.food_map.order.vo.OrderGetListVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Lazy)
@RequestMapping("/order")
public class OrderController {
    private final ProductOrderService service;

    @PostMapping("/remove")
    public void removeOrder(@RequestBody OrderRemoveDTO dto) {
        service.remove(dto);
    }
    @PostMapping("/edit")
    public void editOrder(@RequestBody OrderSaveDTO dto) {
        service.edit(dto);
    }
    @PostMapping("/getList")
    public IPage<OrderGetListVO> getList(@RequestBody OrderGetListDTO dto){
        return service.getList(dto);
    }
}
