package com.gok.food_map.log.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gok.food_map.log.entity.SysLog;
import com.gok.food_map.log.mapper.SysLogMapper;
import org.springframework.stereotype.Service;

/**
* @author 速溶摇摆
* @description 针对表【sys_log(系统操作日志表)】的数据库操作Service实现
* @createDate 2025-09-24 23:09:54
*/
@Service
public class SysLogService extends ServiceImpl<SysLogMapper, SysLog> {
    public void saveLog(SysLog sysLog){
        this.save(sysLog);
    }
}




