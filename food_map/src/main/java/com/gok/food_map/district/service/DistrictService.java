package com.gok.food_map.district.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gok.food_map.district.entity.MDistrict;
import com.gok.food_map.district.mapper.DistrictMapper;
import com.gok.food_map.district.vo.DistrictGetVO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class DistrictService extends ServiceImpl<DistrictMapper, MDistrict> {
    private final DistrictMapper mDistrictMapper;
    public MDistrict get(Long id) {
        return mDistrictMapper.selectById(id);
    }
    public List<DistrictGetVO> getByParent(Long parent) {
        if (parent == null) {
            parent = 0L;
        }
        List<DistrictGetVO> district= mDistrictMapper.getDistrict(parent);
        List<DistrictGetVO> res= new ArrayList<>();
        Map<String, DistrictGetVO> map= new HashMap<>();
        //生成map（方便后续通过id查询对应的entity）
        district.forEach(d -> map.put(d.getValue(), d));
        //构建children
        for(DistrictGetVO vo :district) {
            DistrictGetVO p= map.get(vo.getParent());
            if(p == null) {
                res.add(vo);
                continue;
            }
            List<DistrictGetVO> children= p.getChildren();
            if(children == null) {
                children = new ArrayList<>();
                p.setChildren(children);
            }
            children.add(vo);
        }
        return res;
    }
}
