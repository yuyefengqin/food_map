package com.gok.food_map.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gok.food_map.annotation.Auth;
import com.gok.food_map.util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

// 登录拦截器类
// 实现 HandlerInterceptor 接口 用于拦截请求并进行登录验证。
public class LoginInterceptor implements HandlerInterceptor {

    /**
     *
     * @param request  HTTP 请求对象
     * @param response HTTP 响应对象
     * @param handler  处理请求的处理器对象，可能是 Controller 方法或其他类型的处理器
     * @return 是否继续处理请求，如果返回 false，则不会继续执行该请求的处理器方法
     * @throws Exception 如果处理过程中抛出异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断处理器是否为 HandlerMethod 类型 即是否为一个方法处理器
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 检查处理器方法所属的 bean 是否为 BasicErrorController 类型 如果是 则直接放行
            if (handlerMethod.getBean() instanceof BasicErrorController) {
                return true;
            }
            // 获取处理器方法上的 Auth 注解 用于判断该方法是否需要认证
            Auth auth = handlerMethod.getMethod().getAnnotation(Auth.class);
            // 如果存在 Auth 注解且要求认证
            if (auth != null && auth.require()) {
                // 从请求头中获取 token 用于认证
                Object o = request.getSession().getAttribute("token");
                String token;
                if(o == null){
                    token = request.getHeader("Authorization");
//                    request.getRequestDispatcher("/error/token").forward(request, response);
                }else {
                    token = o.toString();
                }
                // 如果 token 不为空且通过验证
                if (StringUtils.isNotBlank(token)) {
                    if (TokenUtil.verifyToken(token)) {
                        // 认证成功 继续处理请求
                        return true;
                    } else {
                        // 认证失败 重定向到 token 错误页面
                        response.sendRedirect("/error/tokenError");
                    }
                } else {
                    // 未提供 token 重定向到 token 缺失页面
                    response.sendRedirect("/error/token");
                }
            } else {
                // 不需要认证 继续处理请求
                return true;
            }
        } else {
            // 非 HandlerMethod 类型的处理器 直接放行
            return true;
        }
        // 如果执行到此处 表示拦截处理失败 不应继续处理请求
        return false;
    }
}

