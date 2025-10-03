package com.gok.food_map.district.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName("m_district")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MDistrict implements Serializable {
    @TableId
    private Long id;
    private String name;
    private Long parent;
}
