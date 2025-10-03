package com.gok.food_map.district.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.gok.food_map.district.entity.MDistrict;
import com.gok.food_map.district.vo.DistrictGetVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DistrictMapper extends BaseMapper<MDistrict> {
    List<DistrictGetVO> getDistrict(Long parent);
}
