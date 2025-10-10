package com.gok.food_map.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gok.food_map.log.entity.OperationLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 速溶摇摆
* @description 针对表【operation_log(系统操作日志表)】的数据库操作Mapper
* @createDate 2025-10-01 20:56:13
* @Entity com.gok.food_map_log.log.domain.OperationLog
*/
@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLogDO> {

}




