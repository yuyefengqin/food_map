package com.gok.food_map.district.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gok.food_map.district.dto.AddressDeleteDTO;
import com.gok.food_map.district.dto.AddressOperateDTO;
import com.gok.food_map.district.entity.MAddress;
import com.gok.food_map.district.mapper.MAddressMapper;
import com.gok.food_map.district.vo.AddressGetVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 32741
 * @description 针对表【m_address(收货地址表)】的数据库操作Service实现
 * @createDate 2025-10-02 21:50:00
 */
@Service
public class MAddressService extends ServiceImpl<MAddressMapper, MAddress> implements IService<MAddress> {
    @Resource
    private MAddressMapper mAddressMapper;
    //从这里开始去补我管理地址需要的方法
    //增和改
    public void saveOrUpdateAddress(AddressOperateDTO dto){
        MAddress mAddress = new MAddress();
        mAddress.setAddressId(Long.valueOf(dto.getUserId()));
        BeanUtils.copyProperties(dto,mAddress);
        mAddress.setUserId(Long.valueOf(dto.getUserId()));
        // 如果设置为默认地址，那将该用户的其他所有的地址改为非默认
        if (dto.getIsDefault() != null && dto.getIsDefault()) {
            mAddressMapper.updateAllNonDefault(Long.valueOf(dto.getUserId()));
        }
        if (dto.getAddressId() == null) {
            //如果是第一个地址，就自动设为默认地址
            mAddressMapper.insert(mAddress);
        } else {
            // 编辑部分在这里
            mAddressMapper.updateById(mAddress);
        }
    }
    // 删
    @Transactional
    public void deleteAddress(AddressDeleteDTO dto){
        // 检查地址是否存在且属于该用户，如果不是该用户也不给
        MAddress address = mAddressMapper.selectById(Long.valueOf(dto.getAddressId()));
        if (address != null) {
            mAddressMapper.deleteById(Long.valueOf(dto.getAddressId()));
        }
    }
    // 查
    public List<MAddress> getUserAddresses(Long userId) {
        QueryWrapper<MAddress> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return mAddressMapper.selectList(wrapper);
    }

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
                    addressGetVO.setAddressId(iPage.getAddressId().toString());
                    addressGetVO.setIsDefault(iPage.getisDefault());
                    return addressGetVO;
                })
                .toList());
        return voiPage;
    }
}




