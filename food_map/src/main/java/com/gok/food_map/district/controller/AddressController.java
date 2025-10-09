package com.gok.food_map.district.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gok.food_map.district.dto.AddressDeleteDTO;
import com.gok.food_map.district.dto.AddressGetDto;
import com.gok.food_map.district.dto.AddressOperateDTO;
import com.gok.food_map.district.entity.MAddress;
import com.gok.food_map.district.vo.AddressGetVO;
import com.gok.food_map.district.service.MAddressService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Resource
    private MAddressService addressService;

    //增和改
    @PostMapping("/saveOrUpdate")
    public void addAddress(@RequestBody AddressOperateDTO dto) {
        addressService.saveOrUpdateAddress(dto);
    }
    //删
    @PostMapping("/delete")
    public void delete(@RequestBody AddressDeleteDTO dto) {
        addressService.deleteAddress(dto);
    }
    //查
    @PostMapping("/getAll")
    public IPage<AddressGetVO> getAll(@RequestBody AddressGetDto dto) {
        // 校验userId是否为空
        if (dto.getId() == null || dto.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        Long userId;
        try {
            userId = Long.valueOf(dto.getId());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("用户ID格式错误");
        }
        return addressService.selectPage(String.valueOf(userId));
    }
    @PostMapping("/getUserAddress")
    public IPage<AddressGetVO> getUserAddress(@RequestBody AddressGetDto dto) {
        return addressService.selectPage(dto.getId());
    }
}
