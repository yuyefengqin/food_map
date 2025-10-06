package com.gok.food_map.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gok.food_map.user.dto.LevelSaveDTO;
import com.gok.food_map.user.entity.MemberLevel;
import com.gok.food_map.user.mapper.MemberLevelMapper;
import com.gok.food_map.user.vo.LevelGetListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* @author 32741
* @description 针对表【member_level(会员等级表)】的数据库操作Service实现
* @createDate 2025-10-01 15:44:12
*/
@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class MemberLevelService {

    private final MemberLevelMapper mapper;

    public MemberLevel findById(int id) {
        return mapper.selectById(id);
    }
    public MemberLevel findByName(String name) {
        LambdaQueryWrapper<MemberLevel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasText(name),MemberLevel::getLevelName, name);
        return mapper.selectList(wrapper).getFirst();
    }


    public List<MemberLevel> findAll() {
        return mapper.selectList(null);
    }

    public void edit(LevelSaveDTO dto) {
        check(dto,false);
        MemberLevel memberLevel = new MemberLevel();
        BeanUtils.copyProperties(dto, memberLevel);
        mapper.updateById(memberLevel);
    }

    public List<LevelGetListVO> getList() {
        List<MemberLevel> levels = this.findAll();
        List<LevelGetListVO> res = new ArrayList<>();
        BeanUtils.copyProperties(res, levels);
        for (MemberLevel level : levels) {
            LevelGetListVO vo = new LevelGetListVO();
            BeanUtils.copyProperties(level, vo);
            vo.setDiscountRate(level.getDiscountRate().toString());
            res.add(vo);
        }
        return res;
    }

    public void add(LevelSaveDTO dto) {
        check(dto,true);
        MemberLevel memberLevel = new MemberLevel();
        BeanUtils.copyProperties(dto, memberLevel);
        mapper.insert(memberLevel);
    }
    public void delete(int id) {
     MemberLevel memberLevel = mapper.selectById(id);
     if (memberLevel != null) {
         mapper.deleteById(id);
     }
    }
    public void check(LevelSaveDTO dto,boolean isAdd) {
        MemberLevel memberLevel = null;
        Integer id = dto.getLevelId();
        if (isAdd) {
            dto.setLevelId(null);
        }else  {
            memberLevel = mapper.selectById(id);
            if (memberLevel == null) {
                throw new RuntimeException("会员等级不存在");
            }
        }
        String levelName = dto.getLevelName();
        if (!StringUtils.hasText(levelName)) {
            throw new RuntimeException("会员等级名称错误");
        }
        BigDecimal discountRate = dto.getDiscountRate();
        if (discountRate == null) {
            throw new RuntimeException("折扣错误");
        }
    }
}




