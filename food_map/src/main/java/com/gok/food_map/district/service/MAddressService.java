package com.gok.food_map.district.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gok.food_map.district.entity.MAddress;
import com.gok.food_map.district.mapper.MAddressMapper;
import com.gok.food_map.district.vo.AddressGetVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
* @author 32741
* @description 针对表【m_address(收货地址表)】的数据库操作Service实现
* @createDate 2025-10-02 21:50:00
*/
@Service
public class MAddressService extends ServiceImpl<MAddressMapper, MAddress> implements IService<MAddress> {
    @Resource
    private MAddressMapper mAddressMapper;


    public IPage<AddressGetVO> selectPage(String userId) {
        IPage<MAddress> page = new Page<>(1,10);
        QueryWrapper<MAddress> wrapper = new QueryWrapper<>();
        wrapper.eq(userId != null,"user_id",Long.valueOf(userId));
        IPage<MAddress> iPages = mAddressMapper.selectPage(page, wrapper);
        IPage<AddressGetVO> voiPage = new Page<>();
        BeanUtils.copyProperties(iPages,voiPage);
        voiPage.setRecords(iPages
                .getRecords()
                .stream()
                .map(iPage ->{
                    AddressGetVO addressGetVO = new AddressGetVO();
                    BeanUtils.copyProperties(iPage,addressGetVO);
                    addressGetVO.setDefault(iPage.getIsDefault());
                    return addressGetVO;
                })
                .toList());
        return voiPage;
    }
}




