package com.gok.food_map.merchant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gok.food_map.file.service.FileService;
import com.gok.food_map.merchant.dto.MerchantSaveDTO;
import com.gok.food_map.merchant.entity.MMerchant;
import com.gok.food_map.merchant.mapper.MMerchantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class MerchantService extends ServiceImpl<MMerchantMapper, MMerchant>
        implements IService<MMerchant> {

    private final FileService fileService;

    //新增
    @Transactional
    public void add(MerchantSaveDTO dto) {
        MMerchant mMerchant = new MMerchant();
        BeanUtils.copyProperties(dto, mMerchant);
        this.save(mMerchant); //保存用户数据
        fileService.enable(mMerchant.getBusinessLicense());//生效营业执照
        fileService.enable(mMerchant.getLogoUrl());//生效logo
    }
}
