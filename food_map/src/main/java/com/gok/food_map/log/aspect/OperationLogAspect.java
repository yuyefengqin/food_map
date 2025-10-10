package com.gok.food_map.log.aspect;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gok.food_map.log.annotation.OperationLog;
import com.gok.food_map.log.entity.OperationLogDO;
import com.gok.food_map.log.mapper.OperationLogMapper;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

//byd这个真的得优化一下
@Aspect
@Component
@RequiredArgsConstructor
public class OperationLogAspect {
    private static final Logger log = LoggerFactory.getLogger(OperationLogAspect.class);
    //用aop去拦截被注解标记到的方法
    //拦截器的话不知道要不要用
    //如果这个可以实现功能的话，拦截器再说吧
    @Resource
    private OperationLogMapper operationLogMapper;
    //ObjectMapper是用于将对象转换为json字符串的
    @Resource
    private ObjectMapper objectMapper;
    //切点
    //但是这个是单独的项目，controller的包名是不知道的
    //等会我搞个测试样例
    @Pointcut("@annotation(com.gok.food_map.log.annotation.OperationLog)")
    public void logPointCut() {}

    //尤其是这里
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //这个时间拦截器拦下来过了,直接拿
        //仍然优化
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Long interceptorStartTime = null; // 依旧Long包装类，判断null的问题
        if (attributes != null && attributes.getRequest() != null) {
            interceptorStartTime = (Long) attributes.getRequest().getAttribute("startTime");
            // 增加debug日志，验证是否拿到拦截器的startTime
            if (interceptorStartTime == null) {
                log.warn("[AOP] 未从请求属性中获取到拦截器记录的startTime，将使用AOP执行时间作为基准");
            } else {
                log.debug("[AOP] 成功获取拦截器记录的startTime: {}ms", interceptorStartTime);
            }
        }
        Object[] args = joinPoint.getArgs();
        // 优先使用拦截器的startTime，否则用AOP执行时的时间
        long startTime = interceptorStartTime != null ? interceptorStartTime : System.currentTimeMillis();
        Object result;//初始化这个方法的执行结果
        OperationLogDO logDO = new OperationLogDO();
        //直接在这里拿名字得了
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //直接开户
        OperationLog annotation = method.getAnnotation(OperationLog.class);
        //这个主要是检查有没有注解,还是在这里处理一下
        if (annotation == null) {
            log.warn("方法 {} 未找到 @OperationLog 注解，跳过日志记录", method.getName());
            return joinPoint.proceed(); // 直接执行方法，不记录日志
        }
        try {
            result = joinPoint.proceed();
            logDO.setSuccess(true);
            return result;
        } catch (Exception e) {
            logDO.setSuccess(false);
            logDO.setErrorMsg(e.getMessage());
            throw e;
        } finally {
            // 计算总耗时（从拦截器记录的时间开始）
            long executionTime = System.currentTimeMillis() - startTime;
            fillLog(logDO, annotation.value(),
                    method.getDeclaringClass().getName() + "." + method.getName(),args,
                    getParamsJson(joinPoint.getArgs()), executionTime);
            operationLogMapper.insert(logDO);
            printLog(logDO);
        }
        }
    // 提取参数解析为独立方法
    private String getParamsJson(Object[] args) {
        try {
            return objectMapper.writeValueAsString(args);
        } catch (Exception e) {
            return "参数解析失败: " + e.getMessage();
        }
    }
    private void fillLog(
            OperationLogDO log,
            String operationDesc,
            String fullName,
            Object[] params,
            String paramsJson,
            long Time) {
        log.setOperation(operationDesc);
        log.setMethod(fullName);
        log.setParams(Arrays.toString(params));
        log.setParams(paramsJson);
        log.setExecutionTime(Time);
        //这个是mybatis_plus自带的id生成器，感觉挺方便
        log.setId(DefaultIdentifierGenerator.getInstance().nextId(log));
        log.setCreateTime(LocalDateTime.now());
        //很爽了，确实看着舒服多了
    }
    private void printLog(OperationLogDO logDO) {
        //现在内容存进去了已经，直接一个形参就可以了
        //先看看里面是不是传进去了
        String msg = logDO.getSuccess() ? "操作成功" : "操作失败";
        //打印
        log.info("[操作日志] 日志ID: {} | 操作: {} | 方法: {} | 耗时: {}ms | 结果: {}",
                logDO.getId(),
                logDO.getOperation(),
                logDO.getMethod(),
                logDO.getExecutionTime(),
                msg);
        log.info("[传入参数]: {}",logDO.getParams());
    }
}