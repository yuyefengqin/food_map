package com.gok.food_map.merchant.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gok.food_map.merchant.dto.MerchantGetListDTO;
import com.gok.food_map.merchant.dto.MerchantRemoveDTO;
import com.gok.food_map.merchant.dto.MerchantSaveDTO;
import com.gok.food_map.merchant.service.MerchantService;
import com.gok.food_map.merchant.vo.MerchantGetListVO;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Resource
    private MerchantService merchantService;

    //获取列表
    @PostMapping("/getList")
    public IPage<MerchantGetListVO> getList(@RequestBody MerchantGetListDTO dto) {

        return merchantService.getList(dto);
    }

    //新增
    @PostMapping("/add")
    public void add(@RequestBody MerchantSaveDTO dto) {
        merchantService.add(dto);
    }

//    //编辑
//    @PostMapping("/edit")
//    public void edit(@RequestBody MerchantSaveDTO dto) {
//
//        merchantService.edit(dto);
//    }

    //删除
    @PostMapping("/remove")
    public void remove(@RequestBody MerchantRemoveDTO dto) {

        merchantService.remove(dto);
    }
}
