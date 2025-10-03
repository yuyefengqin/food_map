package com.gok.food_map.district.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gok.food_map.district.dto.AddressGetDto;
import com.gok.food_map.district.vo.AddressGetVO;
import com.gok.food_map.district.service.MAddressService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Resource
    private MAddressService addressService;

    @PostMapping("/getUserAddress")
    public IPage<AddressGetVO> getUserAddress(@RequestBody AddressGetDto dto) {
        return addressService.selectPage(dto.getId());
    }
}
