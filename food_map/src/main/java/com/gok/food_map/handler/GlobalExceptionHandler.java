package com.gok.food_map.handler;



import com.auth0.jwt.exceptions.TokenExpiredException;
import com.gok.food_map.exception.ResponseEnum;
import com.gok.food_map.util.Resp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// * 全局异常处理器，用于捕获并处理应用程序中抛出的特定异常。
@RestControllerAdvice
public class GlobalExceptionHandler {

//  日志记录器 用于记录异常信息。
    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 处理Token过期异常。
     * 当用户持有的Token过期时，此异常会被抛出。
     * 使用@RestControllerAdvice注解，标识这个类是处理这个异常的全局控制器。
     *
     * @param e Token过期异常的具体实例，包含详细的错误信息。
     * @return 返回一个封装了错误信息的响应对象。
     **/
    @ExceptionHandler(TokenExpiredException.class)
    public Resp<?> handleTokenExpiredException(TokenExpiredException e) {
        // 记录token过期的错误信息
        logger.error("token已过期");
        logger.error(e.getMessage());
        // 返回一个表示Token过期的错误响应
        return Resp.error(ResponseEnum.TOKEN_EX);
    }
}
