package com.gok.food_map.handler;

import com.gok.food_map.exception.ResponseEnum;
import com.gok.food_map.exception.ServiceException;
import com.gok.food_map.util.Resp;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 全局异常处理器，用于统一处理应用程序中抛出的各种异常
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理业务异常 ServiceException
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Resp<?> handleServiceException(ServiceException e, HttpServletRequest request) {
        logger.warn("业务异常: {} - {}", request.getRequestURI(), e.getServiceMsg());

        return Resp.error(e.getServiceMsg().getCode(), e.getServiceMsg().getMsg());
    }

    /**
     * 处理参数校验异常 - MethodArgumentNotValidException
     * 用于处理@RequestBody标注的控制器方法形参和@Validated标注的DTO参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Resp<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                         HttpServletRequest request) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        logger.warn("参数校验失败: {} - {}", request.getRequestURI(), errorMessage);

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        logger.warn("字段详细错误信息: {}", errors);

        return Resp.error(ResponseEnum.PARAM_ERROR.getCode(), "参数校验失败: " + errorMessage,errors);
    }

    /**
     * 处理参数绑定异常 - BindException
     * 用于非@RequestBody的参数绑定校验
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Resp<?> handleBindException(BindException e, HttpServletRequest request) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        logger.warn("参数绑定失败: {} - {}", request.getRequestURI(), errorMessage);

        return Resp.error(ResponseEnum.PARAM_ERROR.getCode(), "参数绑定失败: " + errorMessage);
    }


    /**
     * 处理请求参数缺失异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Resp<?> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e, HttpServletRequest request) {

        logger.warn("请求参数缺失: {} - 参数名: {}, 参数类型: {}",
                request.getRequestURI(), e.getParameterName(), e.getParameterType());

        String message = String.format("缺少必要参数: %s", e.getParameterName());
        return Resp.error(ResponseEnum.PARAM_ERROR.getCode(), message);
    }

    /**
     * 处理参数类型不匹配异常
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Resp<?> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e, HttpServletRequest request) {

        logger.warn("参数类型不匹配: {} - 参数名: {}, 期望类型: {}, 实际值: {}",
                request.getRequestURI(), e.getName(),
                e.getRequiredType(), e.getValue());

        String message = String.format("参数类型不匹配: %s，期望类型: %s",
                e.getName(), Objects.requireNonNull(e.getRequiredType()).getSimpleName());
        return Resp.error(ResponseEnum.PARAM_ERROR.getCode(), message);
    }

    /**
     * 处理所有未捕获的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Resp<?> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        logger.error("运行时异常: {} - {}", request.getRequestURI(), e.getMessage(), e);

        return Resp.error(ResponseEnum.SYSTEM_ERROR.getCode(), "系统繁忙，请稍后重试");
    }

    /**
     * 处理所有未捕获的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Resp<?> handleException(Exception e, HttpServletRequest request) {
        logger.error("系统异常: {} - {}", request.getRequestURI(), e.getMessage(), e);

        return Resp.error(ResponseEnum.SYSTEM_ERROR.getCode(), "系统异常，请稍后重试");
    }

}