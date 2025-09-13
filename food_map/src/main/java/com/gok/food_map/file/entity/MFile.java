package com.gok.food_map.file.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件
 * @TableName m_file
 */
@TableName(value ="m_file")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MFile implements Serializable {
    /**
     * 编号
     */
    @TableId
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 有效
     */
    private Boolean enable;

    /**
     * 路径
     */
    private String path;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}