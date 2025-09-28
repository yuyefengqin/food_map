package com.gok.food_map.log.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gok.food_map.log.annotation.Log;
import com.gok.food_map.log.context.UserContext;
import com.gok.food_map.log.entity.SysLog;
import com.gok.food_map.log.service.SysLogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {
    //注入service
    private final SysLogService sysLogService;
    private final ObjectMapper objectMapper;

    //拦截所有controller的方法
    @Pointcut("execution(* com.gok.food_map..controller.*.*(..))")
    public void logPointcut() {}
    @Around("logPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = null;
        boolean success = true;
        String errorMsg = null;
        try {
            // 执行目标方法
            result = joinPoint.proceed();
        } catch (Exception e) {
            success = false;
            errorMsg = e.getMessage();
            throw e;
        } finally {
            // 记录日志
            saveLog(joinPoint, startTime, success, errorMsg);
        }
        return result;
    }
    /**
     * 构建并保存日志
     */
    private void saveLog(ProceedingJoinPoint joinPoint, long startTime, boolean success, String errorMsg) {
        // 获取当前请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(attributes)) {
            return; // 非Web请求不记录日志
        }
        HttpServletRequest request = attributes.getRequest();

        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 构建日志对象
        SysLog sysLog = new SysLog();
        sysLog.setUserId(getCurrentUserId()); // 当前用户ID
        sysLog.setUsername(getCurrentUsername()); // 当前用户名
        sysLog.setOperation(getOperation(method)); // 操作描述
        sysLog.setBusinessType(getBusinessType(method)); // 业务类型
        sysLog.setRequestMethod(request.getMethod()); // 请求方法（GET/POST）
        sysLog.setRequestUrl(request.getRequestURI()); // 请求URL
        sysLog.setRequestParam(getRequestParams(joinPoint, method)); // 请求参数
        sysLog.setIpAddress(request.getRemoteAddr()); // 客户端IP
        sysLog.setExecutionTime(System.currentTimeMillis() - startTime); // 执行时间
        sysLog.setSuccess(success);
        sysLog.setErrorMsg(errorMsg);
        sysLog.setCreateTime(LocalDateTime.now()); // 操作时间

        // 保存日志
        sysLogService.saveLog(sysLog);
    }

    /**
     * 从注解或方法名获取操作描述
     */
    private String getOperation(Method method) {
        Log logAnnotation = method.getAnnotation(Log.class);
        return logAnnotation != null && !logAnnotation.operation().isEmpty()
                ? logAnnotation.operation()
                : method.getName(); // 默认使用方法名
    }

    /**
     * 从注解或类名获取业务类型
     */
    private String getBusinessType(Method method) {
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null && !logAnnotation.businessType().isEmpty()) {
            return logAnnotation.businessType();
        }
        // 从Controller类名提取（如UserController -> 用户管理）
        String className = method.getDeclaringClass().getSimpleName();
        return className.replace("Controller", "") + "管理";
    }

    /**
     * 获取请求参数（排除文件类型，避免序列化问题）
     */
    private String getRequestParams(ProceedingJoinPoint joinPoint, Method method) {
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null && logAnnotation.ignoreParam()) {
            return "参数已忽略"; // 标记忽略的参数
        }

        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            return "无参数";
        }

        // 过滤文件类型参数（MultipartFile）
        Object[] filteredArgs = Arrays.stream(args)
                .filter(arg -> !(arg instanceof MultipartFile) && !(arg instanceof MultipartFile[]))
                .toArray();

        try {
            return objectMapper.writeValueAsString(filteredArgs);//序列化
        } catch (Exception e) {
            return "参数解析失败: " + e.getMessage();
        }
    }

    private Long getCurrentUserId() {
        return UserContext.getUserId(); // 测试用的
    }

    private String getCurrentUsername() {
        return UserContext.getUsername(); // 测试用的
    }
}
