package com.gok.food_map.log.interceptor;

import com.gok.food_map.log.context.UserContext;
import com.gok.food_map.log.service.TokenStore;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Resource
    private TokenStore tokenStore;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 排除登录接口（允许匿名访问）
        String requestURI = request.getRequestURI();
        if ("/user/login".equals(requestURI)) {
            return true;
        }

        // 从请求头获取Token
        String token = request.getHeader("Authorization");
        if (token == null || !tokenStore.isValid(token)) {
            response.setStatus(401); // 未授权
            return false;
        }

        // 设置用户上下文（ID和用户名）
        Long userId = tokenStore.getUserId(token);
        String username = tokenStore.getUsername(token);
        UserContext.setUserId(userId);
        UserContext.setUsername(username);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清除上下文，防止内存泄漏
        UserContext.clear();
    }
}
