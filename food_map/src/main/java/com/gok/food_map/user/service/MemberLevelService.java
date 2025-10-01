package com.gok.food_map.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gok.food_map.user.entity.MemberLevel;
import com.gok.food_map.user.mapper.MemberLevelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
}




