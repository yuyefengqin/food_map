package com.gok.food_map.merchant.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gok.food_map.file.service.FileService;
import com.gok.food_map.merchant.dto.MerchantGetListDTO;
import com.gok.food_map.merchant.dto.MerchantRemoveDTO;
import com.gok.food_map.merchant.dto.MerchantSaveDTO;
import com.gok.food_map.merchant.entity.MMerchant;
import com.gok.food_map.merchant.mapper.MMerchantMapper;
import com.gok.food_map.merchant.vo.MerchantGetListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class MerchantService extends ServiceImpl<MMerchantMapper, MMerchant>
        implements IService<MMerchant> {
    private final MMerchantMapper mMerchantMapper;
    private final FileService fileService;
    private static final String PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z\\W]{6,20}$";//密码

    //新增
    @Transactional
    public void add(MerchantSaveDTO dto) {
        //checkSave(dto, true);
        MMerchant mMerchant = new MMerchant();
        BeanUtils.copyProperties(dto, mMerchant);
        mMerchantMapper.insert(mMerchant);//保存用户数据
        fileService.enable(mMerchant.getBusinessLicense());//生效营业执照
        fileService.enable(mMerchant.getLogoUrl());//生效logo
    }

    //编辑
    @Transactional
    public void edit(MerchantSaveDTO dto) {
        //checkSave(dto, false);
        MMerchant mMerchant = mMerchantMapper.selectById(dto.getMerchantId());
        //处理营业执照
        if (dto.getBusinessLicense() == null) { //删除营业执照
            fileService.remove(mMerchant.getBusinessLicense());//删除旧营业执照
        } else {
            if (!dto.getBusinessLicense().equals(mMerchant.getBusinessLicense())) { //替换营业执照
                fileService.remove(mMerchant.getBusinessLicense());//删除旧营业执照
                fileService.enable(dto.getBusinessLicense());//生效营业执照
            }
        }
        //处理logo
        if (dto.getLogoUrl() == null) { //删除原有logo
            fileService.remove(mMerchant.getLogoUrl());//删除旧logo
        } else {
            if (!dto.getBusinessLicense().equals(mMerchant.getLogoUrl())) { //替换logo
                fileService.remove(mMerchant.getLogoUrl());//删除旧logo
                fileService.enable(dto.getLogoUrl());//生效logo
            }
        }
        BeanUtils.copyProperties(dto, mMerchant);
        mMerchantMapper.updateById(mMerchant);
    }

    //获取列表
    public IPage<MerchantGetListVO> getList(MerchantGetListDTO dto) {
        //前端传后端
        IPage<MMerchant> page = new Page<>(dto.getCurrent() == null ? 1 : dto.getCurrent(), dto.getSize() == null ? 20 : dto.getSize());
        LambdaQueryWrapper<MMerchant> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(dto.getMerchantId() != null, MMerchant::getMerchantId, dto.getMerchantId());
        wrapper.like(StringUtils.hasText(dto.getMerchantName()),MMerchant::getMerchantName, dto.getMerchantName());
        wrapper.eq(dto.getStatus() != null, MMerchant::getStatus, dto.getStatus());
        wrapper.ge(dto.getCreateTime() != null, MMerchant::getCreateTime, dto.getCreateTime());
        wrapper.like(StringUtils.hasText(dto.getManageAccount()),MMerchant::getManageAccount, dto.getManageAccount());
        page = mMerchantMapper.selectPage(page, wrapper);

        //后端传前端
        IPage<MerchantGetListVO> res = new Page<>();
        BeanUtils.copyProperties(page, res);
        res.setRecords(page.getRecords().stream().map(mMerchant ->{MerchantGetListVO vo= new MerchantGetListVO();BeanUtils.copyProperties(mMerchant, vo);
                    vo.setMerchantId(mMerchant.getMerchantId().toString());
                    vo.setCreateTime(mMerchant.getCreateTime());
                    vo.setBusinessLicense(mMerchant.getBusinessLicense() == null ? null : mMerchant.getBusinessLicense().toString());
                    vo.setLogoUrl(mMerchant.getLogoUrl() == null ? null : mMerchant.getLogoUrl().toString());
                    return vo;
                }
        ).toList());
        return res;
    }

    //删除
    @Transactional
    public void remove(MerchantRemoveDTO dto) {
        MMerchant mMerchant = mMerchantMapper.selectById(dto.getMerchantId());
        if (mMerchant != null) {
            mMerchantMapper.deleteById(dto.getMerchantId());
            fileService.remove(mMerchant.getBusinessLicense());//删除营业执照
            fileService.remove(mMerchant.getLogoUrl());//删除logo
        }
    }


