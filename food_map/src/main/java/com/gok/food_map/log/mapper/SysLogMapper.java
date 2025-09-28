package com.gok.food_map.log.mapper;

import com.gok.food_map.log.entity.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 速溶摇摆
* @description 针对表【sys_log(系统操作日志表)】的数据库操作Mapper
* @createDate 2025-09-24 23:09:54
* @Entity com.gok.food_map.log.entity.SysLog
*/
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {

}




