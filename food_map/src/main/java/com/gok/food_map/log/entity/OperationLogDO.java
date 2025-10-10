package com.gok.food_map.log.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 系统操作日志表
 * @TableName operation_log
 */
@TableName(value ="operation_log")
@Data
public class OperationLogDO {
    /**
     * 日志ID
     */
    @TableId
    private Long id;

    /**
     * 操作描述
     */
    private String operation;

    /**
     * 调用方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 执行耗时(ms)
     */
    private Long executionTime;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 操作时间
     */
    private LocalDateTime createTime;

    public OperationLogDO(Long id, String operation, String method, String params, Long executionTime, Boolean success, String errorMsg, LocalDateTime createTime) {
        this.id = id;
        this.operation = operation;
        this.method = method;
        this.params = params;
        this.executionTime = executionTime;
        this.success = success;
        this.errorMsg = errorMsg;
        this.createTime = createTime;
    }

    public OperationLogDO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}