//    //校验
//    private void checkSave(MerchantSaveDTO dto, boolean isAdd) {
//
//        //TODO id校验
//        Long id = dto.getMerchantId();
//        MMerchant mMerchant = null;
//        if (isAdd) {
//            dto.setMerchantId(null);//新增时id设为null，交给雪花算法生成
//        } else {
//            //编辑时用户必须存在
//            mMerchant = this.getById(id);
//            if (mMerchant == null) {
//                throw new RuntimeException("用户不存在");
//            }
//        }
//
//        //TODO.1商户管理账号校验
//        String manageAccount = dto.getManageAccount();
//            //1.账号为空或者长度大于20
//        if (!StringUtils.hasText(manageAccount) || manageAccount.length() > 20) {
//            throw new RuntimeException("账号参数错误");//账号为空或者长度大于20
//        }
//            //2.新增时与已有商户管理账号不能重复
//        if (isAdd) {
//            LambdaQueryWrapper<MMerchant> wrapper = new LambdaQueryWrapper<>();
//            wrapper.eq(MMerchant::getManageAccount, manageAccount);
//            MMerchant byCode = this.getOne(wrapper);
//            if (byCode != null) {
//                throw new RuntimeException("账号已经存在");
//            }
//        } else {
//            dto.setManageAccount(mMerchant.getManageAccount());//编辑时，code不能修改，直接设置为原始值
//        }
//
//        //TODO.2管理账号密码校验
//        String password = dto.getManagePassword();
//        if (isAdd) {
//            if (!StringUtils.hasText(password) || !Pattern.matches(PASSWORD, password)) {
//                //密码为空或者密码格式不正确（请输入包含英文和数字，在6-20位之间）
//                throw new RuntimeException("管理账号密码格式不正确(至少6位)");
//            }
//        } else {
//            dto.setManagePassword(mMerchant.getManagePassword());//编辑时不许可修改密码，只能重置密码
//        }
//
//        //TODO.3商户名称校验(姓名为空或者长度大于30)
//        String name = dto.getMerchantName();
//        if (!StringUtils.hasText(name) || name.length() > 30) {
//            throw new RuntimeException("商户名称参数错误");
//        }
//
//        //TODO.4商户联系方式校验
//        String telephone = dto.getContactPhone();
//        if (!StringUtils.hasText(telephone) ||  telephone.length() > 11) {
//            throw new RuntimeException("商户联系方式参数错误");
//        }
//
//        //TODO.5Logo执照URL校验
//        Long Logo = dto.getLogoUrl();
//        if (Logo != null && fileService.get(Logo) == null) {
//            throw new RuntimeException("头像不存在");//有设置头像文件，但是文件不存在
//        }
//
//        //TODO.6营业执照URL校验
//        Long businessLicense = dto.getBusinessLicense();
//        if (businessLicense != null && fileService.get(businessLicense) == null) {
//            throw new RuntimeException("头像不存在");//有设置头像文件，但是文件不存在
//        }
//
//        //TODO.7企业类型校验
//
//
//        //TODO.8备注
//        String notes = dto.getNotes();
//        if (!StringUtils.hasText(notes) ||  notes.length() > 11) {
//            throw new RuntimeException("备注参数错误");
//        }
//    }

}
