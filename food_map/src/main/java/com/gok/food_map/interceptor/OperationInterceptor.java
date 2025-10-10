package com.gok.food_map.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Map;
//好jb臃肿
//精简一点
@Component
public class OperationInterceptor implements HandlerInterceptor {
    //用log代替掉s
    private static final Logger log = LoggerFactory.getLogger(OperationInterceptor.class);
    //拦截器在我的注释处理之前调用
    //先重写
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //换个更完全的思路,然后精简一点
        //我应该把无效的请求过滤掉

        String requestUri = request.getRequestURI();
        //ico那个是网页小图标，和我的代码没啥关系，但是运行的话就会有404，不好看
        //error那个是错误页面，也是和我的代码无关，就是前面的ico导致的
        if ("/favicon.ico".equals(requestUri) || "/error".equals(requestUri)) {
            log.debug("[拦截器] 过滤无效请求: {}", requestUri);
            return true; // 放行，但不执行后续日志记录逻辑
        }
        //记录请求开始时间
        //优化一下,加上debug日志
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime); // 明确设置属性
        log.debug("[拦截器] 记录请求开始时间: {}ms (请求路径: {})", startTime, request.getRequestURI());
        //还是路径和方法
        String uri = request.getRequestURI();
        String method = request.getMethod();
        //获取客户端的浏览器信息,虽然不知道有什么用,学都学了
        String userAgent = request.getHeader("User-Agent");

        // 输出全部的日志
        log.info("[拦截器] {} {} - 浏览器: {}",
                method, uri,
                userAgent);
        //放行
        return true;
    }

    //最后调用
    //异常日志处理问题的核心原因是,拦截器的afterCompletion方法执行时机早于 AOP 的异常捕获,导致异常信息丢失
    //所以的我当务之急是调整拦截器的执行逻辑
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();
        Long startTime = (Long) request.getAttribute("startTime");
        // 处理startTime可能为null的情况
        long executeTime = startTime != null ? endTime - startTime : 0;
        //最后给个结果
        //这样就可以看到异常在哪里
        String result;
        //之前出来异常还显示成功,那很糟糕了
        //分开写
        if (ex != null) {
            result = "出现异常:" + ex.getMessage();
            // 异常日志使用error级别
            log.error("[拦截器] {} {} - 处理完成 - 耗时: {}ms - 结果: {}",
                    request.getMethod(), request.getRequestURI(), executeTime, result);
        } else {
            result = "请求成功,状态码:"+ response.getStatus();
            log.info("[拦截器] {} {} - 处理完成 - 耗时: {}ms - 结果: {}",
                    request.getMethod(), request.getRequestURI(), executeTime, result);
        }
    }
}
