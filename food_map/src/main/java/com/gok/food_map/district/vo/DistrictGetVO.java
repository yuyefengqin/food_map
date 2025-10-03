package com.gok.food_map.district.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictGetVO {
    private String value;
    private String label;
    private String Parent;

    private List<DistrictGetVO> children;

    public List<DistrictGetVO> getChildren() {
        return children;
    }

    public void setChildren(List<DistrictGetVO> children) {
        this.children = children;
    }
}
